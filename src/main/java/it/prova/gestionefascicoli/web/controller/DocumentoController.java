package it.prova.gestionefascicoli.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DocumentoController {
	@Autowired
	private DocumentoService documentoService;
	
	@GetMapping("/insert")
	public String insert(Model model) {
		model.addAttribute("insert_documento_attr", new Documento());
		return "documento/insert";
	}
	@PostMapping("/save")
	public String save() {
		
	}
}
