package org.elis.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.LibreriaDao;
import org.elis.model.Libreria;
import org.elis.model.Utente;

public class LibreriaDaoJDBC implements LibreriaDao{
	
    private static LibreriaDaoJDBC instance;
    
    
    private LibreriaDaoJDBC() {}
    
   
    public static LibreriaDaoJDBC getInstance() {
        if (instance == null) {
        	
            instance = new LibreriaDaoJDBC();
        }
        
        return instance;
    }


	@Override
	public Libreria add(String nome, long id_utente) {
		String insertQuery = "INSERT INTO libreria(nome, id_utente) VALUES(?, ?)";
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			){
			ps.setString(1, nome);
			ps.setLong(2, id_utente);
			int righeQuery = ps.executeUpdate();
			
			if(righeQuery == 0) {
				System.out.println("Non è stata aggiornata nessuna riga.");
				return null;
			}
			try(ResultSet generatedKeys = ps.getGeneratedKeys()){
				if(generatedKeys.next()) {
					Libreria l = new Libreria();
					l.setNome(nome);
					return l;
				}else {
					System.out.println("Non è stato aggiunta nessuna libreria.");
					return null;
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public List<Libreria> findAll() {
		String query = "SELECT * FROM libreria";
		String queryUtente = "SELECT * FROM utente WHERE id = ?";
		List<Libreria> librerie = new ArrayList<>();
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				PreparedStatement psUtente = c.prepareStatement(queryUtente);
				ResultSet rs = ps.executeQuery();
				
				){
			
			
			
			while(rs.next()) {
				Libreria l = new Libreria();
				
				l.setId(rs.getLong("id"));
				l.setNome(rs.getString("nome"));
				long idUtente = rs.getLong("id_utente");

				psUtente.setLong(1, idUtente);
				ResultSet rsUtente = psUtente.executeQuery();
				Utente u = new Utente();
				if(rsUtente.next()) {
					u.setEmail(rsUtente.getString("email"));
					u.setUsername(rsUtente.getString("username"));
					u.setPassword(rsUtente.getString("password"));
				}
				l.setUtente(u);
				// query Utente table to get Utente object by id
				librerie.add(l);
				
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return librerie;
	}

	
	@Override
	public List<Libreria> findByIdUtente(long id_utente) {
		String query = "SELECT id, nome FROM libreria WHERE id_utente = ?";
		List<Libreria> librerie = new ArrayList<>();
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				){
			ps.setLong(1, id_utente);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Libreria l = new Libreria();
				l.setId(rs.getLong("id"));
				l.setNome(rs.getString("nome"));
				
				librerie.add(l);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return librerie;
	}
	
	
	@Override
	public Libreria findByName(String nome) {
		String query = "SELECT * FROM libreria WHERE nome = ?";
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				){
			ps.setString(1, nome);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Libreria l = new Libreria();
				
				l.setNome(rs.getString("nome"));
				System.out.println("Libreria trovata.");
				return l;
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Libreria updateNome(long id, String nome) {
		String updateQuery = "UPDATE libreria SET nome = ?, data_ultima_modifica = ? WHERE id = ?";
		LocalDateTime dataOraAdesso = LocalDateTime.now();
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(updateQuery);
				){
			ps.setString(1, nome);
			ps.setTimestamp(2, java.sql.Timestamp.valueOf(dataOraAdesso));
			ps.setLong(3, id);
			
			int queryEseguita = ps.executeUpdate();
			if(queryEseguita > 0) {
				Libreria l = new Libreria();
				l.setId(id);
				l.setNome(nome);
				l.setData_ultima_modifica(dataOraAdesso);
				
				System.out.println("Nome libreria aggiornato.");
				return l;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}


	@Override
	public Libreria deleteById(long id) {
		String deleteQuery = "DELETE FROM libreria WHERE id = ?";
		
		try(
				Connection c = JdbcDaoFactory.getConnection();
				PreparedStatement ps = c.prepareStatement(deleteQuery);
				){
			ps.setLong(1,  id);
			
			int queryEseguita = ps.executeUpdate();
			
			if(queryEseguita > 0) {
				System.out.println("Libreria eliminata con successo.");
			}else {
				System.out.println("Error");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return null;
	}

}
