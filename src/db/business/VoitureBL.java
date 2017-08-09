/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.VoitureDB;
import db.dto.VoitureDto;
import db.exception.DevisChantierDbException;
import db.selDto.VoitureSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class VoitureBL {

    static int add(VoitureDto el) throws DevisChantierDbException {
        return VoitureDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        VoitureDB.deleteDb(id);
    }

    static void update(VoitureDto el) throws DevisChantierDbException {
        VoitureDB.updateDb(el);
    }
    
    static Collection<VoitureDto> findAll() throws DevisChantierDbException {
        VoitureSel sel = new VoitureSel(0);
        Collection<VoitureDto> col = VoitureDB.getCollection(sel);
        return col;
    }    
    
    static Collection<VoitureDto> findBySel(VoitureSel sel) throws DevisChantierDbException {
        Collection<VoitureDto> col = VoitureDB.getCollection(sel);
        return col;
    }
    
}
