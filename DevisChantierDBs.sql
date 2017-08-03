drop table OUVRIER_DU_CHANTIER;
drop table ENGIN_DU_CHANTIER;
drop table MATERIAU_DU_CHANTIER;
drop table PETIT_MATERIEL_DU_CHANTIER;
drop table CODE_REFERENCE_DU_CHANTIER;
drop table VOITURE_DU_CHANTIER;
drop table CAMION_DU_CHANTIER;
drop table CONDUCTEUR_DU_CHANTIER;

drop table CHANTIER;

drop table ENGIN;
drop table MATERIAU;
drop table PETIT_MATERIEL;
drop table CODE_REFERENCE;
drop table VOITURE;
drop table CAMION;
drop table CLIENT;
drop table OUVRIER;
drop table DEVIS;
drop table PATRON;
drop table CONDUCTEUR;
drop table SEQUENCES;

create table ENGIN (
	id_Engin numeric(10) primary key not null,
	nom varchar(20),
	type_ varchar(20),
	reference varchar(20),
	location boolean,
	prixParHeure double
);

create table MATERIAU (
	id_Materiau numeric(10) primary key not null,
	nom varchar(20),
	type_ varchar(20),
	reference varchar(20),
	fourniture varchar(20),
	siteDeProduction varchar(20),
	prixHtva double
);

create table PETIT_MATERIEL (
	id_PetitMateriel numeric(10) primary key not null, 
	nom varchar(20),
	type_ varchar(20),
	reference varchar(20),
	prixHtva double
);

create table CODE_REFERENCE (
	id_CodeReference numeric(10) primary key not null, 
	reference varchar(20),
	typeDeTravail varchar(20),
	prixHtva double
);

create table VOITURE (
	id_voiture numeric(10) primary key not null, 
	attacheRemorque boolean,
	marque varchar(20),
	modele varchar(20),
	numeroDeChassis varchar(30) unique,
	carburant varchar(20),
	ctAmortMois double
	);

create table CAMION (
	id_Camion numeric(10) primary key not null, 
	categorie varchar(20),
	tonnage numeric(10),
	capacite double,
	location boolean,
	marque varchar(20),
	modele varchar(20),
	numeroDeChassis varchar(20) unique,
	carburant varchar(20),
	prixHtva double,
	ctAmortMois double
	);

create table OUVRIER (
	id_Ouvrier numeric(10) primary key not null, 
	nom varchar(20),
	prenom varchar(20),
	dateDeNaissance date,
	numeroTelephone varchar(20) unique,
	Email varchar(20) unique,
	remuneration double,
	permis boolean,
	entreeEnFonction date,
	anciennete numeric(10),
	cout double
	);

create table CLIENT (
	id_Client numeric(10) primary key not null, 
	nom varchar(20),
	prenom varchar(20),
	dateDeNaissance date,
	numeroTelephone varchar(20) unique,
	Email varchar(20) unique
	);
	
create table CONDUCTEUR(
	id_Conducteur numeric(10) primary key not null,
	nom varchar(20),
	prenom varchar(20),
	dateDeNaissance date,
	numeroTelephone varchar(20) unique,
	numeroTelephonePro varchar(20) unique,
	Email varchar(20) unique,
	remuneration double,
	permis boolean,
	entreeEnFonction date,
	anciennete numeric(10),
	cout double
	);
	
create table PATRON (
	id_Patron numeric(10) primary key not null, 
	validationProjet boolean,

        constraint fkPatron foreign key (id_Patron) references CONDUCTEUR(id_Conducteur)
	);
	
create table DEVIS (
	id_Devis numeric(10) primary key not null, 
	designationDevis varchar(20),
	statut varchar(20),
	dateDevis date
	);
	
create table CHANTIER (
	id_Chantier numeric(10) primary key not null, 
        id_Ouvrier numeric(10) not null,
        id_Client numeric(10) not null,
        id_Devis numeric(10) not null,
        id_Conducteur numeric(10) not null,
        localisation varchar(20),
	designationDuProjet varchar(20),
	commentaire varchar(500),
	dateDeCreationProjet date,
	dateDebutPrevu date,
	dateDebutEffective date,
	dateFinPrevu date,
	dateFinEffective date,
        
        constraint fkOuvrier foreign key (id_Ouvrier) references OUVRIER(id_Ouvrier),
        constraint fkClient foreign key (id_Client) references CLIENT(id_Client),
        constraint fkDevis foreign key (id_Devis) references DEVIS(id_Devis),
        constraint fkConducteur foreign key (id_Conducteur) references CONDUCTEUR(id_Conducteur)

	);

