package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Agenda;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.ClienteRepository;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping
    public String mostrarAgenda(Model model) {
        model.addAttribute("agenda", new Agenda());
        model.addAttribute("agendamentos", agendaRepo.findAll());
        model.addAttribute("clientes", clienteRepo.findAll());
        return "agenda";
    }

    @PostMapping("/salvar")
    public String salvarAgenda(@ModelAttribute Agenda agenda) {
        agendaRepo.save(agenda);
        return "redirect:/agenda";
    }

    @GetMapping("/editar/{id}")
    public String editarAgenda(@PathVariable Long id, Model model) {
        Agenda agenda = agendaRepo.findById(id).orElseThrow();
        model.addAttribute("agenda", agenda);
        model.addAttribute("clientes", clienteRepo.findAll());
        model.addAttribute("agendamentos", agendaRepo.findAll());
        return "agenda";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgenda(@PathVariable Long id) {
        agendaRepo.deleteById(id);
        return "redirect:/agenda";
    }
}
