package com.blo.sales.v2.model.entities;

import java.util.List;

public class WrapperNotesEntity {
    
    private List<NoteEntity> notes;

    public List<NoteEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteEntity> notes) {
        this.notes = notes;
    }
    
}
