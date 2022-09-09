package com.crud.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.crud.jdbc.model.Materia;
import com.crud.jdbc.service.Utils;

import lombok.Data;

@Data
public class MateriaRepository implements CrudDAO<Materia>{
	
    private final static String DB_URL = "jdbc:mysql://localhost:3306/escuela_jdbc_crud?serverTimezone=GMT";
    private final static String DB_USER = "usuario";
    private final static String DB_PASSWORD = "Contraseñ@2022";
    
	@Override
	public Materia guardar(Materia materia) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
        try {
            connection = DriverManager.getConnection(
            		DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
            		"INSERT INTO materia (nombre, periodo, semestre) VALUES (?, ?, ?)",
            		Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, materia.getNombre());
            statement.setLong(2, materia.getPeriodo());
            statement.setString(3, materia.getSemestre());
         


            if (statement.executeUpdate() != 1) {
                throw new SQLException("No se pudo insertar registro");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                materia.setId(id);
                return materia;
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
	public Materia buscarPorId(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM materia WHERE id = ?;"
            );
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                int periodo = resultSet.getInt("periodo");
                String semestre = resultSet.getString("semestre");
                return new Materia(id, nombre, periodo, semestre);
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
	public List<Materia> buscarTodos() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM materia;"
            );
            resultSet = statement.executeQuery();

            List<Materia> materias = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int periodo = resultSet.getInt("periodo");
                String semestre = resultSet.getString("semestre");
                
      
                materias.add(new Materia(id, nombre, periodo, semestre));
            }
            return materias;
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
	public Materia actualizar(Materia materia) {
		Connection connection = null;
		PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "UPDATE materia SET nombre=?, periodo=?, semestre=? WHERE id=?"
            );
            statement.setString(1, materia.getNombre());
            statement.setLong(2, materia.getPeriodo());
            statement.setString(3, materia.getSemestre());

            statement.setLong(4, materia.getId());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("No es posible actualizar datos");
            }
            return materia;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		finally {
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
                    "DELETE FROM materia WHERE id=?"
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
