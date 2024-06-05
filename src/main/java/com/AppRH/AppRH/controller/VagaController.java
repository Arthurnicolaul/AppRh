package com.AppRH.AppRH.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.model.Candidato;
import com.AppRH.AppRH.model.Vaga;
import com.AppRH.AppRH.repository.CandidatoRepository;
import com.AppRH.AppRH.repository.VagaRepository;

@Controller
public class VagaController {
    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private VagaRepository vagaRepository;  

    // CADASTRAR VAGA
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.GET) 
    public String form(){
        return "vaga/formVaga";
    }
 
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST) 
    public String form(@Validated Vaga vaga, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }

        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    // LISTAR VAGAS
    @RequestMapping("/vagas")
    public ModelAndView listarVagas(){
        ModelAndView mv = new ModelAndView("vaga/listasVaga");
        Iterable<Vaga> vagas = vagaRepository.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    @RequestMapping(value = "/detalhesVaga/{id}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("id") long id){
        Vaga vaga = vagaRepository.findById(id);
        ModelAndView mv = new ModelAndView("/detalhesVaga");
        mv.addObject("vaga", vaga);

        Iterable<Candidato> candidatos = candidatoRepository.findByVaga(vaga);
        mv.addObject("candidatos", candidatos);
        return mv;
    }

    // EXCLUIR VAGA
    @RequestMapping("/deletarVaga/{id}")
    public String deletarVaga(@PathVariable("id") long id){
        Vaga vaga = vagaRepository.findById(id);
        vagaRepository.delete(vaga);
        return "redirect:/vagas";
    }

    // ATUALIZAR CANDIDATO
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String detalhesVagaPost(@PathVariable("id") long id, @Validated Candidato candidato, BindingResult result, RedirectAttributes attributes){
        
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/{id}";
        }
        // RG DUPLICADO
        if(candidatoRepository.findByRg(candidato.getRg()) != null){
            attributes.addFlashAttribute("mensagem", "RG já cadastrado!");
            return "redirect:/vaga/{id}";
        }

        Vaga vaga = vagaRepository.findById(id);
        candidato.setVaga(vaga);
        candidatoRepository.save(candidato);
        attributes.addFlashAttribute("mensagem", "Candidato cadastrado com sucesso!");
        return "redirect:/{id}";
    }

    // EXCLUIR CANDIDATO PELO RG
    @RequestMapping("/deletarCandidato")
    public String deletarCandidato(String rg){
        Candidato candidato = candidatoRepository.findByRg(rg);
        Vaga vaga = candidato.getVaga();
        String id = "" + vaga.getId();
        candidatoRepository.delete(candidato);
        return "redirect:/" + id;
    }

    // ATUALIZAR VAGA
    // FORMULÁRIO EDIÇÃO DE VAGA
    @RequestMapping(value = "/editarVaga/{id}", method = RequestMethod.GET)
    public ModelAndView editarVaga(@PathVariable("id") long id){
        Vaga vaga = vagaRepository.findById(id);
        ModelAndView mv = new ModelAndView("vaga/updateVaga");
        mv.addObject("vaga", vaga);
        return mv;
    }

    // UPDATE VAGA
    @RequestMapping(value = "/editarVaga", method = RequestMethod.POST)
    public String updateVaga(@Validated Vaga vaga, BindingResult result, RedirectAttributes attributes){
        vagaRepository.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga atualizada com sucesso!");
        
        long codigoLong = vaga.getId();
        String codigo = String.valueOf(codigoLong);
        return "redirect:/" + codigo;
    }
}
