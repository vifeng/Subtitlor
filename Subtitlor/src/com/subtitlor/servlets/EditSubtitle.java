package com.subtitlor.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.subtitlor.beans.Fichier;
import com.subtitlor.dao.DaoException;
import com.subtitlor.dao.DaoFactory;
import com.subtitlor.dao.FichierDao;
import com.subtitlor.utilities.ExportFichier;
import com.subtitlor.utilities.ExportFichierException;
import com.subtitlor.utilities.SubtitlesHandler;

@WebServlet("/EditSubtitle")
public class EditSubtitle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FichierDao fichierDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.fichierDao = daoFactory.getFichierDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/edit_sub.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			List<Fichier> subtitles = fichierDao.AfficherLeFichier((String) session.getAttribute("fileName"));
			request.setAttribute("subtitles", subtitles);

			if (request.getParameter("buttonSave") != null) {
				try {
					List<Fichier> subtitles2 = save(request, subtitles, session);
					request.setAttribute("subtitles", subtitles2);
					request.setAttribute("success", "Votre travail a été sauvegardé.");
				} catch (Exception e) {
					request.setAttribute("error", "#ERREUR Votre travail n'a pas été sauvegardé." + e.getMessage());
				}

			} else if (request.getParameter("buttonExport") != null) {
				// sauvegarde traduction
				List<Fichier> subtitles2 = save(request, subtitles, session);
				request.setAttribute("subtitles", subtitles2);
				// EXPORT du fichier
				ArrayList<Fichier> MonFichierTraduit = (ArrayList<Fichier>) fichierDao
						.AfficherLeFichier((String) session.getAttribute("fileName"));
				ExportFichier ex = new ExportFichier((String) session.getAttribute("fileName"));
				ex.export(MonFichierTraduit);
				request.setAttribute("success",
						"Votre travail a été sauvegardé et exporté sur votre bureau dans le dossier MySrtFiles");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/edit_sub.jsp").forward(request, response);
		} catch (DaoException e1) {
			request.setAttribute("error", "Echec de l'opération.");
		} catch (ExportFichierException e) {
			request.setAttribute("error", "L'export à échoué.");
			e.printStackTrace();
		}

	}

	public List<Fichier> save(HttpServletRequest request, List<Fichier> subtitles, HttpSession session) {
		String[] traductions = new String[subtitles.size()];
		for (int index = 0; index < subtitles.size(); index++) {
			String nomIndex = "line" + index;
			String ligneTraduite = request.getParameter(nomIndex);
			traductions[index] = ligneTraduite;
		}
		try {
			fichierDao.insertTraduction((String) session.getAttribute("fileName"), traductions);
			return fichierDao.AfficherLeFichier((String) session.getAttribute("fileName"));
		} catch (DaoException e) {
			request.setAttribute("error", "Votre travail n'a pas été sauvegardé.");
		}
		return null;
	}
}
