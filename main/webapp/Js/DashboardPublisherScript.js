function showSection(sectionId) {
    
    const sections = document.querySelectorAll('main section');
    sections.forEach(section => {
        section.classList.add('hidden');
        section.classList.remove('active');
    });

    
    const activeSection = document.getElementById(sectionId);
    activeSection.classList.remove('hidden');
    activeSection.classList.add('active');
}

document.getElementById('add-product-btn').addEventListener("click",function(){
	
	document.getElementById('add-product-form').style.display = "block";
})

document.getElementById('cancel-add-product').addEventListener("click",function(){
	
	document.getElementById('add-product-form').style.display = 'none';
})

document.getElementById('remove-product-btn').addEventListener("click", function() {
    document.getElementById('remove-product-form').style.display = "block";
});


document.getElementById('cancel-remove-product').addEventListener("click", function() {
    document.getElementById('remove-product-form').style.display = 'none';
});
