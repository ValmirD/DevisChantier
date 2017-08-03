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
    
    private String localisation;
    private String designationProjet;
    private int idClient;

    public ChantierSel(String localisation, String designationProjet, int idClient) {
        this.localisation = localisation;
        this.designationProjet = designationProjet;
        this.idClient = idClient;
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
