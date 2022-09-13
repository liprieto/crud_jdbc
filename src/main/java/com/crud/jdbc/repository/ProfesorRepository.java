package com.crud.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.crud.jdbc.model.Profesor;
import com.crud.jdbc.service.Utils;

import lombok.Data;

@Data
public class ProfesorRepository implements CrudDAO<Profesor> {

	private final static String DB_URL = "jdbc:mysql://localhost:3306/escuela_jdbc_crud?serverTimezone=GMT";
	private final static String DB_USER = "usuario";
	private final static String DB_PASSWORD = "Contraseñ@2022";

	@Override
	public Profesor guardar(Profesor profesor) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("INSERT INTO profesor (nombre, apellido, legajo) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, profesor.getNombre());
			statement.setString(2, profesor.getApellido());
			statement.setString(3, profesor.getLegajo());

			if (statement.executeUpdate() != 1) {
				throw new SQLException("No se pudo insertar registro");
			}

			generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {
				int id = generatedKeys.getInt(1);
				profesor.setId(id);
				return profesor;
			} else {
				throw new SQLException("No se pudo asignar ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Utils.cerrarResultSet(generatedKeys);
			Utils.cerrarStatement(statement);
			Utils.cerrarConexion(connection);
		}
		return null;
	}

	@Override
	public Profesor buscarPorId(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM profesor WHERE id = ?;"
            );
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String legajo = resultSet.getString("legajo");
                return new Profesor(id, nombre, apellido, legajo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
		} finally {
			Utils.cerrarResultSet(resultSet);
			Utils.cerrarStatement(statement);
			Utils.cerrarConexion(connection);
		}
        return null;
	}

	@Override
	public List<Profesor> buscarTodos() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("SELECT * FROM profesor;");
			resultSet = statement.executeQuery();
			
			List<Profesor> profesores = new ArrayList<>();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				String legajo = resultSet.getString("legajo");
				
				profesores.add(new Profesor(id, nombre, apellido, legajo));
			}
			return profesores;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Utils.cerrarResultSet(resultSet);
			Utils.cerrarStatement(statement);
			Utils.cerrarConexion(connection);
		}
		return null;
	}

	@Override
	public Profesor actualizar(Profesor profesor) {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.prepareStatement("UPDATE profesor SET nombre =?, apellido=?, legajo=? WHERE id=?");
			
			statement.setString(1, profesor.getNombre());
			statement.setString(2, profesor.getApellido());
			statement.setString(3, profesor.getLegajo());			
			statement.setInt(4, profesor.getId());
			
			if (statement.executeUpdate() != 1) {
                throw new SQLException("No es posible actualizar datos");
            }
            return profesor;
		}catch(SQLException e) {
			
		}finally {
			Utils.cerrarStatement(statement);
			Utils.cerrarConexion(connection);
		}
		return null;
	}

	@Override
	public void borrarPorId(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "DELETE FROM profesor WHERE id=?"
            );
            statement.setLong(1, id);

            if (statement.executeUpdate() != 1) {
                throw new SQLException("No se pudo eliminar registro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		finally {
			Utils.cerrarStatement(statement);
			Utils.cerrarConexion(connection);
		}

	}


}
