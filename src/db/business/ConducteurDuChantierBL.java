/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.ConducteurDuChantierDB;
import db.dto.ConducteurDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.ConducteurDuChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class ConducteurDuChantierBL {

    static int add(ConducteurDuChantierDto el) throws DevisChantierDbException {
        return ConducteurDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        ConducteurDuChantierDB.deleteDb(id);
    }

    static void update(ConducteurDuChantierDto el) throws DevisChantierDbException {
        ConducteurDuChantierDB.updateDb(el);
    }
    
    static Collection<ConducteurDuChantierDto> findAll() throws DevisChantierDbException {
        ConducteurDuChantierSel sel = new ConducteurDuChantierSel(0);
        Collection<ConducteurDuChantierDto> col = ConducteurDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<ConducteurDuChantierDto> findBySel(ConducteurDuChantierSel sel) throws DevisChantierDbException {
        Collection<ConducteurDuChantierDto> col = ConducteurDuChantierDB.getCollection(sel);
        return col;
    }
    
}
