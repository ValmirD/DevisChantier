/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.PatronDB;
import db.dto.PatronDto;
import db.exception.DevisChantierDbException;
import db.selDto.PatronSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class PatronBL {

    static int add(PatronDto el) throws DevisChantierDbException {
        return PatronDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        PatronDB.deleteDb(id);
    }

    static void update(PatronDto el) throws DevisChantierDbException {
        PatronDB.updateDb(el);
    }
    
    static Collection<PatronDto> findAll() throws DevisChantierDbException {
        PatronSel sel = new PatronSel(0);
        Collection<PatronDto> col = PatronDB.getCollection(sel);
        return col;
    }    
    
    static Collection<PatronDto> findBySel(PatronSel sel) throws DevisChantierDbException {
        Collection<PatronDto> col = PatronDB.getCollection(sel);
        return col;
    }
    
}
