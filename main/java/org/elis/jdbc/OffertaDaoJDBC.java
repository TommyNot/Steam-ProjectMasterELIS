package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        String query = "INSERT INTO offerta (data_creazione, data_ultima_modifica, nome, sconto, data_inizio, data_fine) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();  

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {

            ps.setTimestamp(1, java.sql.Timestamp.valueOf(now)); 
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(now));
            ps.setString(3, nome); 
            ps.setDouble(4, sconto); 
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(data_inizio)); 
            ps.setTimestamp(6, java.sql.Timestamp.valueOf(data_fine));

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("inserimento fallito");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Offerta offerta = new Offerta();
                    offerta.setId(generatedKeys.getLong(1)); 
                    offerta.setData_creazione(now); 
                    offerta.setData_ultima_modifica(now); 
                    offerta.setNome(nome);
                    offerta.setSconto(sconto);
                    offerta.setData_inizio(data_inizio);
                    offerta.setData_fine(data_fine);

                    return offerta; 
                } else {
                    throw new SQLException("inserimento fallito");
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
    public List<Offerta> findAll() {
        String query = "SELECT id, data_creazione, data_ultima_modifica, nome, sconto, data_inizio, data_fine FROM offerta";
        List<Offerta> offerte = new ArrayList<>();  

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery(); 
        ) {
            while (rs.next()) {
                Offerta offerta = new Offerta();
                offerta.setId(rs.getLong("id"));
                offerta.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
                offerta.setData_ultima_modifica(rs.getTimestamp("data_ultima_modifica").toLocalDateTime());
                offerta.setNome(rs.getString("nome"));
                offerta.setSconto(rs.getDouble("sconto"));
                offerta.setData_inizio(rs.getTimestamp("data_inizio").toLocalDateTime());
                offerta.setData_fine(rs.getTimestamp("data_fine").toLocalDateTime());

                offerte.add(offerta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return offerte;  
    }



    @Override
    public Offerta updatePrezzo(long id, double prezzo) {
        String query = "UPDATE offerta SET sconto = ?, data_ultima_modifica = ? WHERE id = ?";
        LocalDateTime now = LocalDateTime.now();

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setDouble(1, prezzo);
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(now));
            ps.setLong(3, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Aggiornamento fallito, nessuna riga aggiornata.");
            }

            return selectById(id); 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    @Override
    public Offerta updateDataInizio(long id, LocalDateTime data_inizio) {
        String query = "UPDATE offerta SET data_inizio = ?, data_ultima_modifica = ? WHERE id = ?";
        LocalDateTime now = LocalDateTime.now();

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(data_inizio));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(now));
            ps.setLong(3, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Aggiornamento fallito, nessuna riga aggiornata.");
            }

            return selectById(id); 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



	@Override
	public Offerta updateDataFine(long id,LocalDateTime data_fine) {
		String query = "UPDATE offerta SET data_fine = ?, data_ultima_modifica = ? WHERE id = ?";
        LocalDateTime now = LocalDateTime.now();

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement ps = c.prepareStatement(query);
        ) {
            ps.setTimestamp(1, java.sql.Timestamp.valueOf(data_fine));
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(now));
            ps.setLong(3, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Aggiornamento fallito, nessuna riga aggiornata.");
            }

            return selectById(id); 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


	@Override
	public Offerta deleteByNome(String nome, long id) {
	    String query = "DELETE FROM offerta WHERE nome = ? AND id = ?";

	    try (
	        Connection c = JdbcDaoFactory.getConnection();
	        PreparedStatement ps = c.prepareStatement(query);
	    ) {
	        ps.setString(1, nome);
	        ps.setLong(2, id);

	        Offerta offertaDaEliminare = selectById(id);

	        int affectedRows = ps.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Eliminazione fallita, nessuna riga eliminata.");
	        }

	        return offertaDaEliminare;

	    } catch (SQLException e) {
	        e.printStackTrace(); 
	    } catch (Exception e) {
	        e.printStackTrace();  
	    }

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
	    String query = "SELECT * FROM offerta WHERE nome = ?";

	    try (
	        Connection c = JdbcDaoFactory.getConnection();
	        PreparedStatement ps = c.prepareStatement(query);
	    ) {
	        ps.setString(1, nome);

	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Offerta offerta = new Offerta();
	                offerta.setId(rs.getLong("id"));
	                offerta.setNome(rs.getString("nome"));
	                offerta.setSconto(rs.getDouble("sconto"));
	                offerta.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
	                offerta.setData_ultima_modifica(rs.getTimestamp("data_ultima_modifica").toLocalDateTime());
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

}
