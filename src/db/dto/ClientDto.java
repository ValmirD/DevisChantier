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
public class ClientDto extends EntityDto<Integer> {
    
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String numeroTelephone;
    private String email;
    private int idChantier;

    public ClientDto(int idClient, String nom, String prenom, Date dateNaissance, String numeroTelephone, String email, int idChantier) {
        this.id = idClient;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
        this.idChantier = idChantier;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public int getIdChantier() {
        return idChantier;
    }
    
    
    
}
