package com.nghia.libraries.mysql.infrustructure.domain;

import com.nghia.libraries.commons.mss.infrustructure.domain.AbstractObject;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AbstractEntity extends AbstractObject {
    @Id
    protected String id;

//    @Field
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected Date createdAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    protected Date modifiedAt;

    protected boolean deleted;

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void initDefaultFieldsCreate() {
        //TODO: create dateTimeUtils to get currentDate
        this.createdAt= new Date();
    }

    public void initDefaultFieldsModify() {
        this.modifiedAt = new Date();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
