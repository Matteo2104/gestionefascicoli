package it.prova.gestionefascicoli.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.prova.gestionefascicoli.dto.DocumentoDTO;
import it.prova.gestionefascicoli.model.Documento;
import it.prova.gestionefascicoli.service.DocumentoService;

@Controller
@RequestMapping(value = "/documento")
public class DocumentoController {
	@Autowired
	private DocumentoService documentoService;

	@GetMapping
	public ModelAndView listAllDocumenti() {
		ModelAndView mv = new ModelAndView();
		List<Documento> fascicoli = documentoService.listAllElements();
		mv.addObject("documento_list_attribute", DocumentoDTO.createDocumentoDTOListFromModelList(fascicoli));
		mv.addObject("path", "gestioneDocumenti");
		mv.setViewName("documento/list");
		return mv;
	}

	// CICLO RICERCA
	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("path", "gestioneDocumenti");
		return "documento/search";
	}

	@PostMapping("/list")
	public String listDocumenti(DocumentoDTO documentoExample, @RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "id") String sortBy,
			ModelMap model) {
		List<Documento> dipendenti = documentoService
				.findByExample(documentoExample.buildDocumentoModel(true, true), pageNo, pageSize, sortBy).getContent();
		model.addAttribute("documento_list_attribute", DocumentoDTO.createDocumentoDTOListFromModelList(dipendenti));
		model.addAttribute("path", "gestioneDocumenti");
		return "documento/list";
	}

	@GetMapping("/show/{idDocumento}")
	public String showDocumento(@PathVariable(required = true) Long idDocumento, Model model) {
		model.addAttribute("show_documento_attr",
				DocumentoDTO.buildDocumentoDTOFromModel(documentoService.caricaSingoloElementoEager(idDocumento)));
		model.addAttribute("path", "gestioneDocumenti");

		return "documento/show";
	}

	@GetMapping("/insert")
	public String create(Model model) {
		model.addAttribute("documento_totali_attr",
				DocumentoDTO.createDocumentoDTOListFromModelList(documentoService.listAllElements()));
		model.addAttribute("insert_documento_attr", new DocumentoDTO());
		model.addAttribute("path", "gestioneDocumenti");
		return "documento/insert";
	}

	// per la validazione devo usare i groups in quanto nella insert devo validare
	// la pwd, nella edit no
	@PostMapping("/save")
	public String save(@Validated @ModelAttribute("insert_documento_attr") DocumentoDTO documentoDTO,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {
			DocumentoDTO.createDocumentoDTOListFromModelList(documentoService.listAllElements());
			return "documento/insert";
		}

		if (documentoDTO.getFascicolo().getId() == null) {
			DocumentoDTO.createDocumentoDTOListFromModelList(documentoService.listAllElements());
			model.addAttribute("errorMessage", "È necessario inserire un fascicolo");
			return "documento/insert";
		}
		documentoService.inserisciNuovo(documentoDTO.buildDocumentoModel(true, true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/documento";
	}

	@GetMapping("/showAttachment/{idDocumento}")
	public ResponseEntity<byte[]> showAttachment(@PathVariable(required = true) Long idDocumento) {

		Documento file = documentoService.caricaSingoloElemento(idDocumento);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getCodice() + "\"")
				.body(file.getFileAllegato());

	}

	// CICLO MODIFICA
	@GetMapping("/edit/{idDocumento}")
	public String edit(@PathVariable(required = true) Long idDocumento, Model model) {
		DocumentoDTO documentoDTO = DocumentoDTO
				.buildDocumentoDTOFromModel(documentoService.caricaSingoloElemento(idDocumento));

		// System.out.println(richiestaPermesso);

		model.addAttribute("edit_documento_attr", documentoDTO);
		model.addAttribute("errorMessage", "Operazione non autorizzata!!");
		return "permesso";
	}

	@PostMapping("/update")
	public String update(@ModelAttribute("edit_documento_attr") DocumentoDTO documentoDTO, BindingResult result,
			Model model, RedirectAttributes redirectAttrs, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "permesso/edit";
		}

		documentoService.aggiorna(documentoDTO.buildDocumentoModel(true, true));

		redirectAttrs.addFlashAttribute("successMessage", "Operazione eseguita correttamente");
		return "redirect:/documento";
	}

}
