package it.prova.gestionefascicoli.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("list_fascicolo_attr", FascicoloDTO.createFascicoloDTOListFromModelList(
				fascicoloService.findByExample(example.buildFascicoloModel(), null, null, null).toList()));
		return "fascicolo/list";
	}

	@GetMapping("/insert")
	public String createFascicolo(Model model) {
		model.addAttribute("insert_fascicolo_attr", new FascicoloDTO());
		return "fascicolo/insert";
	}

	@PostMapping("/save")
	public String saveRegista(@Valid @ModelAttribute("insert_fascicolo_attr") FascicoloDTO fascicoloDTO,
			BindingResult result, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			return "fascicolo/insert";
		}
		fascicoloService.inserisciFascicoloConDate(fascicoloDTO.buildFascicoloModel());

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo";
	}

}
