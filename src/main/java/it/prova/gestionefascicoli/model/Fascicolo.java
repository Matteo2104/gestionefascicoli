package it.prova.gestionefascicoli.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fascicolo")
public class Fascicolo {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fascicolo")
	private Set<Documento> documenti = new HashSet<>();

	public Fascicolo() {
		super();
	}

	public Fascicolo(Long id) {
		super();
		this.id = id;
	}

	public Fascicolo(Long id, String codice, String descrizione, Date dataCreazione) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
	}

	public Fascicolo(Long id, String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public Fascicolo(Long id, String codice, String descrizione, Date dataCreazione, Set<Documento> documenti) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.documenti = documenti;
	}

	public Fascicolo(Long id, String codice, String descrizione, Date dataCreazione, Date dataUltimaModifica,
			Set<Documento> documenti) {
		super();
		this.id = id;
		this.codice = codice;
		this.descrizione = descrizione;
		this.dataCreazione = dataCreazione;
		this.dataUltimaModifica = dataUltimaModifica;
		this.documenti = documenti;
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

	public Set<Documento> getDocumenti() {
		return documenti;
	}

	public void setDocumenti(Set<Documento> documenti) {
		this.documenti = documenti;
	}

}
