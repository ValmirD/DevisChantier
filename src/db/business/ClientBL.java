/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.ClientDB;
import db.dto.ClientDto;
import db.exception.DevisChantierDbException;
import db.selDto.ClientSel;
import java.util.Collection;

/**
 *
 * @author Vali
 */
public class ClientBL {
    
    static int add(ClientDto el) throws DevisChantierDbException {
        return ClientDB.insertDb(el);
    }

    static void delete(int id) throws DevisChantierDbException {
        ClientDB.deleteDb(id);
    }

    static void update(ClientDto el) throws DevisChantierDbException {
        ClientDB.updateDb(el);
    }
    
    static Collection<ClientDto> findAll() throws DevisChantierDbException {
        ClientSel sel = new ClientSel(0);
        Collection<ClientDto> col = ClientDB.getCollection(sel);
        return col;
    }    
    
    static Collection<ClientDto> findBySel(ClientSel sel) throws DevisChantierDbException {
        Collection<ClientDto> col = ClientDB.getCollection(sel);
        return col;
    }    
    
}
