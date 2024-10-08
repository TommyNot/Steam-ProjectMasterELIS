document.querySelector('.next').addEventListener('click', function () {
    let slider = document.querySelector('.slider');
    slider.appendChild(slider.firstElementChild);
});

document.querySelector('.prev').addEventListener('click', function () {
    let slider = document.querySelector('.slider');
    slider.insertBefore(slider.lastElementChild, slider.firstElementChild);
});

$(document).ready(function(){
        $('.slider').slick({
            dots: true,           // Mostra i punti sotto lo slider
            infinite: true,      // Scorrimento infinito
            speed: 500,          // Velocità della transizione
            slidesToShow: 3,     // Numero di immagini da mostrare
            slidesToScroll: 10,   // Numero di immagini da scorrere
            autoplay: true,      // Abilita autoplay
            autoplaySpeed: 2000, // Velocità dell'autoplay
            arrows: true         // Mostra le frecce di navigazione
        });
    });