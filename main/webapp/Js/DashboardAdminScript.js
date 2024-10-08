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

function showSection(sectionId) {
            const containers = document.querySelectorAll('main div.container');
            containers.forEach(container => {
                container.classList.add('hidden');
                container.classList.remove('active');
            });

            const activeContainer = document.getElementById(sectionId);
            activeContainer.classList.remove('hidden'); 
            activeContainer.classList.add('active'); 
        }
		
 window.onload = function() {
		   fetch('/SteamProject/TrovaTuttiUtentiServlet')
		      .then(response => response.text())
		      .then(data => {
		        document.getElementById('Visualizza').innerHTML = data;
		      });
};

document.getElementById('searchForm').addEventListener('submit', function(event) {
       event.preventDefault();
	   
       const username = document.getElementById('username').value;  
       fetch('/SteamProject/UtenteFindByNameServlet', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/x-www-form-urlencoded'
           },
           body: 'username=' + encodeURIComponent(username)
       })
       .then(response => response.text())
       .then(data => {
           document.getElementById('result').innerHTML = data;
       })
       .catch(error => {
           console.error('Errore:', error);
           document.getElementById('result').innerHTML = '<p style="color:red;">Errore nella richiesta.</p>';
      });
   });