package org.elis.jdbc;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.dao.GiocoDao;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Ruolo;
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
    public Gioco add(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo, List<Genere> generi, Offerta offerta, Utente utente) {

        String queryInsertGioco = "INSERT INTO gioco(nome, data_rilascio, descrizione, immagine, eliminato, prezzo, id_offerta, id_utente)"
                                 + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        String querySelectUtente = "SELECT id, ruolo FROM utente WHERE id = ?";
        
        String queryInsertGenereGioco = "INSERT INTO genere_gioco(id_genere, id_gioco) VALUES(?, ?)";
        
        try (
            Connection c = JdbcDaoFactory.getConnection();
            
            
            PreparedStatement selectUtente = c.prepareStatement(querySelectUtente);
            
            
            PreparedStatement inserimentoGioco = c.prepareStatement(queryInsertGioco);
            
            
            PreparedStatement inserimentoGenereGioco = c.prepareStatement(queryInsertGenereGioco);
            
        ) {
            
            
            
        	ResultSet resultSetUtente = selectUtente.executeQuery();
            
            
            boolean userIsPublisher = false;

            if (resultSetUtente.next()) {
                int ruoloInt = resultSetUtente.getInt("ruolo");
                Ruolo[] ruoli = Ruolo.values();
                userIsPublisher = ruoli[ruoloInt] == Ruolo.PUBLISHER;
            }

           
            if (!userIsPublisher) {
                return null;
            }

           
            inserimentoGioco.setString(1, nome);
            inserimentoGioco.setTimestamp(2, Timestamp.valueOf(dataRilascio));
            inserimentoGioco.setString(3, descrizione);
            inserimentoGioco.setString(4, immagine);
            inserimentoGioco.setBoolean(5, eliminato);
            inserimentoGioco.setDouble(6, prezzo);
            
            
            if (offerta != null) {
            	
                inserimentoGioco.setLong(7, offerta.getId());
            } else {
                inserimentoGioco.setNull(7, Types.BIGINT);
            }

           
            

            
            int aggiornamento = inserimentoGioco.executeUpdate();
            
        

            if (aggiornamento > 0) {
            	
            	ResultSet rs = inserimentoGioco.getGeneratedKeys();
            	
            	if(rs.next()) {
            		
            		int insID= rs.getInt("ID");
            		selectUtente.setLong(1, insID);
            	}
            	
            	
               
                Gioco nuovoGioco = new Gioco();
                nuovoGioco.setNome(nome);
                nuovoGioco.setData_rilascio(dataRilascio);
                nuovoGioco.setDescrzione(descrizione);
                nuovoGioco.setImmagine(immagine);
                nuovoGioco.setEliminato(eliminato);
                nuovoGioco.setPrezzo(prezzo);
                
                nuovoGioco.setOfferta(offerta);
                nuovoGioco.setUtente(utente);

                System.out.println("Gioco aggiunto con successo: " + nuovoGioco.getNome());
                return nuovoGioco;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
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
