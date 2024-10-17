
  const generiLink = document.querySelector('.generi-link');
  const dropdown = document.querySelector('.dropdown');
  let dropdownTimeout;

  // Mostra il dropdown al passaggio del mouse
  generiLink.addEventListener('mouseover', function() {
    clearTimeout(dropdownTimeout); // Cancella eventuali timer di chiusura
    dropdown.style.display = 'block';
  });

  // Mantieni il dropdown aperto quando il mouse è sopra il dropdown
  dropdown.addEventListener('mouseover', function() {
    clearTimeout(dropdownTimeout); // Cancella il timer quando il mouse entra nel dropdown
    dropdown.style.display = 'block';
  });

  // Imposta un ritardo quando il mouse esce dal link "Generi"
  generiLink.addEventListener('mouseout', function() {
    dropdownTimeout = setTimeout(function() {
      dropdown.style.display = 'none';
    }, 300); // Ritardo di 300 ms
  });

  // Imposta un ritardo quando il mouse esce dal dropdown
  dropdown.addEventListener('mouseout', function() {
    dropdownTimeout = setTimeout(function() {
      dropdown.style.display = 'none';
    }, 300); // Ritardo di 300 ms
  });

