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
