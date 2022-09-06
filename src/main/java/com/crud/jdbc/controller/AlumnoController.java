package com.crud.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.jdbc.model.Alumno;
import com.crud.jdbc.repository.AlumnoRepository;

@Controller
public class AlumnoController {

	private AlumnoRepository repository = new AlumnoRepository();

	@GetMapping("/alumnos")
	public String buscarTodos(Model model) {

		model.addAttribute("alumnos", repository.buscarTodos());

		return "alumnos";
	}

	@GetMapping("/alumno")
	public String buscarAlumno(Model model, @RequestParam(required = false) Long id) {

		Alumno alumno = new Alumno();
		if (id != null) {
			alumno = repository.buscarPorId(id);
		}
		model.addAttribute("alumno", alumno);

		return "alumno";
	}

	@PostMapping("/alumno")
	public String enviarAlumno(@ModelAttribute Alumno alumno) {

		if (alumno.getId() != null) {
			repository.actualizar(alumno);
		} else {
			repository.guardar(alumno);
		}
		return "redirect:/alumnos";
	}

	@GetMapping("/alumno/delete")
	public String borrarAlumno(@RequestParam Long id) {

		repository.borrarPorId(id);

		return "redirect:/alumnos";
	}

}
