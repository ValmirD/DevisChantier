/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.business;

import db.db.DBManager;
import db.dto.CamionDto;
import db.exception.DevisChantierDbException;
import db.exception.DevisChantierBusinessException;
import db.selDto.CamionSel;
import java.util.Collection;

/**
 *
 * @author Marco
 */
public class FacadeDB {
    
    /*Camion*/
    
    public static Collection<CamionDto> getAllCamions() throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            Collection<CamionDto> col = CamionBL.findAll();
            DBManager.validateTransaction();
            return col;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Liste des Camions inaccessible! \n" + msg);
            }
        }
            
    }

    public static CamionDto findCamionById(int eId) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionSel sel = new CamionSel(eId);
            Collection<CamionDto> col = CamionBL.findBySel(sel);
            CamionDto ldto = null;
            if (col.size() == 1) {
                ldto = col.iterator().next();
            }
            DBManager.validateTransaction();
            return ldto;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Camion par ID introuvable ! \n" + msg);
            }
        }
    }

    public static int addCamion(CamionDto caDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            int indice = CamionBL.add(caDto);
            DBManager.validateTransaction();
            return indice;
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Ajout du camion impossible ! \n" + msg);
            }
        }
    }

    public static void updateCamion(CamionDto caDto) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionBL.update(caDto);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Mise à jour du camion impossible ! \n" + msg);
            }
        }
    }

    public static void deleteCamion(int idCamion) throws DevisChantierBusinessException {
        try {
            DBManager.startTransaction();
            CamionBL.delete(idCamion);
            DBManager.validateTransaction();
        } catch (DevisChantierDbException lDB) {
            String msg = lDB.getMessage();
            try {
                DBManager.cancelTransaction();
            } catch (DevisChantierDbException ex) {
                msg = ex.getMessage() + "\n" + msg;
            } finally {
                throw new DevisChantierBusinessException("Suppression du camion impossible ! \n" + msg);
            }
        }
    }    
   
    /*Voiture*/
}
