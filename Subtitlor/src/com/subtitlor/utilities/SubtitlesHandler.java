package com.subtitlor.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.subtitlor.beans.Fichier;

public class SubtitlesHandler {
	private ArrayList<Fichier> originalSubtitles = null;
	private ArrayList<Fichier> translatedSubtitles = null;

	public SubtitlesHandler(String chemin, String fileName) {
		originalSubtitles = new ArrayList<Fichier>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(chemin + fileName));
			String line = br.readLine();

			while ((line) != null) {
				if (line.isEmpty()) {
					line = br.readLine();
				} else if (isANumber(line)) {
					Fichier f = new Fichier(fileName);
					int index = Integer.parseInt(line);
					f.setindexLine(index);
					line = br.readLine();

					String timedep, timeFin;
					timedep = line.substring(0, 12);
					timeFin = line.substring(17, 29);
					f.setTimeDep(timedep);
					f.setTimeFin(timeFin);
					line = br.readLine();

					String phraseOrg = line;
					f.setPhraseFr(phraseOrg);
					line = br.readLine();

					if (line == null) {
					} else if (!line.isEmpty()) {
						phraseOrg += " " + line;
						f.setPhraseFr(phraseOrg);
						line = br.readLine();
					} else {
						line = br.readLine();
					}
					originalSubtitles.add(f);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Fichier> TranslatedSubtitlesHandler(String chemin, String fileName) {
		translatedSubtitles = new ArrayList<Fichier>();

		return translatedSubtitles;
	}

	public ArrayList<Fichier> getSubtitles() {
		return originalSubtitles;
	}

	public ArrayList<Fichier> getTranslatedSubtitles() {
		return translatedSubtitles;
	}

	public void setTranslatedSubtitles(ArrayList<Fichier> translatedSubtitles) {
		this.translatedSubtitles = translatedSubtitles;
	}

	/**
	 * Vérifie qu'une chaîne est un nombre entier
	 * 
	 * @param s
	 * @return true si la chaine est un nombre entier
	 */
	boolean isANumber(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i)))
				return false;
		}
		return true;
	}

}