create table CONDUCTEUR_DU_CHANTIER (
	id_CONDUCTEUR_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_Conducteur numeric(10) not null,
	dateDebut date,
        dateFin date,
        nombreHeures double,
        
        constraint fkCONDUCTEUR_DU_CHANTIER foreign key (id_Conducteur) references CONDUCTEUR(id_Conducteur),
        constraint fkCONDUCTEUR_DU_CHANTIER2 foreign key (id_Chantier) references CHANTIER(id_Chantier)
	);

create table OUVRIER_DU_CHANTIER (
	id_OUVRIER_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_Ouvrier numeric(10) not null,
	dateDebut date,
        dateFin date,
        nombreHeures double,

        constraint fkOUVRIER_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkOUVRIER_DU_CHANTIER2 foreign key (id_Ouvrier) references OUVRIER(id_Ouvrier)
	
	);

create table ENGIN_DU_CHANTIER (
	id_ENGIN_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_Engin numeric(10) not null,
	debutDisponibilite date,
        finDisponibilite date,
        nombreHeures double,
        quantite double,

        constraint fkENGIN_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkENGIN_DU_CHANTIER2 foreign key (id_Engin) references ENGIN(id_Engin)

	);

create table MATERIAU_DU_CHANTIER (
	id_MATERIAU_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_Materiau numeric(10) not null,
	debutDisponibilite date,
        finDisponibilite date,
        quantite double,

        constraint fkMATERIAU_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkMATERIAU_DU_CHANTIER2 foreign key (id_Materiau) references MATERIAU(id_Materiau)
	);

create table PETIT_MATERIEL_DU_CHANTIER (
	id_PETIT_MATERIEL_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_PetitMateriel numeric(10) not null,
	debutDisponibilite date,
        finDisponibilite date,
        quantite double,

        constraint fkPETIT_MATERIEL_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkPETIT_MATERIEL_DU_CHANTIER2 foreign key (id_PetitMateriel) references PETIT_MATERIEL(id_PetitMateriel)
	);

create table CODE_REFERENCE_DU_CHANTIER (
	id_CODE_DE_REFERENCE_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_CodeReference numeric(10) not null,
        quantite double,

        constraint fkCODE_DE_REFERENCE_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkCODE_DE_REFERENCE_DU_CHANTIER2 foreign key (id_CodeReference) references CODE_REFERENCE(id_CodeReference)
        );

create table VOITURE_DU_CHANTIER (
	id_VOITURE_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_Voiture numeric(10) not null,
        debutDisponibilite date,
        finDisponibilite date,

        constraint fkVOITURE_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkVOITURE_DU_CHANTIER2 foreign key (id_Voiture) references VOITURE(id_Voiture)

        );

create table CAMION_DU_CHANTIER (
	id_CAMION_DU_CHANTIER numeric(10) primary key not null, 
        id_Chantier numeric(10) not null,
        id_Camion numeric(10) not null,
        quantite double,
        nombreHeures double,
        debutDisponibilite date,
        finDisponibilite date,

        constraint fkCAMION_DU_CHANTIER foreign key (id_Chantier) references CHANTIER(id_Chantier),
        constraint fkCAMION_DU_CHANTIER2 foreign key (id_Camion) references CAMION(id_Camion)

        );

create table SEQUENCES (
id varchar(50) primary key not null,
sValue numeric(10) not null
);

Insert into SEQUENCES Values ('CAMION',1);
Insert into SEQUENCES Values ('CAMION_DU_CHANTIER',1);
Insert into SEQUENCES Values ('CHANTIER',1);
Insert into SEQUENCES Values ('CLIENT',1);
Insert into SEQUENCES Values ('CODE_REFERENCE',1);
Insert into SEQUENCES Values ('CODE_REFERENCE_DU_CHANTIER',1);
Insert into SEQUENCES Values ('CONDUCTEUR',1);
Insert into SEQUENCES Values ('CONDUCTEUR_DU_CHANTIER',1);
Insert into SEQUENCES Values ('DEVIS',1);
Insert into SEQUENCES Values ('ENGIN',1);
Insert into SEQUENCES Values ('ENGIN_DU_CHANTIER',1);
Insert into SEQUENCES Values ('MATERIAU',1);
Insert into SEQUENCES Values ('MATERIAU_DU_CHANTIER',1);
Insert into SEQUENCES Values ('OUVRIER',1);
Insert into SEQUENCES Values ('OUVRIER_DU_CHANTIER',1);
Insert into SEQUENCES Values ('PATRON',1);
Insert into SEQUENCES Values ('PETIT_MATERIEL',1);
Insert into SEQUENCES Values ('PETIT_MATERIEL_DU_CHANTIER',1);
Insert into SEQUENCES Values ('VOITURE',1);
Insert into SEQUENCES Values ('VOITURE_DU_CHANTIER',1);