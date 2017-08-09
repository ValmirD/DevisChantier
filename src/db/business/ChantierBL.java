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
    
    static int add(ChantierDto el) throws DevisChantierDbException {
        return ChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        ChantierDB.deleteDb(id);
    }

    static void update(ChantierDto el) throws DevisChantierDbException {
        ChantierDB.updateDb(el);
    }
    
    static Collection<ChantierDto> findAll() throws DevisChantierDbException {
        ChantierSel sel = new ChantierSel(0);
        Collection<ChantierDto> col = ChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<ChantierDto> findBySel(ChantierSel sel) throws DevisChantierDbException {
        Collection<ChantierDto> col = ChantierDB.getCollection(sel);
        return col;
    }  
    
}
