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
public class CamionSel {
    
    private int idCamion;
    private String marque;
    private String modele;

    public CamionSel(int idCamion, String marque, String modele) {
        this.idCamion = idCamion;
        this.marque = marque;
        this.modele = modele;
    }
    
    public CamionSel(int idCamion) {
        this.idCamion = idCamion;
    }    

    public int getIdCamion() {
        return idCamion;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }
    
    
}
