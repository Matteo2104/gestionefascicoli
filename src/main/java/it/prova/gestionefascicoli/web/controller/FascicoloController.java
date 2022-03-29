package it.prova.gestionefascicoli.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionefascicoli.dto.FascicoloDTO;
import it.prova.gestionefascicoli.exceptions.FascicoloConDocumentiException;
import it.prova.gestionefascicoli.exceptions.FascicoloNotFoundException;
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
		mv.addObject("list_fascicolo_attr", FascicoloDTO.createFascicoloDTOListFromModelList(fascicoli));
		mv.addObject("path", "gestioneFascicoli");
		mv.setViewName("fascicolo/list");
		return mv;
	}

	// CICLO RICERCA
	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("path", "gestioneFascicoli");
		return "fascicolo/search";
	}

	@PostMapping("/find")
	public String find(FascicoloDTO example, Model model) {

		model.addAttribute("list_fascicolo_attr", FascicoloDTO.createFascicoloDTOListFromModelList(
				fascicoloService.findByExample(example.buildFascicoloModel(), null, null, null).toList()));
		model.addAttribute("path", "gestioneFascicoli");
		return "fascicolo/list";
	}

	@GetMapping(value = "/searchFascicoliAjax", produces = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody String searchFascicolo(@RequestParam String term) {

		List<Fascicolo> listaFascicoliByTerm = fascicoloService.cercaByCodiceILike(term);
		return buildJsonResponse(listaFascicoliByTerm);
	}

	private String buildJsonResponse(List<Fascicolo> listaFascicoli) {
		JsonArray ja = new JsonArray();

		for (Fascicolo fascicoloItem : listaFascicoli) {
			JsonObject jo = new JsonObject();
			jo.addProperty("value", fascicoloItem.getId());
			jo.addProperty("label", fascicoloItem.getCodice());
			ja.add(jo);
		}

		return new Gson().toJson(ja);
	}
	/*
	 * @GetMapping("/show/{idFascicolo}") public String
	 * showDipendente(@PathVariable(required = true) Long idFascicolo, Model model)
	 * { model.addAttribute("show_fascicolo_attr",
	 * FascicoloDTO.buildFascicoloDTOFromModel(fascicoloService.
	 * caricaSingoloElementoEager(idFascicolo))); model.addAttribute("path",
	 * "gestioneFascicoli");
	 * 
	 * return "fascicolo/show"; }
	 */

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

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo";
	}

	// CICLO VISUALIZZA
	@GetMapping("/show/{idFascicolo}")
	public String show(@PathVariable(required = true) Long idFascicolo, Model model) {

		model.addAttribute("show_fascicolo_attr", fascicoloService.caricaSingoloElementoEager(idFascicolo));
		return "fascicolo/show";
	}

	@PostMapping("/delete")
	public String delete(@RequestParam(name = "idFascicoloToDelete", required = true) Long idFascicolo,
			RedirectAttributes redirectAttrs) {

		try {
			fascicoloService.rimuoviFascicolo(idFascicolo);
		} catch (FascicoloNotFoundException e) {
			redirectAttrs.addFlashAttribute("errorMessage", "Impossibile trovare il fascicolo da rimuovere!");
			return "redirect:/fascicolo";
		} catch (FascicoloConDocumentiException e) {
			redirectAttrs.addFlashAttribute("errorMessage",
					"Il fascicolo che tenti di eliminare ha dei documenti associati!");
			return "redirect:/contribuente";
		}
		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/fascicolo";

	}

}
