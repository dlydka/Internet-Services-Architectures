import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    //fetchAndDisplayMage();
});

/**
 * Action event handled for updating mage info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();
    
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //fetchAndDisplayMage();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/towers', true);

    const request = {
        'name': document.getElementById('name').value,
        'colour': (document.getElementById('colour').value),
        'height': parseFloat(document.getElementById('height').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.href = 'http://localhost:8083/tower_list/tower_list.html';
}