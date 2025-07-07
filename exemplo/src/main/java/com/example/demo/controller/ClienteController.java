package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repo;

    @GetMapping
    public String listar(Model model) {
        List<Cliente> clientes = repo.findAll();
        model.addAttribute("clientes", clientes);
        return "lista-clientes";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro-cliente";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente) {
        repo.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{cpf}")
    public String editar(@PathVariable String cpf, Model model) {
        Cliente c = repo.findById(cpf).orElseThrow();
        model.addAttribute("cliente", c);
        return "cadastro-cliente";
    }

    @GetMapping("/excluir/{cpf}")
    public String excluir(@PathVariable String cpf) {
        repo.deleteById(cpf);
        return "redirect:/clientes";
    }
}