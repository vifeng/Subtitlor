# ---
# Config MAC
# ---

# Démarrer le serveur dans les préférences
# Dans votre terminal, connectez vous à votre mysql
cd /usr/local/mysql/bin
./mysql -u root -p
# entrer le mot de passe 'root'

use mysql;
SHOW DATABASES;

CREATE DATABASE IF NOT EXISTS jeeSubtitlor DEFAULT CHARACTER SET = utf8 COLLATE = utf8_general_ci;

USE jeeSubtitlor;

CREATE TABLE  IF NOT EXISTS fichier (
	id INT( 11 ) NOT NULL AUTO_INCREMENT,
	indexLine INT( 11 ) NOT NULL,
	nomFichier VARCHAR( 200 ) NOT NULL,
	timeDep VARCHAR( 200 ) NOT NULL,
	timeFin VARCHAR( 200 ) NOT NULL,
	phraseFr VARCHAR( 200 ),
	phraseAng VARCHAR( 200 ),
	PRIMARY KEY ( id )
) ENGINE = INNODB;


-- Pour vérifier que tout est conforme…
show tables;
describe fichier;

# voir les entrées
SELECT *
FROM fichier;

# En cas d'erreur :
# Efface toutes les entrées et ré-initalise l'auto-incrémentation
TRUNCATE TABLE `fichier`;

