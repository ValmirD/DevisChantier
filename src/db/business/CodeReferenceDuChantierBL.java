/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.CodeReferenceDuChantierDB;
import db.dto.CodeReferenceDuChantierDto;
import db.exception.DevisChantierDbException;
import db.selDto.CodeReferenceDuChantierSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class CodeReferenceDuChantierBL {

    static int add(CodeReferenceDuChantierDto el) throws DevisChantierDbException {
        return CodeReferenceDuChantierDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        CodeReferenceDuChantierDB.deleteDb(id);
    }

    static void update(CodeReferenceDuChantierDto el) throws DevisChantierDbException {
        CodeReferenceDuChantierDB.updateDb(el);
    }
    
    static Collection<CodeReferenceDuChantierDto> findAll() throws DevisChantierDbException {
        CodeReferenceDuChantierSel sel = new CodeReferenceDuChantierSel(0);
        Collection<CodeReferenceDuChantierDto> col = CodeReferenceDuChantierDB.getCollection(sel);
        return col;
    }    
    
    static Collection<CodeReferenceDuChantierDto> findBySel(CodeReferenceDuChantierSel sel) throws DevisChantierDbException {
        Collection<CodeReferenceDuChantierDto> col = CodeReferenceDuChantierDB.getCollection(sel);
        return col;
    }
    
}
