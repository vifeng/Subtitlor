package com.subtitlor.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.subtitlor.beans.Fichier;

public class FichierDaoImpl implements FichierDao {
	private DaoFactory daoFactory;

	public FichierDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creationFichier(List<Fichier> listfichier) throws DaoException {
		for (Fichier item : listfichier) {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion.prepareStatement(
						"INSERT INTO fichier(nomFichier, indexLine, timeDep, timeFin, phraseFr) VALUES(?, ?, ?, ?, ?);");
				preparedStatement.setString(1, item.getNomFichier());
				preparedStatement.setInt(2, item.getindexLine());
				preparedStatement.setString(3, item.getTimeDep());
				preparedStatement.setString(4, item.getTimeFin());
				preparedStatement.setString(5, item.getPhraseFr());
				preparedStatement.executeUpdate();
				connexion.commit();
			} catch (SQLException e) {
				try {
					if (connexion != null) {
						connexion.rollback();
					}
				} catch (SQLException e2) {

				}
				throw new DaoException("Impossible de communiquer avec la base de données");
			} finally {
				try {
					if (connexion != null) {
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DaoException("Impossible de communiquer avec la base de données");
				}
			}
		}
	}

	@Override
	public void insertTraduction(String nomFichier, String[] traductions) throws DaoException {
		for (int i = 0; i < traductions.length; i++) {
			Connection connexion = null;
			PreparedStatement preparedStatement = null;
			try {
				connexion = daoFactory.getConnection();
				preparedStatement = connexion
						.prepareStatement("UPDATE fichier SET phraseAng = ?  WHERE indexLine =  ? AND nomFichier = ?;");
				preparedStatement.setString(1, traductions[i]);
				preparedStatement.setLong(2, i + 1); // les index de ligne à traduire commencent à 1
				preparedStatement.setString(3, nomFichier);
				preparedStatement.executeUpdate();
				connexion.commit();
			} catch (SQLException e) {
				try {
					if (connexion != null) {
						connexion.rollback();
					}
				} catch (SQLException e2) {
				}
				throw new DaoException("Impossible de communiquer avec la base de données");
			} finally {
				try {
					if (connexion != null) {
						connexion.close();
					}
				} catch (SQLException e) {
					throw new DaoException("Impossible de communiquer avec la base de données");
				}
			}
		}
	}

	@Override
	public List<String> listFichiers() throws DaoException {
		List<String> lesFichiers = new ArrayList<String>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT DISTINCT nomFichier FROM fichier ORDER BY nomFichier ASC;");

			while (resultat.next()) {
				String nom = resultat.getString("nomFichier");
				lesFichiers.add(nom);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
		return lesFichiers;
	}

	@Override
	public List<Fichier> AfficherLeFichier(String nomFichier) throws DaoException {
		List<Fichier> leFichier = new ArrayList<Fichier>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM fichier WHERE nomFichier = ?;");
			preparedStatement.setString(1, nomFichier);
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				Fichier f = new Fichier(nomFichier);
				f.setindexLine(resultat.getInt("indexLine"));
				f.setTimeDep(resultat.getString("timeDep"));
				f.setTimeFin(resultat.getString("timeFin"));
				f.setPhraseFr(resultat.getString("phraseFr"));
				f.setPhraseAng(resultat.getString("phraseAng"));
				leFichier.add(f);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
		return leFichier;
	}

	@Override
	public boolean contentFichier(String nomFichier) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM fichier WHERE nomFichier = ?;");
			preparedStatement.setString(1, nomFichier);
			resultat = preparedStatement.executeQuery();
			if (resultat.next()) {
				// which is true;
				throw new DaoException("Le fichier est déjà dans la base");
			} else {
				return false;
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de données");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de données");
			}
		}
	}
}
