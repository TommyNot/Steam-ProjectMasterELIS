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
import java.util.ArrayList;
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
            PreparedStatement selectUtente = c.prepareStatement(querySelectUtente);
            PreparedStatement inserimentoGioco = c.prepareStatement(queryInsertGioco, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement inserimentoGenereGioco = c.prepareStatement(queryInsertGenereGioco);
        ) {
            
            // Verifica utente publisher
            selectUtente.setLong(1, idUtente);
            ResultSet resultSetUtente = selectUtente.executeQuery();

            boolean userIsPublisher = false;
            if (resultSetUtente.next()) {
                int ruoloInt = resultSetUtente.getInt("ruolo");
                Ruolo[] ruoli = Ruolo.values();
                userIsPublisher = ruoli[ruoloInt] == Ruolo.PUBLISHER;
            }

            if (!userIsPublisher) {
                System.out.println("L'utente non è un publisher.");
                return null;
            }

            // Inserimento gioco
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

            try {
                int aggiornamento = inserimentoGioco.executeUpdate();

                if (aggiornamento > 0) {
                    ResultSet recuperIdGioco = inserimentoGioco.getGeneratedKeys();
                    
                    if (recuperIdGioco.next()) {
                        long giocoId = recuperIdGioco.getLong(1);

                        for (Genere g : generi) {
                            inserimentoGenereGioco.setLong(1, g.getId());
                            inserimentoGenereGioco.setLong(2, giocoId);
                            inserimentoGenereGioco.executeUpdate();
                        }
                    }
                }

                // Creazione Gioco
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
                // Gestione errori di duplicati stackoverflow
                if (e.getSQLState().equals("23505")) { // Stato SQL per mysql stackoverflow
                    System.out.println("Errore: il gioco con questo nome esiste già.");
                } else {
                    System.out.println("Errore SQL durante l'inserimento del gioco: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
        	
            System.out.println("Errore di connessione al database: " + e.getMessage());
            
            e.printStackTrace();
        } catch (Exception e) {
        	
            System.out.println("Errore generico: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }


	@Override
	public List<Gioco> findAll() {
		String query = "SELECT * FROM GIOCO";
		 List<Gioco> giochi = new ArrayList<>();
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				ResultSet rs = ps.executeQuery();
				
			){
			
			
			
			while(rs.next()){
				
				Gioco g = new Gioco();
				String nome = rs.getString("nome");
				Timestamp dataRilascio = rs.getTimestamp("data_rilascio");
				String descrzione = rs.getString("descrizione");
				String immagine = rs.getString("immagine");
				boolean eliminato = rs.getBoolean("eliminato");
				double prezzo = rs.getDouble("prezzo");
				
				g.setNome(nome);
				g.setData_rilascio(dataRilascio.toLocalDateTime());
				g.setDescrzione(descrzione);
				g.setImmagine(immagine);
				g.setEliminato(eliminato);
				g.setPrezzo(prezzo);
				
				
				giochi.add(g);

			}
			
			
		}catch(SQLException e) {
			
			System.out.println("Errore durante il recupero dei giochi: " + e.getMessage());
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return giochi;
		
		
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
