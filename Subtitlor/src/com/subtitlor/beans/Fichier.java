package com.subtitlor.beans;

public class Fichier {
	private String nomFichier;
	private int indexLine;
	private String timeDep;
	private String timeFin;
	private String phraseFr;
	private String phraseAng;

	public Fichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public int getindexLine() {
		return indexLine;
	}

	public void setindexLine(int indexLine) {
		this.indexLine = indexLine;
	}

	public String getTimeDep() {
		return timeDep;
	}

	public void setTimeDep(String timeDep) {
		this.timeDep = timeDep;
	}

	public String getTimeFin() {
		return timeFin;
	}

	public void setTimeFin(String timeFin) {
		this.timeFin = timeFin;
	}

	public String getPhraseFr() {
		return phraseFr;
	}

	public void setPhraseFr(String phraseFr) {
		this.phraseFr = phraseFr;
	}

	public String getPhraseAng() {
		return phraseAng;
	}

	public void setPhraseAng(String phraseAng) {
		this.phraseAng = phraseAng;
	}

}
