package com.subtitlor.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class UploadFichiers {

	String description;
	Part part;
	String nomFichier;
	String nomChamp;
	public final int TAILLE_TAMPON = 10240;

	public void envoif(HttpServletRequest request, String CheminNomFichier) throws IOException, ServletException {
		part = request.getPart("fichier");
		// On vérifie qu'on a bien reçu un fichier
		nomFichier = getNomFichier(part);
		// Si on a bien un fichier
		if (nomFichier != null && !nomFichier.isEmpty() && CheminNomFichier != null && !CheminNomFichier.isEmpty()) {
			nomChamp = part.getName();
			// Corrige un bug du fonctionnement d'Internet Explorer
			nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
					.substring(nomFichier.lastIndexOf('\\') + 1);

			// On écrit définitivement le fichier sur le disque
			ecrireFichier(part, nomFichier, CheminNomFichier);
		}
	}

	private void ecrireFichier(Part part, String nomFichier, String chemin) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

	private String getNomFichier(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public String getNomChamp() {
		return nomChamp;
	}

	public void setNomChamp(String nomChamp) {
		this.nomChamp = nomChamp;
	}

	public int getTAILLE_TAMPON() {
		return TAILLE_TAMPON;
	}

}
