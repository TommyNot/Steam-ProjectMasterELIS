package org.elis.daoJpa;

import java.util.List;

import org.elis.model.Utente;

public interface JpaUtenteDao {
	
	List<Utente> getAll();
}

/*					<label for="offerta">Seleziona offerta:</label>	
<select id="offerta" name="offerta">
<%
    List<Offerta> offerte = BusinessLogic.offertaVisualizzaTutto();
    Offerta offertaSelezionata = (Offerta) request.getAttribute("offertaSelezionata"); 
    for (Offerta offerta : offerte) {
%>
    <option value="<%= offerta.getId() %>" <%= (offertaSelezionata != null && offertaSelezionata.getId() == offerta.getId()) ? "selected" : "" %>><%= offerta.getNome() %></option>
<%
    }
%>
</select>*/