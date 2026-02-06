package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntNote;
import com.blo.sales.v2.controller.pojos.WrapperPojoIntNotes;
import com.blo.sales.v2.model.entities.WrapperNotesEntity;
import com.blo.sales.v2.utils.IToOuter;
import java.util.ArrayList;

public class WrapperNotesEntityMapper implements IToOuter<WrapperNotesEntity, WrapperPojoIntNotes> {
    
    private static final NoteEntityMapper mapper = NoteEntityMapper.getInstance();
    
    private static WrapperNotesEntityMapper instance;
    
    private WrapperNotesEntityMapper(){ }
    
    public static WrapperNotesEntityMapper getInstance() {
        if (instance == null) {
            instance = new WrapperNotesEntityMapper();
        }
        return instance;
    }

    @Override
    public WrapperPojoIntNotes toOuter(WrapperNotesEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new WrapperPojoIntNotes();
        final var lst = new ArrayList<PojoIntNote>();
        if (inner.getNotes() != null && !inner.getNotes().isEmpty()) {
            inner.getNotes().forEach(n -> lst.add(mapper.toOuter(n)));
        }
        out.setNotes(lst);
        return out;
    }
    
}
