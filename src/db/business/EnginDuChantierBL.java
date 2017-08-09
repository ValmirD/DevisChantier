/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.EnginDuChantierDB;
import db.dto.EnginDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.EnginDuChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class EnginDuChantierBL {

    static int add(EnginDuChantierDto el) throws DevisChantierDbException {
        return EnginDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        EnginDuChantierDB.deleteDb(id);
    }

    static void update(EnginDuChantierDto el) throws DevisChantierDbException {
        EnginDuChantierDB.updateDb(el);
    }
    
    static Collection<EnginDuChantierDto> findAll() throws DevisChantierDbException {
        EnginDuChantierSel sel = new EnginDuChantierSel(0);
        Collection<EnginDuChantierDto> col = EnginDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<EnginDuChantierDto> findBySel(EnginDuChantierSel sel) throws DevisChantierDbException {
        Collection<EnginDuChantierDto> col = EnginDuChantierDB.getCollection(sel);
        return col;
    }
    
}
