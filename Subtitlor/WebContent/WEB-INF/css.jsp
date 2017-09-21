<!-- j'inclus le css comme ça car j'ai fait un appel à un cdn pour aller plus vite mais donc il faut que j'insère le CSS dans le html. Je ne peux pas faire appel à un fichier css qui écraserait celui par défaut du cdn-->

<style type="text/css">
#masthead {
	padding-top: 50px;
	min-height: 150px;
}

h1.home {
	padding-top: 20px;
}

#masthead h1 {
	font-size: 30px;
	line-height: 1;
	/* padding-top: 20px; */
}

@media screen and (min-width: 768px) {
	#masthead h1 {
		font-size: 50px;
	}
}

.navbar-default {
	background-color: #7badca;
}

.navbar-default .navbar-brand {
	color: white;
}

.navbar-default .navbar-nav>li>a {
	color: white;
}

.navbar-bright {
	background-color: #111155;
	color: #fff;
}

.affix-top, .affix {
	position: static;
}

@media ( min-width : 979px) {
	#sidebar.affix-top {
		position: static;
		margin-top: 30px;
		width: 228px;
	}
	#sidebar.affix {
		position: fixed;
		top: 70px;
		width: 228px;
	}
}

#sidebar li.active {
	border: 0 #eee solid;
	border-right-width: 5px;
}

.traduction {
	padding: 10px;
	width: 100%;
	height: 60px;
}

.container-full {
	margin: 0 auto;
	width: 100%;
	min-height: 100%;
	background-color: #7BADCA;
	color: #eee;
	overflow: hidden;
}

h3 {
	text-align: center;
	color: #296386;
}

.panel-default>.panel-heading {
	background-color: #d2e6ec;
}

div.col-lg-12.text-center.v-center {
	color: #ffffff;
}

button.btn.btn-sm {
	background-color: #7BADCA;
}

.buttonText {
	background: none;
	border: none;
	margin: 0;
	padding: 0;
}

a.alert.alert-success {
	padding-top: 6px;
	padding-right: 6px;
	padding-bottom: 6px;
	padding-left: 6px;
	margin-top: 8px;
	background-color: #d2e6ec;
    border-color:#9cb0bb;
}

.navbar-default .navbar-nav>li>a.alert.alert-success {
	color: #296386;
}

a.alert.alert-warning {
	padding-top: 6px;
	padding-right: 6px;
	padding-bottom: 6px;
	padding-left: 6px;
	margin-top: 8px;
}

.navbar-default .navbar-nav>li>a.alert.alert-warning {
	color: #a94442;
}

.anchor {
	display: block;
	height: 75px; /*same height as header*/
	margin-top: -75px; /*same height as header*/
	visibility: hidden;
}

span.alert.alert.alert-warning.errorFile{
	padding: 8px;
}
span.alert.alert-success.successFile{
	padding: 8px;
}

</style>