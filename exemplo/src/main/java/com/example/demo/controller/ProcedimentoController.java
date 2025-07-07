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

import com.example.demo.model.Procedimento;
import com.example.demo.repository.ProcedimentoRepository;

@Controller
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoRepository repo;

    @GetMapping
    public String listar(Model model) {
        List<Procedimento> todos = repo.findAll();
        model.addAttribute("procedimentos", todos);
        return "procedimentos";
    }

    @GetMapping("/historico/{cpf}")
    public String historico(@PathVariable String cpf, Model model) {
        List<Procedimento> hist = repo.findByCliente_CpfOrderByHorarioDesc(cpf);
        model.addAttribute("historico", hist);
        return "historico";
    }

    @GetMapping("/novo/{cpf}")
    public String novo(@PathVariable String cpf, Model model) {
        Procedimento p = new Procedimento();
        p.setCliente(new com.example.demo.model.Cliente());
        p.getCliente().setCpf(cpf);
        model.addAttribute("procedimento", p);
        return "form-procedimento";
    }

    @PostMapping("/registrar")
    public String registrar(@ModelAttribute Procedimento procedimento) {
        repo.save(procedimento);
        return "redirect:/procedimentos/historico/" + procedimento.getCliente().getCpf();
    }
}