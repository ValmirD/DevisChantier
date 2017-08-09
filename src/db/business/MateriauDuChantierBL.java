/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.MateriauDuChantierDB;
import db.dto.MateriauDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.MateriauDuChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class MateriauDuChantierBL {

    static int add(MateriauDuChantierDto el) throws DevisChantierDbException {
        return MateriauDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        MateriauDuChantierDB.deleteDb(id);
    }

    static void update(MateriauDuChantierDto el) throws DevisChantierDbException {
        MateriauDuChantierDB.updateDb(el);
    }
    
    static Collection<MateriauDuChantierDto> findAll() throws DevisChantierDbException {
        MateriauDuChantierSel sel = new MateriauDuChantierSel(0);
        Collection<MateriauDuChantierDto> col = MateriauDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<MateriauDuChantierDto> findBySel(MateriauDuChantierSel sel) throws DevisChantierDbException {
        Collection<MateriauDuChantierDto> col = MateriauDuChantierDB.getCollection(sel);
        return col;
    }
    
}
