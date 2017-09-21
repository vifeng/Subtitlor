<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Editer les sous-titres</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<%@ include file="css.jsp"%>
<!-- j'inclus le css comme ça car j'ai fait un appel à un cdn pour aller plus vite. Je ne peux pas faire appel à un fichier *.css qui écraserait celui par défaut du cdn, donc j'insère le css directement dans le html via une balise style -->


<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="masthead">
		<div class="container">
			<div>
				<h1>
					Traduire <span class="lead"> ${ fileName }</span>
				</h1>
				<p>Votre traduction ne peux pas excéder 140 caractères. Au 35eme
					caractère vos lignes seront automatiquement renvoyer à la ligne.
					Vous pouvez toutefois décider de les renvoyer vous-même à la ligne
					avant cette limite avec un simple retour chariot.</p>

			</div>
		</div>
		<!--/container-->
	</div>
	<!--/masthead-->

	<!--main-->
	<div class="container">
		<div class="row">
			<!--left-->
			<div class="col-md-2" id="leftCol">
				<ul class="nav nav-stacked" id="sidebar">
					<c:forEach var="i" begin="0" end="${ subtitles.size()-1 }" step="1">
						<li><a href="#line${ i }">${ subtitles[i].getTimeDep() }</a></li>
					</c:forEach>
				</ul>
			</div>
			<!--/left-->

			<form method="post" action="edit">

				<!-- début de nav -->

				<nav class="navbar navbar-default navbar-fixed-top" role="banner">
					<div class="container">
						<div class="navbar-header">
							<button class="navbar-toggle" type="button"
								data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
							<a href="home" class="navbar-brand">Accueil</a>
						</div>
						<nav class="collapse navbar-collapse" role="navigation">
							<ul class="nav navbar-nav">
								<li><a><input type="submit" name="buttonSave"
										value="Enregistrer ma traduction" id="save" class="buttonText"></a></li>
								<li><a><input type="submit" name="buttonExport"
										value="Enregistrer et Exporter ma traduction" id="export"
										class="buttonText"></a></li>

								<c:choose>
									<c:when test="${ !empty success }">
										<li><a class="alert alert-success">${ success }</a></li>
									</c:when>
									<c:when test="${ !empty error }">
										<span class="alert alert alert-warning">${error}</span>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>

							</ul>
						</nav>
					</div>
				</nav>
				<!-- fin de nav -->


				<!--right-->
				<div class="row">
					<div class="col-md-8">
						<c:forEach var="i" begin="0" end="${ subtitles.size()-1 }"
							step="1">
							<div class="row">

								<div class="col-md-6">
									<div id="sec0" class="panel panel-default">
										<div class="panel-body">
											<h6>
												<c:out value="${ subtitles[i].getTimeDep() }" />
												-->
												<c:out value="${ subtitles[i].getTimeFin() }" />
											</h6>
											<p>
												<c:out value="${ subtitles[i].getPhraseFr() }" />
											</p>
										</div>
									</div>
								</div>


								<div class="col-md-6">
									<span class="anchor" id="line${ i }"></span>
									<!-- c'est pour caler les sous titres sur le sticky header sinon ils passent en dessous -->
									<p>
										<textarea class="traduction" name="line${ i}" cols="35"
											rows="5" wrap="hard" maxlength="140"
											placeholder="Votre traduction"><c:out value="${ subtitles[i].getPhraseAng() }"/></textarea>
									</p>
								</div>
								<!-- fin col-md-6 -->
							</div>
							<!-- fin row left -->
						</c:forEach>
			</form>
		</div>
		<!-- Fin du div de la col-md8 -->
	</div>
	<!--/container-->
	<script src="edit_js.js" type="text/javascript"></script>
</body>
</html>

