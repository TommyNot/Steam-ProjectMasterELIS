
let arrayImmagini =["../risorse-media/img_giochi/Cyberpunk_2077.jpg",  
"../risorse-media/img_giochi/Doom_Eternal.jpg",
"../risorse-media/img_giochi/Elden_Ring.jpg",
"../risorse-media/img_giochi/Red_Dead_Redemption_II.jpg"];

let counter = 0;

$(document).ready(function(){
	$("#input-ricerca").on("click", function(){
		$(this).css("text-decoration", "underline");
	})
	
	$("#lente-ingrandimento").on("click", function() {
	      $("#input-ricerca").toggleClass("nascondi-input-ricerca"); // Alterna la visualizzazione della barra di ricerca
	  });
	
	  immaginePrima(event);
	  immagineDopo(event);
})

function immaginePrima(event){
	event.preventDefault();
	
	let immagine = document.getElementById("immagine");
	if(counter == 0){
	   counter = 10;
	 }
	counter--;
	immagine.src = arrayImmagini[counter];
}

function immagineDopo(event){
	event.preventDefault();

	let immagine = document.getElementById("immagine");
	if(counter == arrayImmagini.length - 1){
	    counter = -1;
	    }
	counter++;
	immagine.src = arrayImmagini[counter];
}