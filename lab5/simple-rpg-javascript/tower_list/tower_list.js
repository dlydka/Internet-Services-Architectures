import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayTowers();
});

/**
 * Fetches all towers and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayTowers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayTowers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/towers', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display towers.
 *
 * @param {{towers: string[]}} towers
 */
function displayTowers(towers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    towers.towers.forEach(tower => {
        tableBody.appendChild(createTableRow(tower));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} tower
 * @returns {HTMLTableRowElement}
 */
function createTableRow(tower) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(tower));
    tr.appendChild(createLinkCell('view', '../tower_view/tower_view.html?tower=' + tower));
    tr.appendChild(createLinkCell('edit', '../tower_edit/tower_edit.html?tower=' + tower));
    tr.appendChild(createLinkCell('add mage', '../mage_add/mage_add.html?tower=' + tower));
    tr.appendChild(createButtonCell('delete', () => deleteTower(tower)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } tower to be deleted
 */
function deleteTower(tower) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayTowers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/towers/' + tower, true);
    xhttp.send();
}
