package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.OffertaDao;
import org.elis.model.Offerta;

public class OffertaDaoJDBC implements OffertaDao {
	
	private static OffertaDaoJDBC instance;
    
	   
    private OffertaDaoJDBC() {}
    
   
    public static OffertaDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OffertaDaoJDBC();
        }
        return instance;
    }


	@Override
	public Offerta add(String nome, double sconto, LocalDateTime data_inizio, LocalDateTime data_fine) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Offerta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta updatePrezzo(long id,double prezzo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta updateDataInizio(long id,LocalDateTime data_inizio) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta updateDataFine(long id,LocalDateTime data_fine) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta deleteByNome(String nome, long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta selectById(long id) {
	    String query = "SELECT * FROM offerta WHERE id = ?";

	    try (

	        Connection c = JdbcDaoFactory.getConnection(); 
	        PreparedStatement ps = c.prepareStatement(query)
	    ) {
	        ps.setLong(1, id);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Offerta offerta = new Offerta();
	                offerta.setId(rs.getLong("id"));
	                offerta.setNome(rs.getString("nome"));
	                offerta.setSconto(rs.getDouble("sconto"));
	                offerta.setData_inizio(rs.getTimestamp("data_inizio").toLocalDateTime());
	                offerta.setData_fine(rs.getTimestamp("data_fine").toLocalDateTime());
	                return offerta;
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
	public Offerta selectByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}
}
