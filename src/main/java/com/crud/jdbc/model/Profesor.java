package com.crud.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profesor {
	
	
	private int id;
	private String nombre;
	private String apellido;
	private String legajo;
}
