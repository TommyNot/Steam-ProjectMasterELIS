package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                
               
                Ruolo[] ruoli = Ruolo.values();
                if (ruoloInt >= 0 && ruoloInt < ruoli.length) {
                    u.setRuolo(ruoli[ruoloInt]);
                }
                
                u.setUsername(username);
                u.setEmail(email);
                u.setPassword(password);
                
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
    			PreparedStatement ps = c.prepareStatement(query);
    			
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
            	
            	System.out.println("Utente Aggiunto Con Successo");
            	 return u;
            }else {
            	
            	System.out.println("Errore nell' aggiunta");
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

    	for(Utente u : findAll()) {
    		
    		if(u.getUsername().equalsIgnoreCase(username)) {
    			
    			System.out.println("Utente trovato con successo " + username);
    			return u;
    		}else {
    			
    			System.out.println("Utente non trovato");
    		}
    		
    		
    	}
		return null;
    }

    @Override
    public Utente update(int id,String username, String email, String password) {
        
    	String query="UPDATE utente SET WHERE id=?,username=?, email=?, password=?";
    	
    	//bisogna aggiungere error se utente non presente?
    	
    	try(
    			
    			Connection c = JdbcDaoFactory.getConnection();
    			PreparedStatement ps = c.prepareStatement(query);
    			
    		){
    		
    		ps.setInt(1, id);
    		ps.setString(1, username);
    		ps.setString(2, email);
    		ps.setString(3, password);
    		
    		int aggio = ps.executeUpdate();
    		
    		// execute torna un int faccio controllo sul ritorno ? non so è utile potrei mettere if per far return u se update a successo
    		
    		if(aggio > 0) {
    			
    		Utente u = new Utente();
    			
    		u.setUsername(username);
    		u.setEmail(email);
    		u.setPassword(password);
    		
    		System.out.println("Success Utente aggiornato con successo");
    		return u;
    		
    		}else {
    			
    			System.out.println("Error");
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
}
