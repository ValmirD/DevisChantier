/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.CodeReferenceDB;
import db.dto.CodeReferenceDto;
import db.exception.DevisChantierDbException;
import db.selDto.CodeReferenceSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class CodeReferenceBL {

    static int add(CodeReferenceDto el) throws DevisChantierDbException {
        return CodeReferenceDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        CodeReferenceDB.deleteDb(id);
    }

    static void update(CodeReferenceDto el) throws DevisChantierDbException {
        CodeReferenceDB.updateDb(el);
    }
    
    static Collection<CodeReferenceDto> findAll() throws DevisChantierDbException {
        CodeReferenceSel sel = new CodeReferenceSel(0);
        Collection<CodeReferenceDto> col = CodeReferenceDB.getCollection(sel);
        return col;
    }    
    
    static Collection<CodeReferenceDto> findBySel(CodeReferenceSel sel) throws DevisChantierDbException {
        Collection<CodeReferenceDto> col = CodeReferenceDB.getCollection(sel);
        return col;
    }
    
}
