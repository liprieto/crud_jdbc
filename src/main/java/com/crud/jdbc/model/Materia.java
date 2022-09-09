package com.crud.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Materia {

	private int id;
	private String nombre;
	private int periodo;
	private String semestre;
	
}
