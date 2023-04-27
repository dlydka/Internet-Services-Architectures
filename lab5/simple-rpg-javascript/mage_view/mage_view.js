import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayMage();
});

/**
 * Fetches single tower and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayMage() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayMage(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/towers/' + getParameterByName('tower') 
    + '/mages/' + getParameterByName('mage'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display tower.
 *
 * @param {{name: string, age: int, level: int}} mage
 */
function displayMage(mage) {
    setTextNode('name', mage.name);
    setTextNode('age', mage.age);
    setTextNode('level', mage.level);
}
