document.addEventListener("DOMContentLoaded", function() {
    
    const removeButton = document.getElementById('remove-product-btn');
    const removeForm = document.getElementById('remove-product-form');
    const cancelButton = document.getElementById('cancel-remove-product');

    
    if (removeButton) {
        removeButton.addEventListener("click", function() {
            
            removeForm.style.display = "block";
        });
    } else {
        console.error("Il pulsante 'remove-product-btn' non è stato trovato");
    }

    
    if (cancelButton) {
        cancelButton.addEventListener("click", function() {
            
            removeForm.style.display = 'none';
        });
    } else {
        console.error("Il pulsante 'cancel-add-product' non è stato trovato");
    }
});
