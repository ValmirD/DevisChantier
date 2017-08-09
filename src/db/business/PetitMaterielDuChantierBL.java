/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.PetitMaterielDuChantierDB;
import db.dto.PetitMaterielDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.PetitMaterielDuChantierSel;
import java.util.Collection;


/**
 *
 * @author Vali
 */
public class PetitMaterielDuChantierBL {

    static int add(PetitMaterielDuChantierDto el) throws DevisChantierDbException {
        return PetitMaterielDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        PetitMaterielDuChantierDB.deleteDb(id);
    }

    static void update(PetitMaterielDuChantierDto el) throws DevisChantierDbException {
        PetitMaterielDuChantierDB.updateDb(el);
    }
    
    static Collection<PetitMaterielDuChantierDto> findAll() throws DevisChantierDbException {
        PetitMaterielDuChantierSel sel = new PetitMaterielDuChantierSel(0);
        Collection<PetitMaterielDuChantierDto> col = PetitMaterielDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<PetitMaterielDuChantierDto> findBySel(PetitMaterielDuChantierSel sel) throws DevisChantierDbException {
        Collection<PetitMaterielDuChantierDto> col = PetitMaterielDuChantierDB.getCollection(sel);
        return col;
    }
    
}
