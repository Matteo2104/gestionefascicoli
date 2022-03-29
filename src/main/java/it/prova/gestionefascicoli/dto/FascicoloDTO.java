package it.prova.gestionefascicoli.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.gestionefascicoli.model.Fascicolo;

public class FascicoloDTO {

	private Long id;
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	private Date dataCreazione;

	private Date dataUltimaModifica;

	public FascicoloDTO() {
		super();
	}

	public FascicoloDTO(Long id) {
		super();
		this.id = id;
	}

	public FascicoloDTO(Long id, String codice, String descrizione, Date dataCreazione) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
	}

	public FascicoloDTO(Long id, String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public Fascicolo buildFascicoloModel() {
		return new Fascicolo(this.id, this.codice, this.descrizione, this.dataCreazione, this.dataUltimaModifica);
	}

	public static FascicoloDTO buildFascicoloDTOFromModel(Fascicolo fascicoloModel) {
		return new FascicoloDTO(fascicoloModel.getId(), fascicoloModel.getCodice(), fascicoloModel.getDescrizione(),
				fascicoloModel.getDataCreazione(), fascicoloModel.getDataUltimaModifica());
	}

	public static List<FascicoloDTO> createFascicoloDTOListFromModelList(List<Fascicolo> modelListInput) {
		return modelListInput.stream().map(fascicoloEntity -> {
			return FascicoloDTO.buildFascicoloDTOFromModel(fascicoloEntity);
		}).collect(Collectors.toList());
	}

}
