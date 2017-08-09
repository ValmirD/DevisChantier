/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.VoitureDuChantierDB;
import db.dto.VoitureDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.VoitureDuChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class VoitureDuChantierBL {

    static int add(VoitureDuChantierDto el) throws DevisChantierDbException {
        return VoitureDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        VoitureDuChantierDB.deleteDb(id);
    }

    static void update(VoitureDuChantierDto el) throws DevisChantierDbException {
        VoitureDuChantierDB.updateDb(el);
    }
    
    static Collection<VoitureDuChantierDto> findAll() throws DevisChantierDbException {
        VoitureDuChantierSel sel = new VoitureDuChantierSel(0);
        Collection<VoitureDuChantierDto> col = VoitureDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<VoitureDuChantierDto> findBySel(VoitureDuChantierSel sel) throws DevisChantierDbException {
        Collection<VoitureDuChantierDto> col = VoitureDuChantierDB.getCollection(sel);
        return col;
    }
    
}
