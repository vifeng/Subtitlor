package com.subtitlor.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.subtitlor.dao.DaoException;
import com.subtitlor.dao.DaoFactory;
import com.subtitlor.dao.FichierDao;
import com.subtitlor.utilities.SubtitlesHandler;
import com.subtitlor.utilities.UploadFichiers;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FichierDao fichierDao;
	private String fileName;
	private final static String CheminUploads = "/Users/Virg/git/Subtitlor/Subtitlor/WebContent/WEB-INF/uploads/";
//	 A adapter à votre installation (Dans l'onglet package Explorer, faire un
//	 clique droit sur le dossier upload, qui est dans WEB-INF,
//	 puis faire show in /Terminal dans l'onglet terminal taper : pwd - le chemin
//	 du dossier s'affiche, copiez le dans le String CheminUploads et y ajouter le
//	 "/" à la fin). Reproduire la même chose dans web.xml mais en pointant sur
//	 upload/tmp/

	String CheminNomFichier;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.fichierDao = daoFactory.getFichierDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("listFichiers", fichierDao.listFichiers());
		} catch (DaoException e) {
			request.setAttribute("errorFile", e.getMessage());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		CheminNomFichier = "/WEB-INF/uploads/";

		if (request.getParameter("buttonChoose") != null) {
			fileName = request.getParameter("selectFichier");
			CheminNomFichier += fileName;
			session.setAttribute("fileName", fileName);
			session.setAttribute("CheminNomFichier", CheminNomFichier);
			this.getServletContext().getRequestDispatcher("/edit").forward(request, response);
		} else if (request.getParameter("buttonUpload") != null) {
			UploadFichiers env = new UploadFichiers();
			env.envoif(request, CheminUploads);
			request.setAttribute("env", env);
			boolean contentFichier;

			try {
				contentFichier = fichierDao.contentFichier(env.getNomFichier());
				SubtitlesHandler subtitles = new SubtitlesHandler(CheminUploads, env.getNomFichier());
				try {
					fichierDao.creationFichier(subtitles.getSubtitles());
					request.setAttribute("successFile", env.getNomFichier());
				} catch (DaoException e) {
					request.setAttribute("errorFile", e.getMessage());
				}
			} catch (DaoException e1) {
				request.setAttribute("errorFile", e1.getMessage());
			}

			try {
				request.setAttribute("listFichiers", fichierDao.listFichiers());
			} catch (DaoException e) {
				request.setAttribute("errorFile", e.getMessage());
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);

		}

	}

}
