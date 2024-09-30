package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.GenereDao;
import org.elis.model.Genere;
import org.elis.model.Offerta;

public class GenereDaoJDBC implements GenereDao{

private static GenereDaoJDBC instance;
    
    
    private GenereDaoJDBC() {}
    
   
    public static GenereDaoJDBC getInstance() {
        if (instance == null) {
        	
            instance = new GenereDaoJDBC();
        }
        
        return instance;
    }
	
    @Override
    public Genere add(String nome) {
        String query = "INSERT INTO genere(nome) VALUES(?)";
        
        try (
            Connection c = JdbcDaoFactory.getConnection(); 
            PreparedStatement ps = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, nome);
            
            int aggio = ps.executeUpdate();
            
            if (aggio == 0) {
                throw new SQLException("inserimento fallito");
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Genere genere = new Genere();
                    genere.setId(generatedKeys.getLong(1)); 
                    genere.setNome(nome);
                    return genere;
                } else {
                    throw new SQLException("inserimento fallito,nessun id trovato");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }


	@Override
	public List<Genere> findAll() {
		List<Genere> genere = new ArrayList<>();
		String query="SELECT * FROM GENERE";
		
		try(
				Connection c=JdbcDaoFactory.getConnection();
				PreparedStatement ps=c.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				){
			while(rs.next()) {
				Genere g=new Genere();
				String nome=rs.getString("nome");
				Timestamp dataCreazione=rs.getTimestamp("data_creazione");
                LocalDateTime data_creazione = dataCreazione != null ? dataCreazione.toLocalDateTime() : null;
                Timestamp dataModifica=rs.getTimestamp("data_ultima_modifica");
                LocalDateTime data_modifica= dataModifica !=null ? dataModifica.toLocalDateTime():null;
                Offerta offerta=((Genere) rs).getOfferta();     
                
                g.setNome(nome);
                g.setData_creazione(data_creazione);
                g.setData_ultima_modifica(data_modifica);
                g.setOfferta(offerta);
                
                genere.add(g);
				
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
	public Genere findByName(String nome) {
	    String query = "SELECT * FROM genere WHERE nome = ?";
	    
	    try (
	        Connection c = JdbcDaoFactory.getConnection(); 
	        PreparedStatement ps = c.prepareStatement(query)
	    ) {
	        ps.setString(1, nome);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Genere genere = new Genere();
	                genere.setId(rs.getLong("id"));
	                genere.setNome(rs.getString("nome"));
	                return genere;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}


	@Override
	public Genere selectById(long id) {
	    String query = "SELECT * FROM genere WHERE id = ?";
	    
	    try (
	        Connection c = JdbcDaoFactory.getConnection(); 
	        PreparedStatement ps = c.prepareStatement(query)
	    ) {

	        ps.setLong(1, id);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Genere genere = new Genere();
	                genere.setId(rs.getLong("id"));   
	                genere.setNome(rs.getString("nome"));  
	                return genere;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}


	@Override
	public Genere deleteByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
