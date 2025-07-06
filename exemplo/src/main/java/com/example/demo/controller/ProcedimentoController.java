package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Procedimento;
import com.example.demo.repository.ProcedimentoRepository;

@Controller
public class ProcedimentoController {

    private final ProcedimentoRepository procedimentoRepo;

    public ProcedimentoController(ProcedimentoRepository procedimentoRepo) {
        this.procedimentoRepo = procedimentoRepo;
    }

    @GetMapping("/procedimentos")
    public String listarProcedimentos(Model model) {
        List<Procedimento> todos = procedimentoRepo.findAllByOrderByDataDesc();

        // filtra só procedimentos já realizados
        LocalDateTime agora = LocalDateTime.now();
        List<Procedimento> realizados = todos.stream()
            .filter(p -> p.getHorario() != null && p.getHorario().isBefore(agora))
            .toList();

        model.addAttribute("procedimentos", realizados);

        // totais
        LocalDate hoje = LocalDate.now();
        double totalHoje = procedimentoRepo.totalPorDia(hoje);

        // semana corrente
        WeekFields wf = WeekFields.of(Locale.getDefault());
        LocalDate inicioSemana = hoje.with(wf.dayOfWeek(), 1);
        double totalSemana = procedimentoRepo.totalEntreDatas(inicioSemana, hoje);

        // mês corrente
        LocalDate inicioMes = hoje.withDayOfMonth(1);
        double totalMes = procedimentoRepo.totalEntreDatas(inicioMes, hoje);

        model.addAttribute("totalHoje", totalHoje);
        model.addAttribute("totalSemana", totalSemana);
        model.addAttribute("totalMes", totalMes);

        return "procedimentos"; 
    }
}
