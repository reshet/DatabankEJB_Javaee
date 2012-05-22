/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mplatforma.db.reset;

import com.mplatforma.amr.service.remote.UserAccountBeanRemote;
import com.mresearch.databank.shared.UserAccountDTO;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author reshet
 */
@WebService(serviceName = "HardResetWebService")
@Stateless()
public class HardResetWebService {
    @EJB
    private UserAccountBeanRemote ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

//    @WebMethod(operationName = "getUserAccount")
//    public UserAccountDTO getUserAccount(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
//        return ejbRef.getUserAccount(email, password);
//    }
//
//    @WebMethod(operationName = "getDefaultUser")
//    public UserAccountDTO getDefaultUser() {
//        return ejbRef.getDefaultUser();
//    }
//
//    @WebMethod(operationName = "updateAccountResearchState")
//    public UserAccountDTO updateAccountResearchState(@WebParam(name = "dto") UserAccountDTO dto) {
//        return ejbRef.updateAccountResearchState(dto);
//    }

    @WebMethod(operationName = "initDefaults")
    @Oneway
    public void initDefaults() {
        ejbRef.initDefaults();
    }
    
}
