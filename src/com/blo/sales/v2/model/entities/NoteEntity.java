package com.blo.sales.v2.model.entities;

import com.blo.sales.v2.model.entities.enums.TypeNoteEntityEnum;

public class NoteEntity {
    
    private long id_note;
    
    private long fk_user;
    
    private String note;
    
    private String timestamp;
    
    private TypeNoteEntityEnum type_note;

    public long getId_note() {
        return id_note;
    }

    public void setId_note(long id_note) {
        this.id_note = id_note;
    }

    public long getFk_user() {
        return fk_user;
    }

    public void setFk_user(long fk_user) {
        this.fk_user = fk_user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public TypeNoteEntityEnum getType_note() {
        return type_note;
    }

    public void setType_note(TypeNoteEntityEnum type_note) {
        this.type_note = type_note;
    }

    @Override
    public String toString() {
        return "NoteEntity{" + "id_note=" + id_note + ", fk_user=" + fk_user + ", note=" + note + ", timestamp=" + timestamp + ", type_note=" + type_note + '}';
    }
}
