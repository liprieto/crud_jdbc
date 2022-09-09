package com.crud.jdbc.repository;

import java.util.List;

public interface CrudDAO<T> {

	T guardar(T entity);

	T buscarPorId(int id);

	List<T> buscarTodos();

	T actualizar(T entity);

	void borrarPorId(int id);

}
