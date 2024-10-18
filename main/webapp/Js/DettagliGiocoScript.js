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

	document.getElementById('showAddFormBtn').addEventListener('click', function() {
	    var form = document.getElementById('addForm');
	    if (form.style.display === "none") {
	        form.style.display = "block";
	    } else {
	        form.style.display = "none";
	    }
	});