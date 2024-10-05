const toggleButton = document.getElementById('toggle-btn');
const sidebar = document.getElementById('sidebar');
const dropdownButtons = document.querySelectorAll('.dropdown-btn');


function toggleSidebar() {
    sidebar.classList.toggle('close'); 
    toggleButton.classList.toggle('rotate'); 


    if (sidebar.classList.contains('close')) {
        closeAllSubmenu(); 
    }
}


dropdownButtons.forEach(button => {
    button.addEventListener('click', function () {
        toggleSubMenu(this); 
    });
});


function toggleSubMenu(button) {
    const subMenu = button.nextElementSibling;

    if (!subMenu.classList.contains('show')) {
        closeAllSubmenu();
    }

    subMenu.classList.toggle('show');
    button.classList.toggle('rotate');
	
	if(sidebar.classList.contains('close')){
	        sidebar.classList.toggle('close')
	        toggleButton.classList.toggle('rotate')
	    }
}


function closeAllSubmenu(){
    Array.from(sidebar.getElementsByClassName('show')).forEach(ul=>{
        ul.classList.remove('show')
        ul.previousElementSibling.classList.remove('rotate')
    })
}

