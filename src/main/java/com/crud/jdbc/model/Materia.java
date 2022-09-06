package com.crud.jdbc.model;

public class Materia {
	
	private Long id;
    private String nombre;
    private Long año;
    private String semestre;
    
	public Materia() {
		super();
	}

	public Materia(Long id, String nombre, Long año, String semestre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.año = año;
		this.semestre = semestre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getAño() {
		return año;
	}

	public void setAño(Long año) {
		this.año = año;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}
    
    
}
