package com.subtitlor.dao;

import java.util.List;

import com.subtitlor.beans.Fichier;

public interface FichierDao {
	public void creationFichier( List<Fichier> listfichier ) throws DaoException;
	public void insertTraduction(String nomFichier, String[] traductions) throws DaoException;
    public List<String> listFichiers() throws DaoException;
    public List<Fichier> AfficherLeFichier( String nomFichier) throws DaoException;
	boolean contentFichier(String nomFichier) throws DaoException;
    
}
