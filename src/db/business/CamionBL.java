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
    
    static int add(CamionDto el) throws DevisChantierDbException {
        return CamionDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        CamionDB.deleteDb(id);
    }

    static void update(CamionDto el) throws DevisChantierDbException {
        CamionDB.updateDb(el);
    }
    
    static Collection<CamionDto> findAll() throws DevisChantierDbException {
        CamionSel sel = new CamionSel(0);
        Collection<CamionDto> col = CamionDB.getCollection(sel);
        return col;
    }    
    
    static Collection<CamionDto> findBySel(CamionSel sel) throws DevisChantierDbException {
        Collection<CamionDto> col = CamionDB.getCollection(sel);
        return col;
    }
    
}
