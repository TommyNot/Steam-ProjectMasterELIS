/**
 * 
 */


    document.getElementById("showReviewFormBtn").addEventListener("click", function() {
        var reviewForm = document.getElementById("reviewForm");
        // Toglie la visibilità del form se è già aperto
        if (reviewForm.style.display === "none" || reviewForm.style.display === "") {
            reviewForm.style.display = "block"; // Mostra il form
        } else {
            reviewForm.style.display = "none"; // Nascondi il form
        }
    });

	showAddFormBtn.addEventListener("click", function() {
	    addForm.classList.toggle("show");
	});

	
	function toggleEditForm(recensioneId) {
	    const form = document.getElementById('editForm_' + recensioneId);
	    if (form.style.display === "none") {
	        form.style.display = "block";
	    } else {
	        form.style.display = "none";
	    }
	}