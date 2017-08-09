/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.DevisDB;
import db.dto.DevisDto;
import db.exception.DevisChantierDbException;
import db.selDto.DevisSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class DevisBL {

    static int add(DevisDto el) throws DevisChantierDbException {
        return DevisDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        DevisDB.deleteDb(id);
    }

    static void update(DevisDto el) throws DevisChantierDbException {
        DevisDB.updateDb(el);
    }
    
    static Collection<DevisDto> findAll() throws DevisChantierDbException {
        DevisSel sel = new DevisSel(0);
        Collection<DevisDto> col = DevisDB.getCollection(sel);
        return col;
    }    
    
    static Collection<DevisDto> findBySel(DevisSel sel) throws DevisChantierDbException {
        Collection<DevisDto> col = DevisDB.getCollection(sel);
        return col;
    }
    
}
