package com.subtitlor.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.subtitlor.beans.Fichier;

public class ExportFichier {
	String nomFichier;
	File file;

	public ExportFichier(String nomFichier) {
		nomFichier = nomFichier.split("\\.")[0]; // enlève l'extension
		this.nomFichier = nomFichier + "_ENG.srt";
	}

	public void export(ArrayList<Fichier> MonFichierTraduit) throws IOException, ExportFichierException {
		BufferedWriter bw = null;
		try {
			// Création d'un chemin sous tous les OS
			String dir = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "MySrtFiles";
			final File file = new File(dir);
			file.mkdirs();// make asked directories
			// Création du fichier avec son nom
			File newfile = new File(file, nomFichier);
			if (!newfile.exists()) {
				newfile.createNewFile();
			}
			FileWriter fw = new FileWriter(newfile);
			bw = new BufferedWriter(fw);
			for (Fichier item : MonFichierTraduit) {
				bw.write(Integer.toString(item.getindexLine()));// cast en string
				bw.newLine();
				bw.write(item.getTimeDep() + " --> " + item.getTimeFin());
				bw.newLine();
				bw.write(item.getPhraseAng());
				bw.newLine();
				bw.newLine();
			}

		} catch (IOException ioe) {
			throw new ExportFichierException("L'export à échoué");
			// ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				throw new ExportFichierException("L'export à échoué");
				// System.out.println("Error in closing the BufferedWriter" + ex);
			}
		}

	}

}
