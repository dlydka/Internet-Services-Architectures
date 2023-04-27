import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayMage();
});

/**
 * Fetches currently logged tower's mages and updates edit form.
 */
function fetchAndDisplayMage() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/towers/' + getParameterByName('tower'), true);
    xhttp.send();
}

/**
 * Action event handled for updating mage info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();
    
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayMage();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/towers/' + getParameterByName('tower'), true);

    const request = {
        'name': document.getElementById('name').value,
        'colour': document.getElementById('colour').value,
        'height': parseFloat(document.getElementById('height').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
    window.location.href = 'http://localhost:8083/tower_list/tower_list.html';
}
