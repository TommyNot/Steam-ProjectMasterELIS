
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

  const generiLinkGiochi = document.querySelector('.generi-link-giochi');
    const dropdownGiochi = document.querySelector('.dropdown-giochi');
    let dropdownTimeoutGiochi;

    // Mostra il dropdown al passaggio del mouse
    generiLinkGiochi.addEventListener('mouseover', function() {
      clearTimeout(dropdownTimeoutGiochi); // Cancella eventuali timer di chiusura
      dropdownGiochi.style.display = 'block';
    });

    // Mantieni il dropdown aperto quando il mouse è sopra il dropdown
    dropdownGiochi.addEventListener('mouseover', function() {
      clearTimeout(dropdownTimeoutGiochi); // Cancella il timer quando il mouse entra nel dropdown
      dropdownGiochi.style.display = 'block';
    });

    // Imposta un ritardo quando il mouse esce dal link "Generi"
    generiLinkGiochi.addEventListener('mouseout', function() {
      dropdownTimeoutGiochi = setTimeout(function() {
        dropdownGiochi.style.display = 'none';
      }, 300); // Ritardo di 300 ms
    });

    // Imposta un ritardo quando il mouse esce dal dropdown
    dropdownGiochi.addEventListener('mouseout', function() {
      dropdownTimeoutGiochi = setTimeout(function() {
        dropdownGiochi.style.display = 'none';
      }, 300); // Ritardo di 300 ms
    });

