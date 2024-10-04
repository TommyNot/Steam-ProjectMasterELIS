/**
 * 
 */


    document.addEventListener('DOMContentLoaded', function() {
        const links = document.querySelectorAll('.nav-link');
        
        links.forEach(link => {
            link.addEventListener('click', function(event) {
                event.preventDefault(); // Prevenire il comportamento predefinito
                
                // Nascondi tutte le tab-content
                document.querySelectorAll('.tab-content').forEach(content => {
                    content.style.display = 'none';
                });

                // Mostra il contenuto corrispondente
                const targetId = this.getAttribute('data-target');
                document.getElementById(targetId).style.display = 'block';
            });
        });
    });
