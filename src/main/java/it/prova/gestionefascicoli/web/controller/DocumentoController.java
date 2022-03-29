package it.prova.gestionefascicoli.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionefascicoli.dto.DocumentoDTO;
import it.prova.gestionefascicoli.dto.FascicoloDTO;
import it.prova.gestionefascicoli.model.Documento;
import it.prova.gestionefascicoli.service.DocumentoService;

@Controller
@RequestMapping(value = "/documento")
public class DocumentoController {
	@Autowired
	private DocumentoService documentoService;

	@GetMapping("/insert")
	public String insert(Model model) {
		DocumentoDTO documento = new DocumentoDTO();
		documento.setFascicolo(new FascicoloDTO());
		model.addAttribute("insert_documento_attr", documento);
		
		return "documento/insert";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("insert_documento_attr") DocumentoDTO documentoDTO, 
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		//System.out.println(documentoDTO);
		
		if (result.hasErrors())
			return "documento/insert";

		documentoService.inserisciNuovo(documentoDTO.buildDocumentoModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/documento";
	}
	
	

}
