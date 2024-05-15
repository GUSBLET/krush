const table_news = document.querySelector('.table-light');
const modalTemp = document.getElementById("modal-temp");
const controllerPanel = document.getElementById('controller-panel');
const modalContent = document.querySelector('.main-content');
const mainModalContent = document.querySelector('.main-content-modal');
const span = document.getElementsByClassName("close")[0];

span.onclick = function () {
    modalTemp.style.display = "none";
    const modalContent = document.querySelector('.main-content');
    const controllerPanel = document.querySelector('#controller-panel');
    while (controllerPanel.firstChild) {
        controllerPanel.removeChild(controllerPanel.firstChild);
    }
    while (mainModalContent.firstChild) {
        mainModalContent.removeChild(mainModalContent.firstChild);
    }
}

window.onclick = function (event) {
    if (event.target === modalTemp) {
        modalTemp.style.display = "none";
        const modalContent = document.querySelector('.main-content');
        const controllerPanel = document.querySelector('#controller-panel');
        while (controllerPanel.firstChild) {
            controllerPanel.removeChild(controllerPanel.firstChild);
        }
        while (mainModalContent.firstChild) {
            mainModalContent.removeChild(mainModalContent.firstChild);
        }
    }
}


function getCallModalWindow(url) {
    axios.get(url)
        .then(response => {
            modalContent.innerHTML = response.data;
            modalTemp.appendChild(controllerPanel);

            modalTemp.style.display = 'block';
        }).catch(error => {
        // Handle any errors that occur during the request
        console.error('Error:', error);
    });
    ;


}

function deleteConfirming(url, id) {
    axios.get(url + '?id=' + id)
        .then(response => {

            const elementToDelete = document.getElementById(id);
            if (elementToDelete) {
                elementToDelete.remove();
                modalTemp.style.display = "none";
                const modalContent = document.querySelector('.main-content');
                const controllerPanel = document.querySelector('#controller-panel');
                while (controllerPanel.firstChild) {
                    controllerPanel.removeChild(controllerPanel.firstChild);
                }
                while (modalContent.firstChild) {
                    modalContent.removeChild(modalContent.firstChild);
                }
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function openModalRemove(url, id) {


    const action = document.createElement("button");
    action.textContent = "Видалити";
    action.className = "button-modal"
    action.onclick = function () {
        deleteConfirming(url, id);
    };
    const message = document.createElement("p");
    message.textContent = "Підтвердіть видалення запису"

    mainModalContent.appendChild(message);
    controllerPanel.appendChild(action);
    modalTemp.style.display = 'block';
}

