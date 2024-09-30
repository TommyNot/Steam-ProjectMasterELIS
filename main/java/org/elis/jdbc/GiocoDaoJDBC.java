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

import org.apache.naming.java.javaURLContextFactory;
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
    public Gioco add(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, double prezzo, List<Genere> generi, Offerta offerta, long idUtente) {

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
            inserimentoGioco.setDouble(5, prezzo);

            if (offerta != null) {
                inserimentoGioco.setLong(6, offerta.getId());
            } else {
                inserimentoGioco.setNull(6, Types.BIGINT);
            }

            inserimentoGioco.setLong(7, idUtente);

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
                nuovoGioco.setPrezzo(prezzo);
                nuovoGioco.setGeneri(generi);
                nuovoGioco.setOfferta(offerta);
                nuovoGioco.setIdUtente(idUtente);

                System.out.println("Gioco aggiunto con successo: " + nuovoGioco.getNome());
                return nuovoGioco;

            } catch (SQLException e) {
                // Gestione errori di duplicati stackoverflow
                if (e.getSQLState().equals("23000")) { // Stato SQL per mysql stackoverflow
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
		
		String query = "SELECT * FROM gioco WHERE nome = ?";
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				
				
			){
			
			ps.setString(1, nome);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				
		          Gioco g = new Gioco();
	                g.setNome(rs.getString("nome"));
	                g.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
	                g.setDescrzione(rs.getString("descrizione"));
	                g.setImmagine(rs.getString("immagine"));
	                g.setEliminato(rs.getBoolean("eliminato"));
	                g.setPrezzo(rs.getDouble("prezzo"));
	                
	               System.out.println("gioco trovato");
	                return g;
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Gioco updateGiocoNome(String nome) {
		
		String query = "UPDATE gioco SET nome = ? WHERE nome = ?";
		try(
				
			Connection c = JdbcDaoFactory.getConnection();
			PreparedStatement ps = c.prepareStatement(query);
			
				
		){
			
			//imposto parametri
			ps.setString(1,nome);
			
	     
	        int aggiornamento = ps.executeUpdate();
	        
	        if(aggiornamento > 0) {
	        	
	        	System.out.println("Gioco Aggiornato");
	        	
	        	Gioco giocoAggiornato = new Gioco();
	
	            giocoAggiornato.setNome(nome);
	            
	            return giocoAggiornato;
	        	
	        }else {
	        	
	        	System.out.println("Errore nell'aggiornamento");
	        }
			
			
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Gioco deleteGioco(String nome) {
	    String query = "DELETE FROM gioco WHERE nome = ?";
	    
	    try (
	        Connection c = JdbcDaoFactory.getConnection();
	        PreparedStatement ps = c.prepareStatement(query);
	    ) {
	        ps.setString(1, nome);
	        
	        int aggiornamento = ps.executeUpdate();
	        
	        if (aggiornamento == 0) {
	            System.out.println("Nessun gioco eliminato, ID non trovato");
	            
	        } else {
	            System.out.println("Successo: gioco eliminato");
	            
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    }catch(Exception e) {
	    	
	    	e.printStackTrace();
	    }
		return null;
	}


	@Override
	public Gioco findGiocoById(long id) {
	    
	    String query = "SELECT * FROM gioco WHERE id = ?";
	    
	    try (
	        Connection c = JdbcDaoFactory.getConnection();
	        PreparedStatement ps = c.prepareStatement(query);
	    ) {
	        
	        ps.setLong(1, id);
	        
	        
	        ResultSet rs = ps.executeQuery();
	        
	        
	        if (rs.next()) {
	            
	            Gioco gioco = new Gioco();
	            gioco.setId(rs.getLong("id"));
	            gioco.setNome(rs.getString("nome"));
	            gioco.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
	            gioco.setDescrzione(rs.getString("descrizione"));
	            gioco.setImmagine(rs.getString("immagine"));
	            gioco.setEliminato(rs.getBoolean("eliminato"));
	            gioco.setPrezzo(rs.getDouble("prezzo"));
	            
	            
	            long idOfferta = rs.getLong("id_offerta");
	            if (!rs.wasNull()) {
	                
	                Offerta offerta = findOffertaById(idOfferta); 
	                gioco.setOfferta(offerta);
	            }
	            
	            System.out.println("Gioco trovato: " + gioco.getNome());
	            return gioco;
	        } else {
	            System.out.println("Nessun gioco trovato con l'ID: " + id);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}


	@Override
	public Gioco updateGiocoDescrzione(long id,String descrzione) {
		
		String query = "UPDATE gioco SET descrizione = ? WHERE id = ?";
		
		try(
					Connection c = JdbcDaoFactory.getConnection();
					PreparedStatement ps = c.prepareStatement(query);
					
				
			){
			
			   ps.setString(1, descrzione); 
		       ps.setLong(2, id);
			
			int aggiornamento = ps.executeUpdate();
			
			if(aggiornamento > 0) {
				
				System.out.println("Descrzione success");
	            Gioco giocoAggiornato = findGiocoById(id);
	            return giocoAggiornato;
				
			}else {
				
				System.out.println("Oppss....error nell'aggiunta");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}


	@Override
	public Gioco updateGiocoImmagine(long id, String immagine) {
	    
	    String query = "UPDATE gioco SET immagine = ? WHERE id = ?";
	    
	    try (
	        Connection c = JdbcDaoFactory.getConnection();
	        PreparedStatement ps = c.prepareStatement(query);
	    ) {
	       
	        ps.setString(1, immagine);
	        ps.setLong(2, id);
	        
	        
	        int aggiornamento = ps.executeUpdate();
	        
	        if (aggiornamento > 0) {
	            System.out.println("Immagine aggiornata con successo.");
	            
	            
	            Gioco giocoAggiornato = findGiocoById(id);
	            return giocoAggiornato;
	            
	        } else {
	            System.out.println("Errore nell'aggiornamento dell'immagine.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}


	@Override
	public Gioco updateGiocoPrezzo(long id, double prezzo) {
		
		String query = "UPDATE gioco SET nome = ?, prezzo=? WHERE id = ?";
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				
			){
			
			ps.setDouble(1, prezzo);
			ps.setLong(2, id);
			
			int aggiornamento = ps.executeUpdate();
			
			if(aggiornamento > 0) {
				
				System.out.println("Success prezzo modificato");
			     Gioco giocoAggiornato = findGiocoById(id);
		         return giocoAggiornato;
			}else {
				
				System.out.println("Error modifica prezzo");
				
			}
			
			
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
		
	}


	@Override
	public Gioco updateGiocoOfferta(long id, Offerta offerta) {
	    String query = "UPDATE gioco SET id_offerta = ? WHERE id = ?";
	    
	    try (Connection c = JdbcDaoFactory.getConnection(); 
	         PreparedStatement ps = c.prepareStatement(query)) {

	        if (offerta != null) {
	            ps.setLong(1, offerta.getId());
	        } else {
	            ps.setNull(1, Types.BIGINT); 
	        }
	        ps.setLong(2, id);
	        
	        int aggiornamento = ps.executeUpdate();
	        
	        if (aggiornamento > 0) {
	            System.out.println("Offerta aggiornata con successo.");
	            
	            return findGiocoById(id); 
	        } else {
	            System.out.println("Errore nell'offerta: ID non trovato.");
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null; 
	}


	@Override
	public Gioco updateGiocoGenere(long id, Genere genere) {
	    String queryUpdateGenere = 
	        "UPDATE genere_gioco SET id_genere = ? WHERE id_gioco = ?";

	    String querySelectGioco = 
	        "SELECT g.*, u.Ruolo FROM gioco g " +
	        "JOIN utente u ON g.id_utente = u.id WHERE g.id = ?";

	    try (
	    	 Connection c = JdbcDaoFactory.getConnection();
	         PreparedStatement updateGenere = c.prepareStatement(queryUpdateGenere);
	         PreparedStatement selectGioco = c.prepareStatement(querySelectGioco)
	        ) {

	        
	        updateGenere.setLong(1, genere.getId());
	        updateGenere.setLong(2, id);
	        int rowsAffected = updateGenere.executeUpdate();

	        if (rowsAffected == 0) {
	            System.out.println("Nessun genere aggiornato, il gioco non esiste.");
	            return null; 
	        }

	        
	        selectGioco.setLong(1, id);
	        ResultSet rs = selectGioco.executeQuery();

	        if (!rs.next()) {
	            System.out.println("Il gioco non è stato trovato dopo l'aggiornamento.");
	            return null; 
	        }

	        
	        Gioco giocoAggiornato = new Gioco();
	        giocoAggiornato.setId(id);
	        giocoAggiornato.setNome(rs.getString("nome"));
	        giocoAggiornato.setData_rilascio(rs.getTimestamp("data_rilascio").toLocalDateTime());
	        giocoAggiornato.setDescrzione(rs.getString("descrizione"));
	        giocoAggiornato.setImmagine(rs.getString("immagine"));
	        giocoAggiornato.setEliminato(rs.getBoolean("eliminato"));
	        giocoAggiornato.setPrezzo(rs.getDouble("prezzo"));

	       
	        List<Genere> generi = new ArrayList<>();
	        generi.add(genere);
	        giocoAggiornato.setGeneri(generi);

	        System.out.println("Genere aggiornato per il gioco con ID " + id);
	        return giocoAggiornato;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null; 
	}






}
