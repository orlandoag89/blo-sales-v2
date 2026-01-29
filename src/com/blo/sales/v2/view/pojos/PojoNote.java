package com.blo.sales.v2.view.pojos;

import com.blo.sales.v2.view.pojos.enums.TypeNoteEnum;

public class PojoNote {
    
    private long idNote;
    
    private long fkUser;
    
    private String note;
    
    private String timesamp;
    
    private TypeNoteEnum typeNote;

    public long getIdNote() {
        return idNote;
    }

    public void setIdNote(long idNote) {
        this.idNote = idNote;
    }

    public long getFkUser() {
        return fkUser;
    }

    public void setFkUser(long fkUser) {
        this.fkUser = fkUser;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimesamp() {
        return timesamp;
    }

    public void setTimesamp(String timesamp) {
        this.timesamp = timesamp;
    }

    public TypeNoteEnum getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(TypeNoteEnum typeNote) {
        this.typeNote = typeNote;
    }
    
}
