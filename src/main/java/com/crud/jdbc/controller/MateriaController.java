package com.crud.jdbc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.jdbc.model.Materia;
import com.crud.jdbc.repository.MateriaRepository;

@Controller
public class MateriaController {
	
    private MateriaRepository repository = new MateriaRepository();

    @GetMapping("/materias")
    public String buscarTodas(Model model) {

        model.addAttribute("materias", repository.buscarTodos());

        return "materias";
    }

    @GetMapping("/materia")
    public String buscarMateria(Model model,
                            @RequestParam(required = false) Long id) {

        Materia materia = new Materia();
        if (id != null) {
        	materia = repository.buscarPorId(id);
        }
        model.addAttribute("materia", materia);

        return "materia";
    }

    @PostMapping("/materia")
    public String enviarMateria(@ModelAttribute Materia materia) {

        if (materia.getId() != null) {
            repository.actualizar(materia);
        } else {
            repository.guardar(materia);
        }
        return "redirect:/materia";
    }

    @GetMapping("/materia/delete")
    public String borrarMateria(@RequestParam Long id) {

        repository.borrarPorId(id);

        return "redirect:/materias";
    }
}
