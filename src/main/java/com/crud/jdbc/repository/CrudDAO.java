package com.crud.jdbc.repository;

import java.util.List;

public interface CrudDAO<T> {

	    T guardar(T entity);

	    T buscarPorId(Long id);

	    List<T> buscarTodos();

	    T actualizar(T entity);

	    void borrarPorId(Long id);
	
}
