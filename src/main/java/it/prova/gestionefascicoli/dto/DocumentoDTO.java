package it.prova.gestionefascicoli.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import it.prova.gestionefascicoli.model.Documento;

public class DocumentoDTO {
	private Long id;

	@NotBlank(message = "{codice.notblank}")
	private String codice;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	private Date dataCreazione;

	private Date dataUltimaModifica;

	private Boolean riservato;

	private byte[] payload;

	@NotNull(message = "{fascicolo.notnull}")
	private FascicoloDTO fascicolo;

	public DocumentoDTO() {
		super();
	}

	public DocumentoDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public DocumentoDTO(String codice, String descrizione, Date dataCreazione) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
	}

	public DocumentoDTO(Long id, String codice, String descrizione, Date dataCreazione) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
	}

	public DocumentoDTO(String codice, String descrizione, Date dataCreazione, Boolean riservato) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.riservato = riservato;
	}

	public DocumentoDTO(Long id, String codice, String descrizione, Date dataCreazione, Boolean riservato) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.riservato = riservato;
	}

	public DocumentoDTO(Long id, String codice, String descrizione, Date dataCreazione, Boolean riservato,
			byte[] payload) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.riservato = riservato;
		this.payload = payload;
	}

	public DocumentoDTO(String codice, String descrizione, Date dataCreazione, Boolean riservato, byte[] payload) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.riservato = riservato;
		this.payload = payload;
	}

	public DocumentoDTO(Long id, String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica,
			Boolean riservato, byte[] payload, FascicoloDTO fascicolo) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
		this.riservato = riservato;
		this.payload = payload;
		this.fascicolo = fascicolo;
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

	public Boolean getRiservato() {
		return riservato;
	}

	public void setRiservato(Boolean riservato) {
		this.riservato = riservato;
	}

	public byte[] getPayload() {
		return payload;
	}

	public void setPayload(byte[] payload) {
		this.payload = payload;
	}

	public Documento buildDocumentoModel() {
		return new Documento(this.id, this.codice, this.descrizione, this.dataCreazione, this.dataUltimaModifica,
				this.riservato, this.fascicolo.buildFascicoloModel());
	}

	public static DocumentoDTO buildDocumentoDTOFromModel(Documento documentoModel) {
		return new DocumentoDTO(documentoModel.getId(), documentoModel.getCodice(), documentoModel.getDescrizione(),
				documentoModel.getDataCreazione(), documentoModel.getDataUltimaModifica(),
				documentoModel.getRiservato(), documentoModel.getFileAllegato(),
				FascicoloDTO.buildFascicoloDTOFromModel(documentoModel.getFascicolo()));
	}

	public static List<DocumentoDTO> createDocumentoDTOListFromModelList(List<Documento> modelListInput) {
		return modelListInput.stream().map(documentoEntity -> {
			return DocumentoDTO.buildDocumentoDTOFromModel(documentoEntity);
		}).collect(Collectors.toList());
	}

}
