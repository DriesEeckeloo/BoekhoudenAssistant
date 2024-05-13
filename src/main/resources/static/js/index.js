'use strict';
import {byId, toon, verberg} from "./util.js";

clearTableBody();
byId("submitFilterButton").onclick = async () => {
    await submitFilters();
};
const response = await fetch("bkpf");
if (response.ok) {
    const bkpfResult = await response.json();
    createTableBodyData(bkpfResult);

} else {
    alert("storing bij ophalen data");
}

async function submitFilters() {
    verbergErrors();
    let boekjaar = byId("boekjaar").value;
    let bedrijfsnummer = byId("bedrijfsnummer").value;
    let documentnummerStart = byId("documentnummerStart").value;
    let documentnummerEind = byId("documentnummerEind").value;
    let allCorrect = true;
    if (!isNaN(boekjaar) || boekjaar.length === 0) {
        boekjaar = boekjaar.toString();
    } else {
        byId("boekjaarError").hidden = false;
        allCorrect = false;
    }
    if (!isNaN(bedrijfsnummer) || bedrijfsnummer.length === 0) {
        bedrijfsnummer = bedrijfsnummer.toString();
    } else {
        byId("bedrijfsnummerError").hidden = false;
        allCorrect = false;
    }
    if (!isNaN(documentnummerStart) || documentnummerStart.length === 0) {
        documentnummerStart = documentnummerStart.toString();
    } else {
        byId("documentnummerStartError").hidden = false;
        allCorrect = false;
    }
    if (!isNaN(documentnummerEind) || documentnummerEind.length === 0) {
        documentnummerEind = documentnummerEind.toString();
    } else {
        byId("documentnummerEindError").hidden = false;
        allCorrect = false;
    }

    if (allCorrect){
        let data = {
            "GJAHR": boekjaar,
            "BUKRS": bedrijfsnummer,
            "BELNR1": documentnummerStart,
            "BELNR2": documentnummerEind
        };
        const json = JSON.stringify(data);
        if (byId("details").checked) {
            const response = await fetch(`bkpf/filter/details`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: json
            });
            if (response.ok) {
                const bkpfResult = await response.json();
                clearTableBody();
                createTableBodyDataWithDetails(bkpfResult);
            } else {
                alert("storing bij ophalen data");
            }
        } else {
            const response = await fetch(`bkpf/filter`, {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: json
            });
            if (response.ok) {
                const bkpfResult = await response.json();
                clearTableBody();
                createTableBodyData(bkpfResult);
            } else {
                alert("storing bij ophalen data");
            }
        }
    }

}

function createTableBodyData(bkpfResult) {
    const bkpfBody = byId("bkpfBody");
    const resultLength = bkpfResult.length;
    byId("resultNr").innerText = "Result(s): " + resultLength;

    for (const bkpf of bkpfResult) {
        const tr = bkpfBody.insertRow();
        tr.insertCell().innerText = bkpf.bukrs;
        tr.insertCell().innerText = bkpf.belnr;
        tr.insertCell().innerText = bkpf.gjahr;
        tr.insertCell().innerText = bkpf.blart;
        tr.insertCell().innerText = bkpf.bldat;
        tr.insertCell().innerText = bkpf.budat;
        tr.insertCell().innerText = bkpf.monat;
        tr.insertCell().innerText = bkpf.cpudt;
        tr.insertCell().innerText = bkpf.cputm;
        bkpfBody.appendChild(tr);
    }
    wisselRijKleur();
}

function createTableBodyDataWithDetails(bkpfResult) {
    const bkpfBody = byId("bkpfBody");
    const resultLength = bkpfResult.length;
    byId("resultNr").innerText = "Result(s): " + resultLength;

    for (const bkpfDetails of bkpfResult) {
        const tr = bkpfBody.insertRow();
        tr.insertCell().innerText = bkpfDetails.bkpf.bukrs;
        tr.insertCell().innerText = bkpfDetails.bkpf.belnr;
        tr.insertCell().innerText = bkpfDetails.bkpf.gjahr;
        tr.insertCell().innerText = bkpfDetails.bkpf.blart;
        tr.insertCell().innerText = bkpfDetails.bkpf.bldat;
        tr.insertCell().innerText = bkpfDetails.bkpf.budat;
        tr.insertCell().innerText = bkpfDetails.bkpf.monat;
        tr.insertCell().innerText = bkpfDetails.bkpf.cpudt;
        tr.insertCell().innerText = bkpfDetails.bkpf.cputm;
        bkpfBody.appendChild(tr);
        if (bkpfDetails.detailsResults.length > 0) {
            const extraInfoRow = bkpfBody.insertRow();
            const extraInfoCell = extraInfoRow.insertCell();
            const detailTable = document.createElement("table");
            const thead = document.createElement("thead");
            detailTable.appendChild(thead);
            let headerRow = document.createElement('tr');
            thead.appendChild(headerRow);
            let buzeiTH = document.createElement('th');
            buzeiTH.innerText = "BUZEI";
            buzeiTH.title = "Nummer van boekingsregel in boekhoudingsdocument";
            headerRow.appendChild(buzeiTH);
            let bschlTH = document.createElement('th');
            bschlTH.innerText = "BSCHL";
            bschlTH.title = "boekingssleutel";
            headerRow.appendChild(bschlTH);
            let augdtTH = document.createElement('th');
            augdtTH.innerText = "AUGDT";
            augdtTH.title = "de datum van vereffening";
            headerRow.appendChild(augdtTH);
            let detailsTbody = document.createElement("tbody");
            detailTable.appendChild(detailsTbody);
            for (const detail of bkpfDetails.detailsResults) {
                const tr = bkpfBody.insertRow();
                tr.insertCell().innerText = detail.BUZEI;
                tr.insertCell().innerText = detail.BSCHL;
                tr.insertCell().innerText = detail.AUGDT;
                detailsTbody.appendChild(tr);
            }
            detailTable.id = "detailsTable";
            detailsTbody.id = "detailsTbody";
            extraInfoCell.className = "extraInfoCell";
            let extraInfoDiv = document.createElement("div");
            extraInfoDiv.appendChild(detailTable);
            extraInfoCell.colSpan = 9; // Adjust this number to match the number of columns in your table
            extraInfoCell.appendChild(extraInfoDiv); // Replace this with the actual extra info
            extraInfoCell.style.borderLeft = "10px solid #54bceb";
        }

    }
}

function clearTableBody() {
    const tbody = document.getElementById("bkpfBody");
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }
}

function createDetailsField(details){
    const extraInfoRow = bkpfBody.insertRow();
    const extraInfoCell = extraInfoRow.insertCell();
    extraInfoCell.colSpan = 9; // Adjust this number to match the number of columns in your table
    extraInfoCell.innerText = "Extra info goes here"; // Replace this with the actual extra info
}

function verbergErrors() {
    byId("boekjaarError").hidden = true;
    byId("bedrijfsnummerError").hidden = true;
    byId("documentnummerStartError").hidden = true;
    byId("documentnummerEindError").hidden = true;
}

function wisselRijKleur(){
    let rows = document.querySelectorAll('#bkpfTable tr:nth-child(even)');
    for (let i = 0; i < rows.length; i++) {
        rows[i].style.backgroundColor = '#f2f2f2';
    }
}