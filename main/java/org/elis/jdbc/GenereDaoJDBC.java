package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.elis.dao.GenereDao;
import org.elis.model.Genere;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere deleteByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
