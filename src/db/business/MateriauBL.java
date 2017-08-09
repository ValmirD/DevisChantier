/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.MateriauDB;
import db.dto.MateriauDto;
import db.exception.DevisChantierDbException;
import db.selDto.MateriauSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class MateriauBL {

    static int add(MateriauDto el) throws DevisChantierDbException {
        return MateriauDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        MateriauDB.deleteDb(id);
    }

    static void update(MateriauDto el) throws DevisChantierDbException {
        MateriauDB.updateDb(el);
    }
    
    static Collection<MateriauDto> findAll() throws DevisChantierDbException {
        MateriauSel sel = new MateriauSel(0);
        Collection<MateriauDto> col = MateriauDB.getCollection(sel);
        return col;
    }    
    
    static Collection<MateriauDto> findBySel(MateriauSel sel) throws DevisChantierDbException {
        Collection<MateriauDto> col = MateriauDB.getCollection(sel);
        return col;
    }
    
}
