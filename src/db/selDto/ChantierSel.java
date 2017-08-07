/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.selDto;

/**
 *
 * @author Vali
 */
public class ChantierSel {
    
    private int idChantier;

    private String localisation;
    private String designationProjet;
    private int idClient;

    public ChantierSel(int idChantier, String localisation, String designationProjet, int idClient) {
        this.idChantier = idChantier;
        this.localisation = localisation;
        this.designationProjet = designationProjet;
        this.idClient = idClient;
    }
    
     public ChantierSel(int idChantier) {
        this.idChantier = idChantier;
    }   
     
    public int getIdChantier() {
        return idChantier;
    }     

    public String getLocalisation() {
        return localisation;
    }

    public String getDesignationProjet() {
        return designationProjet;
    }

    public int getIdClient() {
        return idClient;
    }
    
    
}
