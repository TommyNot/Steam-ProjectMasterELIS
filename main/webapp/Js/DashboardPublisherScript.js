function showSection(sectionId) {
    const sections = document.querySelectorAll('main section');
    sections.forEach(section => {
        section.classList.add('hidden');
        section.classList.remove('active');
    });

    const activeSection = document.getElementById(sectionId);
    if (activeSection) {
        activeSection.classList.remove('hidden');
        activeSection.classList.add('active');
    } else {
        console.error(`Section with ID ${sectionId} not found.`);
    }
}

function toggleFormVisibility(formId, isVisible) {
    const form = document.getElementById(formId);
    if (form) {
        form.style.display = isVisible ? "block" : "none";
    } else {
        console.error(`Form with ID ${formId} not found.`);
    }
}

document.getElementById('add-product-btn').addEventListener("click", function() {
    toggleFormVisibility('add-product-form', true);
});

document.getElementById('cancel-add-product').addEventListener("click", function() {
    toggleFormVisibility('add-product-form', false);
});

document.getElementById('remove-product-btn').addEventListener("click", function() {
    toggleFormVisibility('remove-product-form', true);
});

document.getElementById('cancel-remove-product').addEventListener("click", function() {
    toggleFormVisibility('remove-product-form', false);
});

document.getElementById('btn-modifica-gioco').addEventListener("click", function() {
    toggleFormVisibility('edit-product-form', true);
});

document.getElementById('cancel-edit-product').addEventListener("click", function() {
    toggleFormVisibility('edit-product-form', false);
});


// Funzione per filtrare prodotti in base al genere
document.getElementById("filtra").addEventListener("submit", function(event) {
    event.preventDefault();

    // Ottieni i dati dal form per filtrare i prodotti
    let filterValue = document.getElementById("filterInput").value.toLowerCase(); // Case insensitive filter

    // Logica per filtrare i prodotti visibili sulla pagina
    filtraProdotti(filterValue);
});

function filtraProdotti(filterValue) {
    const prodotti = document.querySelectorAll("#opt .prodotto"); // Assuming products have a class 'prodotto'

    prodotti.forEach(prodotto => {
        if (prodotto.innerText.toLowerCase().includes(filterValue)) {
            prodotto.style.display = "block"; // Show the product
        } else {
            prodotto.style.display = "none"; // Hide the product
        }
    });
}
