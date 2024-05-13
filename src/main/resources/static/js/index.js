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
}

function clearTableBody() {
    const tbody = document.getElementById("bkpfBody");
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }
}

function verbergErrors() {
    byId("boekjaarError").hidden = true;
    byId("bedrijfsnummerError").hidden = true;
    byId("documentnummerStartError").hidden = true;
    byId("documentnummerEindError").hidden = true;
}