package it.prova.gestionefascicoli.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionefascicoli.dto.DocumentoDTO;
import it.prova.gestionefascicoli.service.DocumentoService;

@Controller
public class DocumentoController {
	@Autowired
	private DocumentoService documentoService;
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_documento_attr", new DocumentoDTO());
		return "documento/insert";
	}
	@PostMapping("/save")
	public String save(@Valid 
			@ModelAttribute("insert_documento_attr") DocumentoDTO documentoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		
		if (result.hasErrors()) 
			return "documento/insert";
		
		documentoService.inserisciNuovo(documentoDTO.buildDocumentoModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/documento";
	}
}
