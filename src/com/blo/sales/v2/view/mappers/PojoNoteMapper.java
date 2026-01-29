package com.blo.sales.v2.view.mappers;

import com.blo.sales.v2.controller.pojos.PojoIntNote;
import com.blo.sales.v2.controller.pojos.enums.TypeNoteIntEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;
import com.blo.sales.v2.view.pojos.PojoNote;
import com.blo.sales.v2.view.pojos.enums.TypeNoteEnum;

public class PojoNoteMapper implements IToInner<PojoIntNote, PojoNote>, IToOuter<PojoIntNote, PojoNote> {
    
    private static PojoNoteMapper instance;
    
    private PojoNoteMapper() { }
    
    public static PojoNoteMapper getInstance() {
        if (instance == null) {
            instance = new PojoNoteMapper();
        }
        return instance;
    }
    
    @Override
    public PojoIntNote toInner(PojoNote outer) {
        if (outer == null) {
            return null;
        }
        final var inner = new PojoIntNote();
        inner.setFkUser(outer.getFkUser());
        inner.setIdNote(outer.getIdNote());
        inner.setNote(outer.getNote());
        inner.setTimesamp(outer.getTimesamp());
        inner.setTypeNote(TypeNoteIntEnum.valueOf(outer.getTypeNote().name()));
        return inner;
    }

    @Override
    public PojoNote toOuter(PojoIntNote inner) {
        if (inner == null) {
            return null;
        }
        final var outer = new PojoNote();
        outer.setFkUser(inner.getFkUser());
        outer.setIdNote(inner.getIdNote());
        outer.setNote(inner.getNote());
        outer.setTimesamp(inner.getTimesamp());
        outer.setTypeNote(TypeNoteEnum.valueOf(inner.getTypeNote().name()));
        return outer;
    }
    
}
