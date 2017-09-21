<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Home</title>

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<%@ include file="css.jsp"%>
<!-- j'inclus le css comme ça car j'ai fait un appel à un cdn pour aller plus vite. Je ne peux pas faire appel à un fichier *.css qui écraserait celui par défaut du cdn, donc j'insère le css directement dans le html via une balise style -->

<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<body class="home">
	<div class="container-full">
		<div class="row">
			<div class="col-lg-12 text-center v-center ">
				<h1 class="home">Traduisez des sous-titres</h1>
				<p class="lead">Application web pour la traduction de fichiers
					de sous-titres</p>
				<p>Choisissez un fichier dans la liste ou télécharger en un.
					Faites votre traduction, sauvegarder et exporter le fichier.</p>
			</div>

		</div>
		<!-- /row -->

		<br> <br> <br>
	</div>
	<!-- /container full -->

	<div class="container">

		<hr>

		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>Choisissez un fichier</h3>
					</div>
					<div class="panel-body">
						Choisissez parmi nos fichiers.
						<form method="post" action="home">
							<select name="selectFichier">
								<c:forEach items="${listFichiers}" var="item">
									<option value="${item}">${item}</option>
								</c:forEach>
							</select>
							<button type="submit" name="buttonChoose" id="valider"
								class="btn btn-sm">
								<span class="glyphicon glyphicon-thumbs-up"></span> Valider
							</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>Télécharger votre fichier</h3>
					</div>
					<div class="panel-body">
						<div>
							<form method="post" action="home" enctype="multipart/form-data">
								<p>
									Votre fichier doit porté l'extension .srt et être formater de
									la manière suivante : <br>
								<div class="well well-sm">
									1<br> 00:00:00,535 --> 00:00:02,462<br> Un texte à
									traduire sur une ou plusieur ligne<br>
								</div>

								<input type="file" name="fichier" id="fichier" />
								</p>

								<button type="submit" name="buttonUpload" id="telecharger"
									class="btn btn-sm" />
								<span class="glyphicon glyphicon-cloud-upload"></span>
								Télécharger
								</button>
								<c:choose>
									<c:when test="${ !empty successFile }">
										<span class="alert alert-success successFile">Le
											fichier ${ env.nomFichier } a été uploadé !</></span>
									</c:when>
									<c:when test="${ !empty errorFile }">
										<span class="alert alert alert-warning errorFile">${errorFile}</span>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>

							</form>
						</div>
						<!--  -->
					</div>
				</div>
			</div>

		</div>


	</div>
</body>
</html>