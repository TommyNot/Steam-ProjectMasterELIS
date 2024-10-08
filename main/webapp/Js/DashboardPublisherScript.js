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

console.log('${genere}');
console.log('${giochi}')



//Funzione per filtrare prodotti in base al genere

//quando non è submit no hidden product 

document.getElementById("filtra").addEventListener("submit", function(event){
    
    event.preventDefault();
    
    // Ottieni i dati dal form per filtrare i prodotti
    let filterValue = document.getElementById("filterInput").value;
    
    // Logica per filtrare i prodotti visibili sulla pagina
    // Puoi implementare una funzione che mostra/nasconde gli elementi in base al filtro
    filtraProdotti(filterValue);
    
    // Mantiene la pagina visibile e aggiorna i risultati
});

function filtraProdotti(filterValue) {
    let prodotti = document.getElementById("opt");
    
    for (let i = 0; i < prodotti.length; i++) {
        let prodotto = prodotti[i];
        if (prodotto.innerText.includes(filterValue)) {
            prodotto.style.display = "block"; // Mostra il prodotto
        } else {
            prodotto.style.display = "none"; // Nasconde il prodotto
        }
    }
}
