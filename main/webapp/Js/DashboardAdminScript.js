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
	   
       const username = document.getElementById('username1').value;  
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
   
   document.getElementById('searchForm2').addEventListener('submit', function(event) { 
       event.preventDefault(); 
       const idUtente = document.getElementById('idUtente').value; 
       
       fetch('/SteamProject/UtenteFindByIDServlet', { 
           method: 'POST', 
           headers: { 
               'Content-Type': 'application/x-www-form-urlencoded' 
           }, 
           body: 'idUtente=' + encodeURIComponent(idUtente) 
       }) 
       .then(response => response.text()) 
       .then(data => { 
           document.getElementById('resultId').innerHTML = data; 
       }) 
       .catch(error => { 
           console.error('Errore:', error); 
           document.getElementById('resultId').innerHTML = '<p style="color:red;">Errore nella richiesta.</p>'; 
       }); 
   });
	  
	
	  
   document.getElementById('cercaGioco').addEventListener('submit', function(event) {
           event.preventDefault(); 
           console.log("Event listener attivato");

           const gameName = document.getElementById('nomeGioco').value; 
           console.log("Nome del gioco:", gameName);

           fetch('/SteamProject/AdminVisualizzaGiocoServlet', { 
               method: 'POST', 
               headers: {
                   'Content-Type': 'application/x-www-form-urlencoded'
               },
               body: 'name=' + encodeURIComponent(gameName)
           }) 
           .then(response => { 
               if (response.ok) { 
                   console.log("Risposta OK ricevuta");
                   const idGioco = response.headers.get('Game-ID'); 
                   localStorage.setItem('idGioco', idGioco); 
                   return response.blob(); 
               } else { 
                   throw new Error('Gioco non trovato'); 
               } 
           }) 
           .then(blob => { 
               const imgUrl = URL.createObjectURL(blob); 
               console.log("Immagine URL:", imgUrl);

               document.getElementById('resultGioco').innerHTML = `<h3>Gioco trovato:</h3>
                   <img src="${imgUrl}" alt="Immagine del Gioco" style="width: 200px; height: auto;">`;
               document.getElementById('deleteButton').innerHTML = `<button name='productId' onclick="deleteGame()">Elimina Gioco</button>`;
           }) 
           .catch(error => { 
               console.error('Errore:', error); 
               document.getElementById('resultGioco').innerHTML = '<p style="color:red;">Gioco non trovato o errore nel caricamento dell\'immagine.</p>'; 
               document.getElementById('deleteButton').innerHTML = ''; 
           }); 
       });

	   function deleteGame() {
		event.preventDefault();
	       const idGioco = localStorage.getItem('idGioco');
	       if (!idGioco) {
	           console.error('ID del gioco non trovato');
	           document.getElementById('resultGioco').innerHTML = '<p style="color:red;">ID del gioco non trovato.</p>';
	           return;
	       }

	       console.log("Eliminazione del gioco con ID:", idGioco);

	       fetch('/SteamProject/GiocoEliminaServlet', {
	           method: 'POST',
	           headers: {
	               'Content-Type': 'application/x-www-form-urlencoded'
	           },
	           body: 'productId=' + encodeURIComponent(idGioco)
	       })
	       .then(response => {
	           if (response.ok) {
	               return response.text().then(text => {
	                   document.getElementById('resultGioco').innerHTML = '<p style="color:green;">' + text + '</p>';
	                   document.getElementById('deleteButton').innerHTML = '';
	               });
	           } else {
	               return response.text().then(text => {
	                   document.getElementById('resultGioco').innerHTML = '<p style="color:red;">' + text + '</p>';
	                   console.error('Errore durante l\'eliminazione del gioco:', text);
	               });
	           }
	       })
	       .catch(error => {
	           console.error('Errore:', error);
	           document.getElementById('resultGioco').innerHTML = '<p style="color:red;">Errore nella richiesta di eliminazione.</p>';
	       });
	   }

	   document.getElementById('eliminaUtente').addEventListener('submit', function(event) {
	       event.preventDefault();

	       const formData = new FormData(this);
	       const data = new URLSearchParams(formData);

	       fetch('/SteamProject/AdminEliminaServlet', {
	           method: 'POST',
	           headers: {
	               'Content-Type': 'application/x-www-form-urlencoded'
	           },
	           body: data
	       })
		   .then(response => {
		           if (response.ok) {
		               return response.text().then(text => {
		                   document.getElementById('utenteResult').innerHTML = '<p style="color:green;">' + text + '</p>';
		               });
		           } else {
		               return response.text().then(text => {
		                   document.getElementById('utenteResult').innerHTML = '<p style="color:red;">' + text + '</p>';
		                   console.error('Errore durante l\'eliminazione dell\'account:', text);
		               });
		           }
		       })
		       .catch(error => {
		           console.error('Errore:', error);
		           document.getElementById('utenteResult').innerHTML = '<p style="color:red;">Errore nella richiesta di eliminazione.</p>';
		       });
	   });


