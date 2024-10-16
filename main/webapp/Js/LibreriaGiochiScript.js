/**
 * 
 */


const toggleButtonAggiungi = document.getElementById('toggle-form-aggiungi-btn');
const formContainerAggiungi = document.getElementById('form-container-aggiungi');

const toggleButtonElimina = document.getElementById('toggle-form-elimina-btn');
const formContainerElimina = document.getElementById('form-container-rimuovi');

toggleButtonAggiungi.addEventListener('click', function() {
    if (formContainerAggiungi.style.display === "none") {
        formContainerAggiungi.style.display = "block";
        toggleButtonAggiungi.textContent = "Nascondi aggiungi libreria";
    } else {
        formContainerAggiungi.style.display = "none";
        toggleButtonAggiungi.textContent = "Aggiungi libreria";
    }
});

toggleButtonElimina.addEventListener('click', function() {
    if (formContainerElimina.style.display === "none") {
        formContainerElimina.style.display = "block";
        toggleButtonElimina.textContent = "Nascondi aggiungi libreria";
    } else {
        formContainerElimina.style.display = "none";
        toggleButtonElimina.textContent = "Aggiungi libreria";
    }
});
