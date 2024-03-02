package metier.entities;

import java.io.Serializable;

public class Instrument implements Serializable {
	private Long idInstrument;
	private String nomInstrument;
	private double prix;

	public Instrument() {
		super();
	}

	public Instrument(String nomInstrument, double prix) {
		super();
		this.nomInstrument = nomInstrument;
		this.prix = prix;
	}

	public Long getIdInstrument() {
		return idInstrument;
	}

	public void setIdInstrument(Long idInstrument) {
		this.idInstrument = idInstrument;
	}

	public String getNomInstrument() {
		return nomInstrument;
	}

	public void setNomInstrument(String nomInstrument) {
		this.nomInstrument = nomInstrument;
	}

	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return "Instrument [idInstrument=" + idInstrument + ", nomInstrument=" + nomInstrument + ", prix=" + prix + "]";
	}

	
}