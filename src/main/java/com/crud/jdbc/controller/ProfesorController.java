package com.crud.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.jdbc.model.Profesor;
import com.crud.jdbc.repository.ProfesorRepository;

@Controller
public class ProfesorController {
	
	private ProfesorRepository profesorRepository = new ProfesorRepository();
	
	@GetMapping("/profesores")
    public String buscarTodos(Model model) {

        model.addAttribute("profesores", profesorRepository.buscarTodos());

        return "profesores";
    }

    @GetMapping("/profesor")
    public String buscarProfesor(Model model,
                            @RequestParam(required = false) Integer id) {

        Profesor profesor = new Profesor();
        if (id != null) {
        	profesor = profesorRepository.buscarPorId(id);
        }
        model.addAttribute("profesor", profesor);

        return "profesor";
    }

    @PostMapping("/profesor")
    public String enviarProfesor(@ModelAttribute Profesor profesor) {

        if (profesor.getId() != 0) {
            profesorRepository.actualizar(profesor);
        } else {
            profesorRepository.guardar(profesor);
        }
        return "redirect:/profesores";
    }

    @GetMapping("/profesor/delete")
    public String borrarProfesor(@RequestParam Integer id) {

        profesorRepository.borrarPorId(id);

        return "redirect:/profesores";
    }
}
