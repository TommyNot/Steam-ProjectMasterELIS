document.addEventListener('DOMContentLoaded', function () {
    const generiLink = document.querySelector('.generi-link');
    const dropdown = document.querySelector('.dropdown');

    
    generiLink.addEventListener('mouseover', function () {
		
        dropdown.classList.add('show'); 
    });



    
    dropdown.addEventListener('mouseover', function () {
        dropdown.classList.add('show'); 
    });

    dropdown.addEventListener('mouseout', function () {
        dropdown.classList.remove('show'); 
    });
});
