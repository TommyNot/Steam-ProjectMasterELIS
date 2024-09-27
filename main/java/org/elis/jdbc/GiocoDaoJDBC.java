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
    public Gioco add(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo, Offerta offerta,Utente utente) {
        
        String query = "INSERT INTO gioco(nome, data_rilascio, descrizione, immagine, eliminato, prezzo, id_offerta,id_utente)"
                     + " VALUES(?, ?, ?, ?, ?, ?, ? ,?)";
        
        String quer2 = "SELECT id,Ruolo FROM utente WHERE id = ?";
        
        

        try (
        		
        		
        		
            Connection c = JdbcDaoFactory.getConnection();
        		
        	PreparedStatement selectUtente = c.prepareStatement(quer2);
            PreparedStatement inserimentoUtente = c.prepareStatement(query);
        		
        		
        		
        ) {
        	
        	   ResultSet ruoloInt = selectUtente.executeQuery();
        	   
               Ruolo[] ruoli = Ruolo.values();
               
               
             for(Ruolo r : ruoli) {
            	 
            	 //if()
             }
               
        	// Impostazione dei parametri
        	int rs = inserimentoUtente.executeUpdate();
        	inserimentoUtente.setString(1, nome);
        	inserimentoUtente.setTimestamp(2, Timestamp.valueOf(dataRilascio));
        	inserimentoUtente.setString(3, descrizione);
        	inserimentoUtente.setString(4, immagine);
        	inserimentoUtente.setBoolean(5, eliminato);
        	inserimentoUtente.setDouble(6, prezzo);
            if(offerta != null) {
            	
            	inserimentoUtente.setLong(7,offerta.getId());
            	
            }else {
            	
            	inserimentoUtente.setNull(7, Types.BIGINT);
            	
            }
  

            int aggiornamento = inserimentoUtente.executeUpdate();

            if (aggiornamento > 0) {
              
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
