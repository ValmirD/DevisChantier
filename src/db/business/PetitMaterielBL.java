/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.PetitMaterielDB;
import db.dto.PetitMaterielDto;
import db.exception.DevisChantierDbException;
import db.selDto.PetitMaterielSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class PetitMaterielBL {

    static int add(PetitMaterielDto el) throws DevisChantierDbException {
        return PetitMaterielDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        PetitMaterielDB.deleteDb(id);
    }

    static void update(PetitMaterielDto el) throws DevisChantierDbException {
        PetitMaterielDB.updateDb(el);
    }
    
    static Collection<PetitMaterielDto> findAll() throws DevisChantierDbException {
        PetitMaterielSel sel = new PetitMaterielSel(0);
        Collection<PetitMaterielDto> col = PetitMaterielDB.getCollection(sel);
        return col;
    }    
    
    static Collection<PetitMaterielDto> findBySel(PetitMaterielSel sel) throws DevisChantierDbException {
        Collection<PetitMaterielDto> col = PetitMaterielDB.getCollection(sel);
        return col;
    }
    
}
