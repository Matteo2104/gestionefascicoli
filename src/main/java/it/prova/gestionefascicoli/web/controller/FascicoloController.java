package it.prova.gestionefascicoli.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.prova.gestionefascicoli.dto.FascicoloDTO;
import it.prova.gestionefascicoli.service.FascicoloService;

@Controller
@RequestMapping(value = "/fascicolo")
public class FascicoloController {
	@Autowired
	private FascicoloService fascicoloService;

	@GetMapping
	public String listAll(Model model) {

		model.addAttribute("list_fascicolo_attr", fascicoloService.listAllElements());

		return "list";
	}

	// CICLO RICERCA
	@GetMapping("/search")
	public String search() {
		return "fascicolo/search";
	}

	@PostMapping("/find")
	public String find(FascicoloDTO example, Model model) {
		model.addAttribute("list_fascicolo_attr",
				fascicoloService.findByExample(example.buildFascicoloModel(), null, null, null));
		return "fascicolo/list";
	}

}
