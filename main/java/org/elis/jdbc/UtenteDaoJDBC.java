package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.UtenteDao;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

public class UtenteDaoJDBC implements UtenteDao {
    
    
    private static UtenteDaoJDBC instance;
    
   
    private UtenteDaoJDBC() {}
    
   
    public static UtenteDaoJDBC getInstance() {
        if (instance == null) {
            instance = new UtenteDaoJDBC();
        }
        return instance;
    }

    @Override
    public List<Utente> findAll() {
        List<Utente> utente = new ArrayList<>();
        String query = "SELECT * FROM UTENTE";
        
        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Utente u = new Utente();
                int ruoloInt = rs.getInt("Ruolo");
                String username = rs.getString("Username");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                Timestamp dataCreazione=rs.getTimestamp("data_creazione");
                LocalDateTime data_creazione = dataCreazione != null ? dataCreazione.toLocalDateTime() : null;
                Timestamp dataModifica=rs.getTimestamp("data_ultima_modifica");
                LocalDateTime data_modifica= dataModifica !=null ? dataModifica.toLocalDateTime():null;
                
                
               
                
               
                Ruolo[] ruoli = Ruolo.values();
                if (ruoloInt >= 0 && ruoloInt < ruoli.length) {
                    u.setRuolo(ruoli[ruoloInt]);
                }
                
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(password);
                u.setData_creazione(data_creazione);
                u.setData_modifica(data_modifica);
                
                utente.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return utente;
    }
    
