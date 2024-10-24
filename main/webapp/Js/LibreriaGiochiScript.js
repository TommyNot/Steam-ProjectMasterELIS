/**
 * 
 */


const toggleButtonAggiungi = document.getElementById('toggle-form-aggiungi-btn');
const formContainerAggiungi = document.getElementById('form-container-aggiungi');

const toggleButtonModifica = document.getElementById('toggle-form-modifica-btn');
const formContainerModifica = document.getElementById('form-container-modifica');


toggleButtonAggiungi.addEventListener('click', function() {
    if (formContainerAggiungi.style.display === "none") {
        formContainerAggiungi.style.display = "block";
        toggleButtonAggiungi.textContent = "Nascondi aggiungi libreria";
    } else {
        formContainerAggiungi.style.display = "none";
        toggleButtonAggiungi.textContent = "Aggiungi libreria";
    }
});

toggleButtonModifica.addEventListener('click', function() {
    if (formContainerModifica.style.display === "none") {
        formContainerModifica.style.display = "block";
        toggleButtonModifica.textContent = "Nascondi modifica nome libreria";
    } else {
        formContainerModifica.style.display = "none";
        toggleButtonModifica.textContent = "Modifica nome libreria";
    }
});


const profilePictures = ["https://i.pinimg.com/enabled/564x/ce/4d/71/ce4d7190e7f159852de3fa2965a1a4b3.jpg", "https://i.pinimg.com/enabled/564x/15/ac/eb/15aceb5eee4e67f81a155bed0f6d09ad.jpg",
"https://i.pinimg.com/originals/30/ca/8e/30ca8ed3c149dffe00475f1b96f18ab4.jpg", "https://i.pinimg.com/enabled/564x/c0/44/ef/c044ef6f58e5ffe3874048c8eef04afb.jpg",
 "https://i.pinimg.com/736x/27/b0/17/27b017d9e1080c345bbca42ddf0cce8c.jpg", "https://i.pinimg.com/564x/69/50/44/69504422389cec472578ac8dbbd427f8.jpg",
 "https://i.pinimg.com/enabled/564x/b4/48/55/b44855b95b9c1a1edf613f422e410172.jpg","https://i.pinimg.com/736x/fe/7d/af/fe7daf3685750114230bfe3dd4ef8def.jpg", 
 "https://i.pinimg.com/enabled/564x/cf/7f/e9/cf7fe9d8717c783261ab53906a472a04.jpg"];

function getRandomAvatar() {
      const randomIndex = Math.floor(Math.random() * profilePictures.length);
      return profilePictures[randomIndex];
}

    window.onload = function() {
      const avatarElement = document.getElementById('avatar');
      avatarElement.src = getRandomAvatar();
}

	
	  
	  
	  
	  
	  
