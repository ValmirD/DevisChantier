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
public class CodeReferenceSel {
    
    private int idCodeReference;
    private String reference;

    public CodeReferenceSel(int idCodeReference, String reference) {
        this.idCodeReference = idCodeReference;
        this.reference = reference;
    }
    
        public CodeReferenceSel(int idCodeReference) {
        this.idCodeReference = idCodeReference;
    }

    public int getIdCodeReference() {
        return idCodeReference;
    }

    public String getReference() {
        return reference;
    }
        
    
    
}
