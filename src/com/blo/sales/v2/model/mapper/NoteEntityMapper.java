package com.blo.sales.v2.model.mapper;

import com.blo.sales.v2.controller.pojos.PojoIntNote;
import com.blo.sales.v2.controller.pojos.enums.TypeNoteIntEnum;
import com.blo.sales.v2.model.entities.NoteEntity;
import com.blo.sales.v2.model.entities.enums.TypeNoteEntityEnum;
import com.blo.sales.v2.utils.IToInner;
import com.blo.sales.v2.utils.IToOuter;

public class NoteEntityMapper implements IToInner<NoteEntity, PojoIntNote>, IToOuter<NoteEntity, PojoIntNote> {
    
    private static NoteEntityMapper instance;
    
    private NoteEntityMapper() { }
    
    public static NoteEntityMapper getInstance() {
        if (instance == null) {
            instance = new NoteEntityMapper();
        }
        return instance;
    }

    @Override
    public NoteEntity toInner(PojoIntNote outer) {
        if (outer == null) {
            return null;
        }
        final var out = new NoteEntity();
        out.setFk_user(outer.getFkUser());
        out.setId_note(outer.getIdNote());
        out.setNote(outer.getNote());
        out.setTimestamp(outer.getTimesamp());
        out.setType_note(TypeNoteEntityEnum.valueOf(outer.getTypeNote().name()));
        return out;
    }

    @Override
    public PojoIntNote toOuter(NoteEntity inner) {
        if (inner == null) {
            return null;
        }
        final var out = new PojoIntNote();
        out.setFkUser(inner.getFk_user());
        out.setIdNote(inner.getId_note());
        out.setNote(inner.getNote());
        out.setTimesamp(inner.getTimestamp());
        out.setTypeNote(TypeNoteIntEnum.valueOf(inner.getType_note().name()));
        return out;
    }
    
}
