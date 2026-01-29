package com.blo.sales.v2.controller.pojos;

import com.blo.sales.v2.controller.pojos.enums.TypeNoteIntEnum;

public class PojoIntNote {
    
    private long idNote;
    
    private long fkUser;
    
    private String note;
    
    private String timesamp;
    
    private TypeNoteIntEnum typeNote;

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

    public TypeNoteIntEnum getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(TypeNoteIntEnum typeNote) {
        this.typeNote = typeNote;
    }

    @Override
    public String toString() {
        return "PojoIntNote{" + "idNote=" + idNote + ", fkUser=" + fkUser + ", note=" + note + ", timesamp=" + timesamp + ", typeNote=" + typeNote + '}';
    }
    
}
