'use strict';
import {byId, toon, verberg} from "./util.js";

const response = await fetch("bkpf");
if (response.ok) {
    const bkpfResult = await response.json();
    const bkpfBody = byId("bkpfBody");
    const resultLength = bkpfResult.length;
    byId("resultNr").innerText = "Result(s): "+resultLength;
    if (resultLength >= 0) {
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

} else {
    toon("storing");
}

