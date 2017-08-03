/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dto;

import java.sql.Date;

/**
 *
 * @author Vali
 */
public class ChantierDto extends EntityDto<Integer> {
    private int idConducteur;
    private int idOuvrier;
    private int idClient;
    private int idDevis;
    private String localisation;
    private String designationProjet;
    private String commentaire;
    private Date dateCreationProjet;
    private Date dateDebutPrevue;
    private Date dateDebutEffective;
    private Date dateFinPrevue;
    private Date dateFinEffective;

    public ChantierDto(int idChantier, int idConducteur, int idOuvrier, int idClient, int idDevis, String localisation, String designationProjet, String commentaire, Date dateCreationProjet, Date dateDebutPrevue, Date dateFinPrevue) {
        this.id = idChantier;
        this.idConducteur = idConducteur;
        this.idOuvrier = idOuvrier;
        this.idClient = idClient;
        this.idDevis = idDevis;
        this.localisation = localisation;
        this.designationProjet = designationProjet;
        this.commentaire = commentaire;
        this.dateCreationProjet = dateCreationProjet;
        this.dateDebutPrevue = dateDebutPrevue;
        this.dateFinPrevue = dateFinPrevue;
    }

    public int getIdConducteur() {
        return idConducteur;
    }

    public int getIdOuvrier() {
        return idOuvrier;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdDevis() {
        return idDevis;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getDesignationProjet() {
        return designationProjet;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getDateCreationProjet() {
        return dateCreationProjet;
    }

    public Date getDateDebutPrevue() {
        return dateDebutPrevue;
    }

    public Date getDateDebutEffective() {
        return dateDebutEffective;
    }

    public Date getDateFinPrevue() {
        return dateFinPrevue;
    }

    public Date getDateFinEffective() {
        return dateFinEffective;
    }
    
    
    
}
