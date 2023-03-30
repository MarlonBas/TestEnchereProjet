-- Script de création de la base de données ProjetEncheres
-- TYPE : MySQL

create database if not exists ProjetEncheres;
use ProjetEncheres;

create table adresses (
	id_adresse		INTEGER auto_increment not null primary key,
	rue 			VARCHAR(30) not null,
	code_postal 	integer not null,
	ville			varchar(30) not null
);

create table utilisateurs (
	no_utilisateur		integer auto_increment not null primary key,
	pseudo				varchar(30) not null unique,
	nom 				varchar(30) not null,
	prenom 				varchar(30) not null,
	email 				varchar(50) not null unique,
	telephone			varchar(15),
	mot_de_passe		varchar(50) not null,
	credit				integer not null,
	administrateur bit not null,
    id_adresse 			integer not null,
    Constraint fk_adresse_utilisateur FOREIGN KEY (id_adresse) REFERENCES ADRESSES (id_adresse)
);

create table categories (
	id_categorie 		integer auto_increment not null primary key,
    libelle 			varchar(30) not null
);

create table articles (
	id_article			integer auto_increment not null primary key,
	no_utilisateur		integer not null,
    id_adresse			integer not null,
    id_categorie		integer not null,
    nom_article			varchar(70) not null,
    descr_article 		varchar(200),
    date_debut_encheres	date not null,
    date_fin_encheres	date not null,
    mise_a_prix			integer not null,
    prix_vente			integer not null,
    etat_vente			varchar(20),
    
    Constraint fk_articles_utilisateurs FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur),
    Constraint fk_adresses_utilisateurs FOREIGN KEY (id_adresse) REFERENCES ADRESSES (id_adresse),
    Constraint fk_categories_utilisateurs FOREIGN KEY (id_categorie) REFERENCES CATEGORIES (id_categorie),
	CONSTRAINT CK_etat_vente check (etat_vente in ('termine','en cours','pas debute'))
    
);

create table encheres (
	id_enchere			integer auto_increment not null primary key,
    no_utilisateur 		integer not null,
    id_article			integer not null,
	date_enchere		date not null,
    montant_enchere 	integer not null,
    
    Constraint fk_encheres_utilisateurs FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur),
    Constraint fk_encheres_articles FOREIGN KEY (id_article) REFERENCES ARTICLES (id_article)
);


