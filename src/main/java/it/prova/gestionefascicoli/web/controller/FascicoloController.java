package it.prova.gestionefascicoli.web.controller;

<<<<<<< Updated upstream
import javax.validation.Valid;
=======
import java.util.List;
>>>>>>> Stashed changes

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
>>>>>>> Stashed changes
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionefascicoli.dto.FascicoloDTO;
import it.prova.gestionefascicoli.model.Fascicolo;
import it.prova.gestionefascicoli.service.FascicoloService;

@Controller
@RequestMapping(value = "/fascicolo")
public class FascicoloController {
	@Autowired
	private FascicoloService fascicoloService;

	@GetMapping
	public ModelAndView listAllDipendenti() {
		ModelAndView mv = new ModelAndView();
		List<Fascicolo> fascicoli = fascicoloService.listAllElements();
		mv.addObject("fascicolo_list_attribute", FascicoloDTO.createFascicoloDTOListFromModelList(fascicoli));
		mv.addObject("path", "gestioneFascicoli");
		mv.setViewName("fascicolo/list");
		return mv;
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

<<<<<<< Updated upstream
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
=======
	@GetMapping("/show/{idFascicolo}")
	public String showDipendente(@PathVariable(required = true) Long idFascicolo, Model model) {
		model.addAttribute("show_fascicolo_attr",
				FascicoloDTO.buildFascicoloDTOFromModel(fascicoloService.caricaSingoloElementoEager(idFascicolo)));
		model.addAttribute("path", "gestioneFascicoli");

		return "fascicolo/show";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("fascicolo_totali_attr",
				FascicoloDTO.createFascicoloDTOListFromModelList(fascicoloService.listAllElements()));
		model.addAttribute("insert_fascicolo_attr", new FascicoloDTO());
		model.addAttribute("path", "gestioneFascicoli");
		return "fascicolo/insert";
	}

	// per la validazione devo usare i groups in quanto nella insert devo validare
	// la pwd, nella edit no
	@PostMapping("/save")
	public String save(@Validated @ModelAttribute("insert_fascicolo_attr") FascicoloDTO fascicoloDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			FascicoloDTO.createFascicoloDTOListFromModelList(fascicoloService.listAllElements());

			return "fascicolo/insert";
		}
		fascicoloService.inserisciNuovo(fascicoloDTO.buildFascicoloModel());
>>>>>>> Stashed changes

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo";
	}

}
