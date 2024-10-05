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
                System.out.println("errore");
                return null;
            }
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Genere genere = new Genere();
                    genere.setId(generatedKeys.getLong(1)); 
                    genere.setNome(nome);
                    return genere;
                } else {
                    System.out.println("errore di compilazione");
                    return null;
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
        List<Genere> generi = new ArrayList<>();
        String query = "SELECT "
                     + "g.id AS genere_id, g.nome AS genere_nome, "
                     + "g.data_creazione AS genere_data_creazione, "
                     + "g.data_ultima_modifica AS genere_data_ultima_modifica, "
                     + "o.id AS offerta_id, o.nome AS offerta_nome, "
                     + "o.sconto AS offerta_sconto, "
                     + "o.data_inizio AS offerta_data_inizio, "
                     + "o.data_fine AS offerta_data_fine "
                     + "FROM genere g "
                     + "LEFT JOIN offerta o ON g.id_offerta = o.id";

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                Genere g = new Genere();
                g.setId(rs.getInt("genere_id"));
                g.setNome(rs.getString("genere_nome"));
                g.setData_creazione(rs.getTimestamp("genere_data_creazione").toLocalDateTime());
                g.setData_ultima_modifica(rs.getTimestamp("genere_data_ultima_modifica").toLocalDateTime());

                // Estrai i dettagli dell'offerta
                Offerta offerta = null; // Default null
                int offertaId = rs.getInt("offerta_id");
                if (offertaId > 0) { // Controlla se esiste un'offerta
                    offerta = new Offerta();
                    offerta.setId(offertaId);
                    offerta.setNome(rs.getString("offerta_nome"));
                   
                    offerta.setData_inizio(rs.getTimestamp("offerta_data_inizio").toLocalDateTime());
                    offerta.setData_fine(rs.getTimestamp("offerta_data_fine").toLocalDateTime());
                }
                
                g.setOfferta(offerta);
                generi.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return generi;
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
	    String selectQuery = "SELECT * FROM genere WHERE nome = ?";
	    String deleteQuery = "DELETE FROM genere WHERE nome = ?";

	    try (
	        Connection c = JdbcDaoFactory.getConnection(); 
	        PreparedStatement selectStmt = c.prepareStatement(selectQuery);
	        PreparedStatement deleteStmt = c.prepareStatement(deleteQuery)
	    ) {
	        selectStmt.setString(1, nome);
	        try (ResultSet rs = selectStmt.executeQuery()) {
	            if (rs.next()) {
	                Genere g = new Genere();
	                g.setId(rs.getLong("id"));
	                g.setNome(rs.getString("nome"));
	                
	                deleteStmt.setString(1, nome);
	                int affectedRows = deleteStmt.executeUpdate();
	                
	                if (affectedRows > 0) {
	                    return g;  
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;
	}

}
