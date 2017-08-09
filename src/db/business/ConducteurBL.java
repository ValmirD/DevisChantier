/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.ConducteurDB;
import db.dto.ConducteurDto;
import db.exception.DevisChantierDbException;
import db.selDto.ConducteurSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class ConducteurBL {

    static int add(ConducteurDto el) throws DevisChantierDbException {
        return ConducteurDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        ConducteurDB.deleteDb(id);
    }

    static void update(ConducteurDto el) throws DevisChantierDbException {
        ConducteurDB.updateDb(el);
    }
    
    static Collection<ConducteurDto> findAll() throws DevisChantierDbException {
        ConducteurSel sel = new ConducteurSel(0);
        Collection<ConducteurDto> col = ConducteurDB.getCollection(sel);
        return col;
    }    
    
    static Collection<ConducteurDto> findBySel(ConducteurSel sel) throws DevisChantierDbException {
        Collection<ConducteurDto> col = ConducteurDB.getCollection(sel);
        return col;
    }
    
}
