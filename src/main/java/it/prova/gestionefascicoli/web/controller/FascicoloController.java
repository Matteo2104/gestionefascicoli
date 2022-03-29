package it.prova.gestionefascicoli.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.prova.gestionefascicoli.dto.FascicoloDTO;
import it.prova.gestionefascicoli.model.Fascicolo;
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
				FascicoloDTO.createFascicoloDTOListFromModelList(fascicoloService.findByExample(example.buildFascicoloModel(), null, null, null).toList()));
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

}
