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
    public Utente add(int ruolo, String username, String email, String password) {
    	
    	String query = "INSERT INTO utente (ruolo,username,email,password) VALUES(?,?,?,?)";
    	Utente u = new Utente();
    	try(
    			Connection c = JdbcDaoFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    			
    		)
    	{
            ps.setInt(1, ruolo);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, password);
            
            
            
            Ruolo[] ruoli = Ruolo.values();
            Ruolo rol = ruoli[ruolo];
            
            u.setRuolo(rol);
            
            int aggio = ps.executeUpdate();
            
            if(aggio > 0) {
            	
            	 try (ResultSet generatedKeys = ps.getGeneratedKeys()) { 
                     if (generatedKeys.next()) {
                         long id = generatedKeys.getLong(1);
                         u.setId(id);

                         String selectQuery = "SELECT data_creazione FROM utente WHERE id = ?";
                         try (PreparedStatement selectPs = c.prepareStatement(selectQuery)) {
                             selectPs.setLong(1, id);
                             try (ResultSet rs = selectPs.executeQuery()) {
                                 if (rs.next()) {
                                     Timestamp dataCreazione = rs.getTimestamp("data_creazione");
                                     u.setData_creazione(dataCreazione != null ? dataCreazione.toLocalDateTime() : null);
                                 }
                             }
                         }
                         
                         System.out.println("Utente aggiunto con successo. ID: " + id);
                         return u;
                     }
                 }
             } else {
                 System.out.println("Errore nell'aggiunta dell'utente");
             }
         } catch (SQLException e) {
             e.printStackTrace();
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
        Utente u = null;

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setLong(1, id); 
            
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
                        System.out.println("Ruolo non valido per l'utente con ID: " + id);
                        return null;
                    }

                    Timestamp dataCreazione = rs.getTimestamp("data_creazione");
                    u.setData_creazione(dataCreazione != null ? dataCreazione.toLocalDateTime() : null);
                    Timestamp dataModifica = rs.getTimestamp("data_ultima_modifica");
                    u.setData_modifica(dataModifica != null ? dataModifica.toLocalDateTime() : null);

                    System.out.println("Utente trovato con successo: "+ u.getUsername() + " " + u.getEmail() + " "+ u.getData_creazione() + " " + u.getData_modifica() + " " + u.getRuolo());
                } else {
                    System.out.println("Utente non trovato con ID: " + id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return u;
    }



    @Override
    public Utente updateUsername(long id, String username) {
        String query = "UPDATE utente SET username = ? WHERE id = ?";

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query)
        ) {
            ps.setString(1, username);
            ps.setLong(2, id);

            int aggio = ps.executeUpdate();

            if (aggio > 0) {
                Utente u = new Utente();
                u.setId(id);
                u.setUsername(username);

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
    public Utente deleteByName(String email, String password) {
    	
    	String query = "DELETE FROM utente WHERE email=? AND password=?";
    	
    	
       	try(
    			Connection c = JdbcDaoFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(query);
       			
    			
    		)
       	{
       		
       		ps.setString(1,email);
       		ps.setString(2, password);
       		
       		int aggio = ps.executeUpdate();
       		
       		if(aggio > 0) {
       			
       			System.out.println("Eliminato con successo");
       			
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
	public Utente updateEmail(long id, String email) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Utente updatePassword(long id, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
