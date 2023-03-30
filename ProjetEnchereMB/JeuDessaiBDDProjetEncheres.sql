USE ProjetEncheres;

INSERT INTO CATEGORIES (libelle) VALUES ("Informatique");
INSERT INTO CATEGORIES (libelle) VALUES ("Vêtement");
INSERT INTO CATEGORIES (libelle) VALUES ("Electroménager");
INSERT INTO CATEGORIES (libelle) VALUES ("Livre");

INSERT INTO ADRESSES (rue,code_postal,ville) VALUES ("1 rue de l'admin", 00000,"Adminville");
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse) VALUES ("admin","admin","admin","admin@admin.com","0123456789","admin","100000",true,1);

INSERT INTO ADRESSES (rue,code_postal,ville) VALUES ("21 rue du printemps", 13007 ,"Marseille");
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse) VALUES ("YelLow13","Yelrinse","Lowan","Lowan13@gmail.com","0651515151","lowan13","510",false,2);

INSERT INTO ADRESSES (rue,code_postal,ville) VALUES ("5 place de la république", 75015 ,"Paris");
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse) VALUES ("DarkAlex","Petit","Alexandre","darkalex75@gmail.com","0675757575","darkalexdu75","750",false,3);

INSERT INTO ADRESSES (rue,code_postal,ville) VALUES ("17 rue du jardin", 38199 ,"Jardin");
INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,id_adresse) VALUES ("JeanDu38","Dujardin","Jean","jeandu38@gmail.com","0611711738","theartist","117",false,4);

INSERT INTO ADRESSES (rue,code_postal,ville) VALUES ("17 rue du jardin", 38199 ,"Jardin");
INSERT INTO ARTICLES (no_utilisateur, id_adresse, id_categorie, nom_article, descr_article, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente ) VALUES (4,5,3,"Machine à café","Machine à café fonctionnelle état neuf","2023-03-25","2023-04-15",15,20,"en cours");
INSERT INTO ENCHERES (date_enchere, montant_enchere, id_article, no_utilisateur) VALUES ( "2023-03-27",20,1,2);

INSERT INTO ADRESSES (rue,code_postal,ville) VALUES ("21 rue du printemps", 13007 ,"Marseille");
INSERT INTO ARTICLES (no_utilisateur, id_adresse, id_categorie, nom_article, descr_article, date_debut_encheres, date_fin_encheres, mise_a_prix, prix_vente, etat_vente ) VALUES (2,6,4,"Le Temps des Tempêtes","Livre écrit par Nicolas Sarkozy, jamais servi","2023-03-20","2023-04-02",1,1,"en cours");
INSERT INTO ENCHERES (date_enchere, montant_enchere, id_article, no_utilisateur) VALUES ( "2023-03-27",2,2,3);

