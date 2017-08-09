/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.OuvrierDB;
import db.dto.OuvrierDto;
import db.exception.DevisChantierDbException;
import db.selDto.OuvrierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class OuvrierBL {

    static int add(OuvrierDto el) throws DevisChantierDbException {
        return OuvrierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        OuvrierDB.deleteDb(id);
    }

    static void update(OuvrierDto el) throws DevisChantierDbException {
        OuvrierDB.updateDb(el);
    }
    
    static Collection<OuvrierDto> findAll() throws DevisChantierDbException {
        OuvrierSel sel = new OuvrierSel(0);
        Collection<OuvrierDto> col = OuvrierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<OuvrierDto> findBySel(OuvrierSel sel) throws DevisChantierDbException {
        Collection<OuvrierDto> col = OuvrierDB.getCollection(sel);
        return col;
    }
    
}
