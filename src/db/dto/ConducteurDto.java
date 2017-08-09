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
public class ConducteurDto extends EntityDto<Integer> {

    private String password;
    private String numeroTelephonePro;
    private String numeroTelephone;
    private double remuneration;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private Date entreeFonction;
    private double cout;
    private boolean permis;
    private int idChantier;

    public ConducteurDto(int idConducteur, String password, String numeroTelephonePro, String numeroTelephone, double remuneration, String nom, String prenom, Date dateNaissance, String email, Date entreeFonction, double cout, boolean permis, int idChantier) {
        this.id = idConducteur;
        this.password = password;
        this.numeroTelephonePro = numeroTelephonePro;
        this.numeroTelephone = numeroTelephone;
        this.remuneration = remuneration;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.entreeFonction = entreeFonction;
        this.cout = cout;
        this.idChantier = idChantier;
    }

    public String getPassword() {
        return password;
    }

    public String getNumeroTelephonePro() {
        return numeroTelephonePro;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public double getRemuneration() {
        return remuneration;
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

    public String getEmail() {
        return email;
    }

    public Date getEntreeFonction() {
        return entreeFonction;
    }

    public double getCout() {
        return cout;
    }

    public boolean isPermis() {
        return permis;
    }

    public int getIdChantier() {
        return idChantier;
    }

}
