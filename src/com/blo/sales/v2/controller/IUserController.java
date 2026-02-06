package com.blo.sales.v2.controller;

import com.blo.sales.v2.controller.pojos.PojoIntLoggedInUser;
import com.blo.sales.v2.controller.pojos.PojoIntNote;
import com.blo.sales.v2.controller.pojos.PojoIntUser;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntNotes;
import com.blo.sales.v2.utils.BloSalesV2Exception;

public interface IUserController {
    
    PojoIntNote addNote(PojoIntNote note) throws BloSalesV2Exception;
    
    WrapperPojoIntNotes getNotesByUserId(long idUser) throws BloSalesV2Exception;
    
    PojoIntNote updateNote(PojoIntNote note) throws BloSalesV2Exception;
    
    void deleteNote(long idNote) throws BloSalesV2Exception;
    
    PojoIntLoggedInUser doLogin(PojoIntUser userData) throws BloSalesV2Exception;
    
    PojoIntUser getUserById(long id) throws BloSalesV2Exception;
}
