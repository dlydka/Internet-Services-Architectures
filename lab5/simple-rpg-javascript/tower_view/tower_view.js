import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayTower();
    fetchAndDisplayMages();
});

/**
 * Fetches all towers and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayMages() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayMages(JSON.parse(this.responseText));
            displayTable();
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/towers/' + getParameterByName('tower') + '/mages', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display mages.
 *
 * @param {{mages: {id: number, name:string}[]}} mages
 */
function displayMages(mages) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    mages.mages.forEach(mage => {
        tableBody.appendChild(createTableRow(mage));
    })
}

function displayTable() {
    let tableBody = document.getElementById('tableBody1');
    clearElementChildren(tableBody);
    tableBody.appendChild(createTableRow1());
}

function createTableRow1() {
    let tr = document.createElement('tr');
    tr.appendChild(createLinkCell('add mage', '../mage_add/mage_add.html?tower='
        + getParameterByName('tower')));
    return tr;
}

/**
 * Creates single table row for entity.
 *
 * @param {{id: number, name: string}} mage
 * @returns {HTMLTableRowElement}
 */
function createTableRow(mage) {
    let tr = document.createElement('tr');
    //tr.appendChild(createImageCell(getBackendUrl() + '/api/towers/' + getParameterByName('tower') + '/mages/'
        //+ mage.id + '/portrait'));
    tr.appendChild(createTextCell(mage.name));
    tr.appendChild(createLinkCell('view', '../mage_view/mage_view.html?tower='
        + getParameterByName('tower') + '&mage=' + mage.id));
    tr.appendChild(createLinkCell('edit', '../mage_edit/mage_edit.html?tower='
        + getParameterByName('tower') + '&mage=' + mage.id));
    tr.appendChild(createButtonCell('delete', () => deleteMage(mage.id)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} mage to be deleted
 */
function deleteMage(mage) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayMages();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/towers/' + getParameterByName('tower')
        + '/mages/' + mage, true);
    xhttp.send();
}


/**
 * Fetches single tower and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayTower() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTower(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/towers/' + getParameterByName('tower'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display tower.
 *
 * @param {{name: string, colour: string, height: double}} tower
 */
function displayTower(tower) {
    setTextNode('name', tower.name);
    setTextNode('colour', tower.colour);
    setTextNode('height', tower.height);
}
