/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.EnginDB;
import db.dto.EnginDto;
import db.exception.DevisChantierDbException;
import db.selDto.EnginSel;
import java.util.Collection;


/**
 *
 * @author Vali
 */
public class EnginBL {

    static int add(EnginDto el) throws DevisChantierDbException {
        return EnginDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        EnginDB.deleteDb(id);
    }

    static void update(EnginDto el) throws DevisChantierDbException {
        EnginDB.updateDb(el);
    }
    
    static Collection<EnginDto> findAll() throws DevisChantierDbException {
        EnginSel sel = new EnginSel(0);
        Collection<EnginDto> col = EnginDB.getCollection(sel);
        return col;
    }    
    
    static Collection<EnginDto> findBySel(EnginSel sel) throws DevisChantierDbException {
        Collection<EnginDto> col = EnginDB.getCollection(sel);
        return col;
    }
    
}
