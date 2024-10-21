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
		   fetch('/steamproject/TrovaTuttiUtentiServlet')
		      .then(response => response.text())
		      .then(data => {
		        document.getElementById('Visualizza').innerHTML = data;
		      });
};




document.getElementById('searchForm').addEventListener('submit', function(event) {
       event.preventDefault();
	   
       const username = document.getElementById('username1').value;  
       fetch('/steamproject/UtenteFindByNameServlet', {
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

	   document.getElementById('creaOfferta').addEventListener('submit', function(event) {
	   	       event.preventDefault();

	   	       const formData = new FormData(this);
	   	       const data = new URLSearchParams(formData);

	   	       fetch('/SteamProject/OffertaAggiungiServlet', {
	   	           method: 'POST',
	   	           headers: {
	   	               'Content-Type': 'application/x-www-form-urlencoded'
	   	           },
	   	           body: data
	   	       })
	   		   .then(response => {
	   		           if (response.ok) {
	   		               return response.text().then(text => {
	   		                   document.getElementById('resultCreaOfferta').innerHTML = '<p style="color:green;">' + text + '</p>';
	   		               });
	   		           } else {
	   		               return response.text().then(text => {
	   		                   document.getElementById('resultCreaOfferta').innerHTML = '<p style="color:red;">' + text + '</p>';
	   		                   console.error('Errore durante la creazione dell\'offerta:', text);
	   		               });
	   		           }
	   		       })
	   		       .catch(error => {
	   		           console.error('Errore:', error);
	   		           document.getElementById('resultCreaOfferta').innerHTML = '<p style="color:red;">Errore nella richiesta di creazione.</p>';
	   		       });
	   	   });

		   document.getElementById('aggiornaInizio').addEventListener('submit', function(event) {
		       event.preventDefault();

		       const formData = new FormData(this);
		       const data = new URLSearchParams(formData);

		       fetch('/SteamProject/OffertaAggiornaDataInizioServlet', {
		           method: 'POST',
		           headers: {
		               'Content-Type': 'application/x-www-form-urlencoded'
		           },
		           body: data
		       })
		       .then(response => {
		           if (response.ok) {
		               return response.text().then(text => {
		                   document.getElementById('resultInizio').innerHTML = '<p class="success">' + text + '</p>';
		               });
		           } else {
		               return response.text().then(text => {
		                   document.getElementById('resultInizio').innerHTML = '<p class="error">' + text + '</p>';
		               });
		           }
		       })
		       .catch(error => {
		           console.error('Errore:', error);
		           document.getElementById('resultInizio').innerHTML = '<p class="error">Errore nell\'aggiornamento della data.</p>';
		       });
		   });
		   
		   document.getElementById('aggiornaFine').addEventListener('submit', function(event) {
		   	       event.preventDefault();

		   	       const formData = new FormData(this);
		   	       const data = new URLSearchParams(formData);

		   	       fetch('/SteamProject/OffertaAggiornaDataFineServlet', {
		   	           method: 'POST',
		   	           headers: {
		   	               'Content-Type': 'application/x-www-form-urlencoded'
		   	           },
		   	           body: data
		   	       })
		   	       .then(response => {
		   	           if (response.ok) {
		   	               return response.text().then(text => {
		   	                   document.getElementById('resultFine').innerHTML = '<p class="success">' + text + '</p>';
		   	               });
		   	           } else {
		   	               return response.text().then(text => {
		   	                   document.getElementById('resultFine').innerHTML = '<p class="error">' + text + '</p>';
		   	               });
		   	           }
		   	       })
		   	       .catch(error => {
		   	           console.error('Errore:', error);
		   	           document.getElementById('resultFine').innerHTML = '<p class="error">Errore nell\'aggiornamento della data.</p>';
		   	       });
		   	   });
			   
			   document.getElementById('aggiornaSconto').addEventListener('submit', function(event) {
			   		   	       event.preventDefault();

			   		   	       const formData = new FormData(this);
			   		   	       const data = new URLSearchParams(formData);

			   		   	       fetch('/SteamProject/OffertaAggiornaScontoServlet', {
			   		   	           method: 'POST',
			   		   	           headers: {
			   		   	               'Content-Type': 'application/x-www-form-urlencoded'
			   		   	           },
			   		   	           body: data
			   		   	       })
			   		   	       .then(response => {
			   		   	           if (response.ok) {
			   		   	               return response.text().then(text => {
			   		   	                   document.getElementById('resultSconto').innerHTML = '<p class="success">' + text + '</p>';
			   		   	               });
			   		   	           } else {
			   		   	               return response.text().then(text => {
			   		   	                   document.getElementById('resultSconto').innerHTML = '<p class="error">' + text + '</p>';
			   		   	               });
			   		   	           }
			   		   	       })
			   		   	       .catch(error => {
			   		   	           console.error('Errore:', error);
			   		   	           document.getElementById('resultSconto').innerHTML = '<p class="error">Errore nell\'aggiornamento della data.</p>';
			   		   	       });
			   		   	   });
						   
						   document.getElementById('eliminaOfferta').addEventListener('submit', function(event) {
						       event.preventDefault();

						       const selectElement = document.getElementById('offerta3');
						       const id = selectElement.value;
						       const nome = selectElement.options[selectElement.selectedIndex].text;
							
							   console.log("Nome selezionato: " + nome);
							   console.log("ID selezionato: " + id);
							       
							   
						       const formData = new FormData();
							   formData.append('nome', nome);
						       formData.append('id', id);
						       

						       fetch('/SteamProject/OffertaEliminaServlet', {
						           method: 'POST',
						           headers: {
						               'Content-Type': 'application/x-www-form-urlencoded'
						           },
						           body: new URLSearchParams(formData)
						       })
						       .then(response => {
						           if (response.ok) {
						               return response.text().then(text => {
						                   document.getElementById('resultEliminazione').innerHTML = '<p class="success">' + text + '</p>';
						               });
						           } else {
						               return response.text().then(text => {
						                   document.getElementById('resultEliminazione').innerHTML = '<p class="error">' + text + '</p>';
						               });
						           }
						       })
						       .catch(error => {
						           console.error('Errore:', error);
						           document.getElementById('resultEliminazione').innerHTML = '<p class="error">Errore durante l\'eliminazione dell\'offerta.</p>';
						       });
						   });
						   
						   
						   
						   
						   
						   
						   document.addEventListener('DOMContentLoaded', function() {
						       var ctx = document.getElementById('myChart').getContext('2d');
						       var myChart = new Chart(ctx, {
						           type: 'bar',
						           data: {
						               labels: ['Utenti Registrati', 'Giochi Disponibili'],
						               datasets: [{
						                   label: 'Utenti Registrati',
						                   data: [utentiCount, 0],
						                   backgroundColor: 'rgba(75, 192, 192, 0.2)',
						                   borderColor: 'rgba(75, 192, 192, 1)',
						                   borderWidth: 1
						               },
						               {
						                   label: 'Giochi Disponibili',
						                   data: [0, giochiCount],
						                   backgroundColor: 'rgba(153, 102, 255, 0.2)',
						                   borderColor: 'rgba(153, 102, 255, 1)',
						                   borderWidth: 1
						               }]
						           },
						           options: {
						               scales: {
						                   y: {
						                       beginAtZero: true
						                   }
						               },
						               maintainAspectRatio: false
						           }
						       });
						   });
						   
						   
						   document.getElementById('associaGeneriOfferta').addEventListener('submit', function(event) {
						       event.preventDefault();
						       console.log("Entrato nel metodo");

						       const offertaId = document.getElementById('offerta5').value;
						       console.log("Offerta selezionata ID:", offertaId);

						       const checkedGenres = document.querySelectorAll('input[name="idGenere"]:checked');
						       console.log("Generi selezionati:", checkedGenres);

						       const genereIds = Array.from(checkedGenres).map(checkbox => checkbox.value);
						       console.log("ID dei generi selezionati:", genereIds);

						       const formData = new URLSearchParams();
						       formData.append('idOfferta', offertaId);
						       genereIds.forEach(id => formData.append('idGenere', id));
						       console.log("Dati del form:", formData.toString());

						       fetch('/SteamProject/GenereOffertaAddServlet', {
						           method: 'POST',
						           headers: {
						               'Content-Type': 'application/x-www-form-urlencoded'
						           },
						           body: formData
						       })
						       .then(response => {
						           console.log("Risposta dal server:", response);
						           if (response.ok) {
						               return response.text().then(text => {
						                   console.log("Messaggio di successo:", text);
						                   document.getElementById('resultAssociazione').innerHTML = '<p class="success">' + text + '</p>';
						               });
						           } else {
						               return response.text().then(text => {
						                   console.error("Messaggio di errore:", text);
						                   document.getElementById('resultAssociazione').innerHTML = '<p class="error">' + text + '</p>';
						               });
						           }
						       })
						       .catch(error => {
						           console.error('Errore:', error);
						           document.getElementById('resultAssociazione').innerHTML = '<p class="error">Errore durante l\'associazione dei generi all\'offerta.</p>';
						       });
						   });
