package it.prova.gestionefascicoli.model;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "codice")
	private String codice;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "datacreazione")
	private Date dataCreazione;
	@Column(name = "dataultimamodifica")
	private Date dataUltimaModifica;
	@Column(name = "riservato")
	private Boolean riservato;

	@Lob
	private byte[] fileAllegato;

	public byte[] getFileAllegato() {
		return fileAllegato;
	}

	public void setFileAllegato(byte[] fileAllegato) {
		this.fileAllegato = fileAllegato;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fascicolo_id", nullable = false)
	private Fascicolo fascicolo;

	public Documento() {
		super();
	}

	public Documento(Long id) {
		super();
		this.id = id;
	}

	public Documento(String codice, String descrizione, Date dataCreazione, Boolean riservato, Fascicolo fascicolo) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.riservato = riservato;
		this.fascicolo = fascicolo;
	}

	public Documento(Long id, String codice, String descrizione, Date dataCreazione, Boolean riservato,
			Fascicolo fascicolo) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.riservato = riservato;
		this.fascicolo = fascicolo;
	}

	public Documento(Long id, String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica,
			Boolean riservato, Fascicolo fascicolo) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
		this.riservato = riservato;
		this.fascicolo = fascicolo;
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

	public Boolean getRiservato() {
		return riservato;
	}

	public void setRiservato(Boolean riservato) {
		this.riservato = riservato;
	}

	public Fascicolo getFascicolo() {
		return fascicolo;
	}

	public void setFascicolo(Fascicolo fascicolo) {
		this.fascicolo = fascicolo;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", dataCreazione="
				+ dataCreazione + ", dataUltimaModifica=" + dataUltimaModifica + ", riservato=" + riservato
				+ ", fileAllegato=" + Arrays.toString(fileAllegato) + ", fascicolo=" + fascicolo + "]";
	}

}
