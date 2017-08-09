/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.OuvrierDuChantierDB;
import db.dto.OuvrierDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.OuvrierDuChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class OuvrierDuChantierBL {

    static int add(OuvrierDuChantierDto el) throws DevisChantierDbException {
        return OuvrierDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        OuvrierDuChantierDB.deleteDb(id);
    }

    static void update(OuvrierDuChantierDto el) throws DevisChantierDbException {
        OuvrierDuChantierDB.updateDb(el);
    }
    
    static Collection<OuvrierDuChantierDto> findAll() throws DevisChantierDbException {
        OuvrierDuChantierSel sel = new OuvrierDuChantierSel(0);
        Collection<OuvrierDuChantierDto> col = OuvrierDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<OuvrierDuChantierDto> findBySel(OuvrierDuChantierSel sel) throws DevisChantierDbException {
        Collection<OuvrierDuChantierDto> col = OuvrierDuChantierDB.getCollection(sel);
        return col;
    }
    
}
