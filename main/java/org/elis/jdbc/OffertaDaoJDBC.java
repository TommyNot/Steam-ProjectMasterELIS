package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
           
            ps.setTimestamp(1, Timestamp.valueOf(now)); // data_creazione
            ps.setTimestamp(2, Timestamp.valueOf(now)); // data_ultima_modifica
            ps.setString(3, nome); // nome
            ps.setDouble(4, sconto); // sconto
            ps.setTimestamp(5, Timestamp.valueOf(data_inizio)); // data_inizio
            ps.setTimestamp(6, Timestamp.valueOf(data_fine)); // data_fine

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Errore: nessuna riga inserita");
                return null;
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
                    System.out.println("Errore: nessuna chiave generata");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Errore SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Errore generico: " + e.getMessage());
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
        String selectQuery = "SELECT o.id, o.data_creazione, o.data_ultima_modifica, o.nome, o.sconto, o.data_inizio, o.data_fine " +
                             "FROM offerta o " +
                             "WHERE o.id = ?";

        LocalDateTime now = LocalDateTime.now();

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement updatePs = c.prepareStatement(query);
            PreparedStatement selectPs = c.prepareStatement(selectQuery);
        ) {
            
            updatePs.setDouble(1, prezzo);
            updatePs.setTimestamp(2, Timestamp.valueOf(now));
            updatePs.setLong(3, id);

            int affectedRows = updatePs.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Errore: nessuna offerta aggiornata, verifica l'ID.");
                return null;
            }

            
            selectPs.setLong(1, id);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    Offerta offerta = new Offerta();
                    offerta.setId(rs.getLong("id"));
                    offerta.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
                    offerta.setData_ultima_modifica(rs.getTimestamp("data_ultima_modifica").toLocalDateTime());
                    offerta.setNome(rs.getString("nome"));
                    offerta.setSconto(rs.getDouble("sconto"));
                    offerta.setData_inizio(rs.getTimestamp("data_inizio").toLocalDateTime());
                    offerta.setData_fine(rs.getTimestamp("data_fine").toLocalDateTime());

                    return offerta; 
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Errore durante l'aggiornamento del prezzo: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public Offerta updateDataInizio(long id, LocalDateTime data_inizio) {
        String updateQuery = "UPDATE offerta SET data_inizio = ?, data_ultima_modifica = ? WHERE id = ?";
        String selectQuery = "SELECT o.id, o.data_creazione, o.data_ultima_modifica, o.nome, o.sconto, o.data_inizio, o.data_fine " +
                             "FROM offerta o " +
                             "WHERE o.id = ?";
        
        LocalDateTime now = LocalDateTime.now();

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement updatePs = c.prepareStatement(updateQuery);
            PreparedStatement selectPs = c.prepareStatement(selectQuery);
        ) {
            
            updatePs.setTimestamp(1, Timestamp.valueOf(data_inizio));
            updatePs.setTimestamp(2, Timestamp.valueOf(now));
            updatePs.setLong(3, id);

            int affectedRows = updatePs.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Errore: nessuna offerta aggiornata, verifica l'ID.");
                return null;
            }

            
            selectPs.setLong(1, id);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    Offerta offerta = new Offerta();
                    offerta.setId(rs.getLong("id"));
                    offerta.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
                    offerta.setData_ultima_modifica(rs.getTimestamp("data_ultima_modifica").toLocalDateTime());
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
    public Offerta updateDataFine(long id, LocalDateTime data_fine) {
        String updateQuery = "UPDATE offerta SET data_fine = ?, data_ultima_modifica = ? WHERE id = ?";
        String selectQuery = "SELECT o.id, o.data_creazione, o.data_ultima_modifica, o.nome, o.sconto, o.data_inizio, o.data_fine " +
                             "FROM offerta o " +
                             "WHERE o.id = ?";

        LocalDateTime now = LocalDateTime.now();

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement updatePs = c.prepareStatement(updateQuery);
            PreparedStatement selectPs = c.prepareStatement(selectQuery);
        ) {
           
            updatePs.setTimestamp(1, Timestamp.valueOf(data_fine));
            updatePs.setTimestamp(2, Timestamp.valueOf(now));
            updatePs.setLong(3, id);

            int affectedRows = updatePs.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Errore: nessuna offerta aggiornata, verifica l'ID.");
                return null;
            }

           
            selectPs.setLong(1, id);
            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                    Offerta offerta = new Offerta();
                    offerta.setId(rs.getLong("id"));
                    offerta.setData_creazione(rs.getTimestamp("data_creazione").toLocalDateTime());
                    offerta.setData_ultima_modifica(rs.getTimestamp("data_ultima_modifica").toLocalDateTime());
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
    public Offerta deleteByNome(String nome, long id) {
        String selectQuery = "SELECT id, nome, sconto, data_inizio, data_fine FROM offerta WHERE nome = ? AND id = ?";
        String deleteQuery = "DELETE FROM offerta WHERE nome = ? AND id = ?";

        Offerta offertaDaEliminare = null;

        try (
            Connection c = JdbcDaoFactory.getConnection();
            PreparedStatement selectPs = c.prepareStatement(selectQuery);
            PreparedStatement deletePs = c.prepareStatement(deleteQuery);
        ) {
            
            selectPs.setString(1, nome);
            selectPs.setLong(2, id);

            try (ResultSet rs = selectPs.executeQuery()) {
                if (rs.next()) {
                	
                    offertaDaEliminare = new Offerta();
                    offertaDaEliminare.setId(rs.getLong("id"));
                    offertaDaEliminare.setNome(rs.getString("nome"));
                    offertaDaEliminare.setSconto(rs.getDouble("sconto"));
                    offertaDaEliminare.setData_inizio(rs.getTimestamp("data_inizio").toLocalDateTime());
                    offertaDaEliminare.setData_fine(rs.getTimestamp("data_fine").toLocalDateTime());
                } else {
                	
                    System.out.println("Nessuna offerta trovata con quel nome e ID.");
                    return null;
                }
            }

            
            deletePs.setString(1, nome);
            deletePs.setLong(2, id);

            int affectedRows = deletePs.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Errore durante la cancellazione dell'offerta.");
                return null;
            }

            System.out.println("Offerta eliminata con successo.");
            
            return offertaDaEliminare; 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
			// TODO Auto-generated catch block
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
