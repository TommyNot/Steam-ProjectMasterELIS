package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.GiocoDao;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

public class GiocoDaoJDBC implements GiocoDao{
	
    private static GiocoDaoJDBC instance;
    
    
    private GiocoDaoJDBC() {}
    
   
    public static GiocoDaoJDBC getInstance() {
        if (instance == null) {
            instance = new GiocoDaoJDBC();
        }
        return instance;
    }


    @Override
    public Gioco add(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo, Offerta offerta) {
        
        String query = "INSERT INTO gioco(nome, data_rilascio, descrizione, immagine, eliminato, prezzo, id_offerta)"
                     + " VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
        ) {
            // Impostazione dei parametri
            ps.setString(1, nome);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(dataRilascio));
            ps.setString(3, descrizione);
            ps.setString(4, immagine);
            ps.setBoolean(5, eliminato);
            ps.setDouble(6, prezzo);
            ps.setFloat(7, offerta != null ? offerta.getId() : null);

            int aggiornamento = ps.executeUpdate();

            if (aggiornamento > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1); 

                        
                        Gioco nuovoGioco = new Gioco();
                        nuovoGioco.setId(id);
                        nuovoGioco.setNome(nome);
                        nuovoGioco.setData_rilascio(dataRilascio);
                        nuovoGioco.setDescrzione(descrizione);
                        nuovoGioco.setImmagine(immagine);
                        nuovoGioco.setEliminato(eliminato);
                        nuovoGioco.setPrezzo(prezzo);
                        nuovoGioco.setOfferta(offerta);

                        System.out.println("Gioco aggiunto con successo: " + nuovoGioco.getNome());
                        return nuovoGioco;
                    }
                }
            } else {
                System.out.println("Errore: Gioco non aggiunto.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


	@Override
	public List<Gioco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGioco(String nome,LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo,Offerta offerta, Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco deleteGioco(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
