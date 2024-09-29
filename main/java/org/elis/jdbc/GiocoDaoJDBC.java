package org.elis.jdbc;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
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
    public Gioco add(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo, List<Genere> generi, Offerta offerta, long idUtente) {

        String queryInsertGioco = "INSERT INTO gioco(nome, data_rilascio, descrizione, immagine, eliminato, prezzo,id_offerta, id_casa_editrice)"
                                 + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        String querySelectUtente = "SELECT id, ruolo FROM utente WHERE id = ?";
        
        
        String queryInsertGenereGioco = "INSERT INTO genere_gioco(id_genere, id_gioco) VALUES(?, ?)";
        
        try (
        		
            Connection c = JdbcDaoFactory.getConnection();
            
            //preparo query per trovare utenteb publisher
            PreparedStatement selectUtente = c.prepareStatement(querySelectUtente);
            
            //preparo query inserimento del gioco
        		PreparedStatement inserimentoGioco = c.prepareStatement(queryInsertGioco, Statement.RETURN_GENERATED_KEYS);
            
            //query per inserimento genere_gioco
            PreparedStatement inserimentoGenereGioco = c.prepareStatement(queryInsertGenereGioco);
            
        ) {
        	
          
            
        	selectUtente.setLong(1, idUtente);
        	
        	//prendo rs e attivo query utente
        	ResultSet resultSetUtente = selectUtente.executeQuery();
        	
        	
            
            //controllo utente
            boolean userIsPublisher = false;

            if (resultSetUtente.next()) {
            	//prendo ruolo
                int ruoloInt = resultSetUtente.getInt("ruolo");
                Ruolo[] ruoli = Ruolo.values();
                userIsPublisher = ruoli[ruoloInt] == Ruolo.PUBLISHER;
            }

           //se utente è falso tonra indietro
            if (!userIsPublisher) {
                return null;
            }

           //setto paramatri del gioco
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

           inserimentoGioco.setLong(8, idUtente);
            

            //faccio update e controllo row restituite
            int aggiornamento = inserimentoGioco.executeUpdate();
            
        

            if (aggiornamento > 0) {
            	
            	//dopo update facciamo insiemrnto genere iteriamo con for per capire quale genere è ststao associato e poi lo inseriamo in genere_gioco
            	ResultSet recuperIdGioco = inserimentoGioco.getGeneratedKeys();
            	if(recuperIdGioco.next()) {
            		
            		long giocoId = recuperIdGioco.getLong(1);
            		
            		for(Genere g : generi) {
            			
            			inserimentoGenereGioco.setLong(1, g.getId());
            			inserimentoGenereGioco.setLong(2, giocoId);
            			inserimentoGenereGioco.executeUpdate();
            		}
            	}
            	
            }
            
            
               
                Gioco nuovoGioco = new Gioco();
                nuovoGioco.setNome(nome);
                nuovoGioco.setData_rilascio(dataRilascio);
                nuovoGioco.setDescrzione(descrizione);
                nuovoGioco.setImmagine(immagine);
                nuovoGioco.setEliminato(eliminato);
                nuovoGioco.setPrezzo(prezzo);
                nuovoGioco.setGeneri(generi);
                nuovoGioco.setOfferta(offerta);
                nuovoGioco.setIdUtente(idUtente);

                System.out.println("Gioco aggiunto con successo: " + nuovoGioco.getNome());
                return nuovoGioco;
        
            
        
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