    @Override
    public Utente add(String username, String email, String password) {
        
        String checkQuery = "SELECT id FROM utente WHERE username = ? OR email = ?";
        String insertQuery = "INSERT INTO utente (ruolo,username,email,password) VALUES(?,?,?,?)";
        Utente u = new Utente();
        
        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement checkPs = c.prepareStatement(checkQuery);
            PreparedStatement insertPs = c.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        ) {
            checkPs.setString(1, username);
            checkPs.setString(2, email);
            
            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Errore: utente con questo username o email esiste già.");
                    return null; 
                }
            }
            
            Ruolo[] ruoli = Ruolo.values();
            Ruolo ruoloPredefinito = ruoli[1];
            
            insertPs.setInt(1, ruoloPredefinito.ordinal());
            insertPs.setString(2, username);
            insertPs.setString(3, email);
            insertPs.setString(4, password);
            
            u.setRuolo(ruoloPredefinito);
            
            int aggio = insertPs.executeUpdate();
            
            if (aggio > 0) {
                try (ResultSet generatedKeys = insertPs.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        long id = generatedKeys.getLong(1);
                        u.setId(id);
                        
                        String selectQuery = "SELECT data_creazione FROM utente WHERE id = ?";
                        try (PreparedStatement selectPs = c.prepareStatement(selectQuery)) {
                            selectPs.setLong(1, id);
                            try (ResultSet selectRs = selectPs.executeQuery()) {
                                if (selectRs.next()) {
                                    Timestamp dataCreazione = selectRs.getTimestamp("data_creazione");
                                    u.setData_creazione(dataCreazione != null ? dataCreazione.toLocalDateTime() : null);
                                }
                            }
                        }
                        
                        System.out.println("Utente aggiunto con successo. ID: " + id);
                        return u; 
                    }
                }
            } else {
                System.out.println("Errore nell'aggiunta dell'utente.");
            }
            
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { 
                System.out.println("Errore: utente con questo username o email esiste già.");
            } else {
                System.out.println("Errore SQL durante l'inserimento dell'utente: " + e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    @Override
    public Utente findByName(String username) {
    	String query = "SELECT id, ruolo, username, email, password, data_creazione, data_ultima_modifica FROM utente WHERE username = ?";
        Utente u = null;
        
        try (
                Connection c = JdbcDaoFactory.getConnection();
                PreparedStatement ps = c.prepareStatement(query)
            ) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        u = new Utente();
                        u.setId(rs.getLong("id"));
                        u.setUsername(rs.getString("username"));
                        u.setEmail(rs.getString("email"));
                        u.setPassword(rs.getString("password"));
                        
                        int ruoloInt = rs.getInt("ruolo");
                        Ruolo[] ruoli = Ruolo.values();
                        if (ruoloInt >= 0 && ruoloInt < ruoli.length) {
                            u.setRuolo(ruoli[ruoloInt]);
                        } else {
                            System.out.println("Ruolo non valido per l'utente: " + username);
                            return null;
                        }
                        
                        Timestamp dataCreazione = rs.getTimestamp("data_creazione");
                        u.setData_creazione(dataCreazione != null ? dataCreazione.toLocalDateTime() : null);
                        Timestamp dataModifica = rs.getTimestamp("data_ultima_modifica");
                        u.setData_modifica(dataModifica != null ? dataModifica.toLocalDateTime() : null);
                        
                        System.out.println("Utente trovato con successo: "  + u.getUsername() + " " + u.getEmail() + " " + u.getData_creazione() + " " + u.getData_modifica() + " " + u.getRuolo());
                    }else {
                    	 System.out.println("Utente non trovato con username: " + username);
                    }
                }

        } catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        return null;
    }
    
    @Override
    public Utente selectById(long id) {
        String query = "SELECT id, ruolo, username, email, password, data_creazione, data_ultima_modifica FROM utente WHERE id = ?";
        Utente u = null; // Inizializzazione dell'oggetto Utente

        try (Connection c = JdbcDaoFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {  // Controlla se ci sono risultati
                    u = new Utente();
                    u.setId(rs.getLong("id"));
                    u.setUsername(rs.getString("username"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    
                    int ruoloInt = rs.getInt("ruolo");
                    Ruolo[] ruoli = Ruolo.values();
                    if (ruoloInt >= 0 && ruoloInt < ruoli.length) {
                        u.setRuolo(ruoli[ruoloInt]);
                    } else {
                        System.out.println("Ruolo non valido per l'utente con ID: " + id);
                        return null;  // Ruolo non valido, ritorna null
                    }

                    Timestamp dataCreazione = rs.getTimestamp("data_creazione");
                    u.setData_creazione(dataCreazione != null ? dataCreazione.toLocalDateTime() : null);
                    Timestamp dataModifica = rs.getTimestamp("data_ultima_modifica");
                    u.setData_modifica(dataModifica != null ? dataModifica.toLocalDateTime() : null);

                    System.out.println("Utente trovato con successo: " + u.getUsername() + ", " + u.getEmail() + ", " + u.getData_creazione() + ", " + u.getData_modifica() + ", " + u.getRuolo());
                } else {
                    // Se non si trova l'utente, informa l'utente o registra l'errore
                    System.out.println("Nessun utente trovato con ID: " + id);
                }
            }
        } catch (SQLException e) {
            System.err.println("Errore SQL durante la selezione dell'utente: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Errore generico: " + e.getMessage());
            e.printStackTrace();
        }

        return u;  // Restituisce l'utente trovato o null se non trovato
    }



    @Override
    public Utente updateUsername(long id, String username) {
        String query = "UPDATE utente SET username = ?,data_ultima_modifica = ? WHERE id = ?";
        LocalDateTime now = LocalDateTime.now();
        
        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setString(1, username);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(now)); 
            ps.setLong(3, id);

            int aggio = ps.executeUpdate();

            if (aggio > 0) {
                Utente u = new Utente();
                u.setId(id);
                u.setUsername(username);
                u.setData_modifica(now); 

                System.out.println("Utente aggiornato con successo.");
                return u;
            } else {
                System.out.println("Error: Aggiornamento non riuscito.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return null; 
    }
    
    @Override
	public Utente updateEmail(long id,String email) {
    	 String query = "UPDATE utente SET email = ?,data_ultima_modifica = ? WHERE id = ?";
    	 LocalDateTime now = LocalDateTime.now();
    	 
    	 try(
    			Connection c=JdbcDaoFactory.getConnection();
    			 PreparedStatement ps=c.prepareStatement(query)
    			 ){
    		 ps.setString(1, email);
    		 ps.setTimestamp(2, java.sql.Timestamp.valueOf(now)); 
    		 ps.setLong(3,id);
    		 
    		 int aggio=ps.executeUpdate();
    		 
    		 if(aggio>0) {
    			 Utente u=new Utente();
    			 u.setId(id);
    			 u.setEmail(email);
    			 u.setData_modifica(now);
    			 
    			 System.out.println("mail aggiornata");
        		 return u;
        	 }else {
        		 System.out.println("Error:aggiornamento non riuscito");
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
	public Utente updatePassword(long id,String password) {
    	String query = "UPDATE utente SET password = ?,data_ultima_modifica = ? WHERE id = ?";
    	LocalDateTime now = LocalDateTime.now();
    	try(
    			Connection c=JdbcDaoFactory.getConnection();
    			 PreparedStatement ps=c.prepareStatement(query)
    			 ){
    		 ps.setString(1, password);
    		 ps.setTimestamp(2, java.sql.Timestamp.valueOf(now)); 
    		 ps.setLong(3,id);
    		 
    		 int aggio=ps.executeUpdate();
    		 
    		 if(aggio>0) {
    			 Utente u=new Utente();
    			 u.setId(id);
    			 u.setPassword(password);
    			 u.setData_modifica(now); 
    			 
    			 System.out.println("password aggiornata");
        		 return u;
        	 }else {
        		 System.out.println("Error:aggiornamento non riuscito");
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
    public Utente loginUtente(String email, String password) {
        
        String query = "SELECT * FROM utente WHERE email = ? AND password = ?";
        
        try (Connection c = JdbcDaoFactory.getConnection();
             PreparedStatement ps = c.prepareStatement(query)) {
            
            
            ps.setString(1, email);
            ps.setString(2, password);
            
            
            ResultSet rs = ps.executeQuery();
            
            
            if (rs.next()) {
            	
                Utente utente = new Utente();
                utente.setId(rs.getLong("id"));
                utente.setUsername(rs.getString("username"));
                utente.setEmail(rs.getString("email"));
                utente.setPassword(rs.getString("password"));
                int ruoloInt = rs.getInt("ruolo");
                Ruolo[] ruoli = Ruolo.values();
                if (ruoloInt >= 0 && ruoloInt < ruoli.length) {
                    utente.setRuolo(ruoli[ruoloInt]);
                } else {
                    System.out.println("Ruolo non valido per l'utente con ID: " + utente.getId());
                    return null;
                }
 
                System.out.println("Utente trovato: " + utente.getEmail());
                
                return utente;  
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } catch (Exception e) {
			
			e.printStackTrace();
		}
        
        
        System.out.println("Email o password errati.");
        return null;
    }


	@Override
	public Utente deleteByPassword(long id, String password) {
		String query = "DELETE FROM utente WHERE password=? AND id=?";

	    

	  	
       	try(
    			Connection c = JdbcDaoFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(query);			
    		)
       	{
       		
       		ps.setString(1,password);
       		ps.setLong(2,id);
       		
       		int aggio = ps.executeUpdate();
       		
       		if(aggio > 0) {
       			
       			System.out.println("Account eliminato con successo");
       			
       		}else {
       			
       			System.out.println("Errore nell' eliminazione");
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
	public Utente deleteByNome(long id, String username) {
		String query = "DELETE FROM utente WHERE username=? AND id=?";

	    

	  	
       	try(
    			Connection c = JdbcDaoFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(query);			
    		)
       	{
       		
       		ps.setString(1,username);
       		ps.setLong(2,id);
       		
       		int aggio = ps.executeUpdate();
       		
       		if(aggio > 0) {
       			
       			System.out.println("Account eliminato con successo");
       			
       		}else {
       			
       			System.out.println("Errore nell' eliminazione");
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
	public Utente ripristinaPassword(String username, String email, String password) {
	    String query = "UPDATE utente SET password = ?, data_ultima_modifica = ? WHERE username = ? AND email = ?";
	    LocalDateTime now = LocalDateTime.now();

	    try (Connection c = JdbcDaoFactory.getConnection();
	         PreparedStatement ps = c.prepareStatement(query)) {

	        ps.setString(1, password);
	        ps.setTimestamp(2, java.sql.Timestamp.valueOf(now)); 
	        ps.setString(3, username);
	        ps.setString(4, email);

	        int aggiornamenti = ps.executeUpdate();

	        if (aggiornamenti > 0) {
	            Utente u = new Utente();
	            u.setPassword(password);
	            u.setData_modifica(now);

	            System.out.println("Password aggiornata");
	            return u; 
	        } else {
	            System.out.println("Errore: aggiornamento non riuscito");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return null;
	}

    
}
