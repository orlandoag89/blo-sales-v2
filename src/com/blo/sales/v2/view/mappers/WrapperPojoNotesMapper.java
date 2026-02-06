package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.WrapperPojoIntNotes;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoNote;
import com.blo.sales.v2.view.pojos.WrapperPojoNotes;
import java.util.ArrayList;

public class WrapperPojoNotesMapper implements IToOuter<WrapperPojoIntNotes, WrapperPojoNotes> {
    
    private static final PojoNoteMapper mapper = PojoNoteMapper.getInstance();
    
    private static WrapperPojoNotesMapper instance;
    
    private WrapperPojoNotesMapper() { }
    
    public static WrapperPojoNotesMapper getInstance() {
        if (instance == null) {
            instance = new WrapperPojoNotesMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoNotes toOuter(WrapperPojoIntNotes inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new WrapperPojoNotes();
        final var lst = new ArrayList<PojoNote>();
        if (inner.getNotes() != null && !inner.getNotes().isEmpty()) {
            inner.getNotes().forEach(n -> lst.add(mapper.toOuter(n)));
        }
        outer.setNotes(lst);
        return outer;
    }
    
}
