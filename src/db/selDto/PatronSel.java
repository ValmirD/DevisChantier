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
public class PatronSel {

    private int idPatron;
    private String password;

    public PatronSel(int idPatron, String password) {
        this.idPatron = idPatron;
    }

    public PatronSel(int idPatron) {
        this.idPatron = idPatron;
    }

    public int getIdPatron() {
        return idPatron;
    }

    public String getPassword() {
        return password;
    }

}
