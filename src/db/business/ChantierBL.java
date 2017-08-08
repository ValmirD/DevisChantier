/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.ChantierDB;
import db.dto.ChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.ChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class ChantierBL {
    
    static ChantierDto findById(int id) throws DevisChantierDbException {
        ChantierSel sel = new ChantierSel(id);
        Collection<ChantierDto> col = ChantierDB.getCollection(sel);
        if (col.size() == 1) {
            return col.iterator().next();
        } else {
            return null;
        }
    }
    
    static Collection<ChantierDto> findBySel(ChantierSel sel) throws DevisChantierDbException {
        Collection<ChantierDto> col = ChantierDB.getCollection(sel);
        return col;
    }    
    
}
