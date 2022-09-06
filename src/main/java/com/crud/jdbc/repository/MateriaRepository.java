package com.crud.jdbc.repository;

import java.util.List;

import com.crud.jdbc.model.Materia;

public class MateriaRepository implements CrudDAO<Materia>{
	
    private final static String DB_URL = "jdbc:mysql://localhost:3306/escuela_jdbc_crud?serverTimezone=GMT";
    private final static String DB_USER = "usuario";
    private final static String DB_PASSWORD = "Contraseñ@2022";
    
	@Override
	public Materia guardar(Materia entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Materia buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Materia> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Materia actualizar(Materia entity) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void borrarPorId(Long id) {
		// TODO Auto-generated method stub
		
	}
}
