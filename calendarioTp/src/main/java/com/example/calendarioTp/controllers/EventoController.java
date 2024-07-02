package com.example.calendarioTp.controllers;

import com.example.calendarioTp.entities.Evento;
import com.example.calendarioTp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("eventos", eventoRepository.findAll());
        return "index";
    }

    @GetMapping("/evento/nuevo")
    public String nuevoEventoForm(Model model) {
        model.addAttribute("evento", new Evento());
        return "nuevoEvento";
    }

    @PostMapping("/evento/guardar")
    public String guardarEvento(@ModelAttribute Evento evento) {
        eventoRepository.save(evento);
        return "redirect:/";
    }

    @GetMapping("/evento/editar/{id}")
    public String editarEventoForm(@PathVariable Long id, Model model) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de evento inválido: " + id));
        model.addAttribute("evento", evento);
        return "editarEvento";
    }

    @PostMapping("/evento/actualizar/{id}")
    public String actualizarEvento(@PathVariable Long id, @ModelAttribute Evento evento) {
        evento.setId(id);
        eventoRepository.save(evento);
        return "redirect:/";
    }

    @GetMapping("/evento/eliminar/{id}")
    public String eliminarEvento(@PathVariable Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id de evento inválido: " + id));
        eventoRepository.delete(evento);
        return "redirect:/";
    }
}
