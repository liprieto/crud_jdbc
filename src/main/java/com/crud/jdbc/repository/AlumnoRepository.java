package com.crud.jdbc.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crud.jdbc.model.Alumno;
import com.crud.jdbc.service.Utils;





public class AlumnoRepository implements CrudDAO<Alumno>{
	
    private final static String DB_URL = "jdbc:mysql://localhost:3306/escuela_jdbc_crud?serverTimezone=GMT";
    private final static String DB_USER = "usuario";
    private final static String DB_PASSWORD = "Contraseñ@2022";
    
	@Override
	public Alumno guardar(Alumno alumno) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
        try {
            connection = DriverManager.getConnection(
            		DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
            		"INSERT INTO alumno (nombre, apellido, fecha_nac, domicilio) VALUES (?, ?, ?, ?)",
            		Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setDate(3, alumno.getFecha_nac());
            statement.setString(4, alumno.getDomicilio());


            if (statement.executeUpdate() != 1) {
                throw new SQLException("No se pudo insertar registro");
            }

            generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                alumno.setId(id);
                return alumno;
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
	public Alumno buscarPorId(Long id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM alumno WHERE id = ?;"
            );
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Date fechaNac = resultSet.getDate("fecha_nac");
                String domicilio = resultSet.getString("domicilio");
                return new Alumno(id, nombre, apellido, fechaNac, domicilio);
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
	public List<Alumno> buscarTodos() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "SELECT * FROM alumno;"
            );
            resultSet = statement.executeQuery();

            List<Alumno> alumnos = new ArrayList<>();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Date fechaNac = resultSet.getDate("fecha_nac");
                String domicilio = resultSet.getString("domicilio");
      
                alumnos.add(new Alumno(id, nombre, apellido, fechaNac, domicilio));
            }
            return alumnos;
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
	public Alumno actualizar(Alumno alumno) {
		Connection connection = null;
		PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "UPDATE alumno SET nombre=?, apellido=?, fecha_nac=?, domicilio=? WHERE id=?"
            );
            statement.setString(1, alumno.getNombre());
            statement.setString(2, alumno.getApellido());
            statement.setDate(3, alumno.getFecha_nac());
            statement.setString(4, alumno.getDomicilio());

            statement.setLong(5, alumno.getId());

            if (statement.executeUpdate() != 1) {
                throw new SQLException("No es posible actualizar datos");
            }
            return alumno;
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
	public void borrarPorId(Long id) {
		Connection connection = null;
		PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(
                    DB_URL, DB_USER, DB_PASSWORD
            );
            statement = connection.prepareStatement(
                    "DELETE FROM alumno WHERE id=?"
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
