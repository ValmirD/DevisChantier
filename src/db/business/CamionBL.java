/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.CamionDB;
import db.dto.CamionDto;
import db.exception.DevisChantierDbException;
import db.selDto.CamionSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class CamionBL {
    
        static CamionDto findById(int id) throws DevisChantierDbException {
        CamionSel sel = new CamionSel(id);
        Collection<CamionDto> col = CamionDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }
    
    static Collection<CamionDto> findBySel(CamionSel sel) throws DevisChantierDbException {
        Collection<CamionDto> col = CamionDB.getCollection(sel);
        return col;
    }
    
}
