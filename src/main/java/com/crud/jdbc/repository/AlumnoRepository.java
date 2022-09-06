package com.crud.jdbc.repository;

import java.util.List;

import com.crud.jdbc.model.Alumno;

public class AlumnoRepository implements CrudDAO<Alumno>{
	
    private final static String DB_URL = "jdbc:mysql://localhost:3306/escuela_jdbc_crud?serverTimezone=GMT";
    private final static String DB_USER = "usuario";
    private final static String DB_PASSWORD = "Contraseñ@2022";
    
	@Override
	public Alumno guardar(Alumno entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Alumno buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Alumno> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Alumno actualizar(Alumno entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void borrarPorId(Long id) {
		// TODO Auto-generated method stub
		
	}
}
