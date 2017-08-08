/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.CamionDuChantierDB;
import db.dto.CamionDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.CamionDuChantierSel;
import java.util.Collection;


/**
 *
 * @author Vali
 */
public class CamionDuChantierBL {
    
    static int add(CamionDuChantierDto el) throws DevisChantierDbException {
        return CamionDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        CamionDuChantierDB.deleteDb(id);
    }

    static void update(CamionDuChantierDto el) throws DevisChantierDbException {
        CamionDuChantierDB.updateDb(el);
    }
    
    static Collection<CamionDuChantierDto> findAll() throws DevisChantierDbException {
        CamionDuChantierSel sel = new CamionDuChantierSel(0);
        Collection<CamionDuChantierDto> col = CamionDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static CamionDuChantierDto findById(int id) throws DevisChantierDbException {
        CamionDuChantierSel sel = new CamionDuChantierSel(id);
        Collection<CamionDuChantierDto> col = CamionDuChantierDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }
    
    static Collection<CamionDuChantierDto> findBySel(CamionDuChantierSel sel) throws DevisChantierDbException {
        Collection<CamionDuChantierDto> col = CamionDuChantierDB.getCollection(sel);
        return col;
    }
    
    
    
}
