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
public class PetitMaterielDuChantierDto extends EntityDto<Integer> {
    
    private int quantite;
    private Date debutDisponibilite;
    private Date finDisponibilite;
    private int idChantier;
    private int idPetitMateriel;

    public PetitMaterielDuChantierDto(int idPetitMaterielDuChantier, int quantite, Date debutDisponibilite, Date finDisponibilite, int idChantier, int idPetitMateriel) {
        this.id = idPetitMaterielDuChantier;
        this.quantite = quantite;
        this.debutDisponibilite = debutDisponibilite;
        this.finDisponibilite = finDisponibilite;
        this.idChantier = idChantier;
        this.idPetitMateriel = idPetitMateriel;
    }
    
}
