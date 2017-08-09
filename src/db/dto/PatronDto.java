/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.dto;

/**
 *
 * @author Vali
 */
public class PatronDto extends EntityDto<Integer> {

    private boolean validationProjet;
    private String password;

    public PatronDto(int idPatron, String password, boolean validationProjet) {
        this.id = idPatron;
        this.password = password;
        this.validationProjet = validationProjet;
    }

    public boolean isValidationProjet() {
        return validationProjet;
    }

    public String getPassword() {
        return password;
    }

}
