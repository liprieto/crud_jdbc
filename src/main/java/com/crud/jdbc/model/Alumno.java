package com.crud.jdbc.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Alumno {
	
	private int id;
    private String nombre;
    private String apellido;
    private Date fecha_nac;
    private String domicilio;
    
    
}
