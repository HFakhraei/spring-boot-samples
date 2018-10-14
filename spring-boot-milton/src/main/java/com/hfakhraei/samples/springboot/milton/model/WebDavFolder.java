package com.hfakhraei.samples.springboot.milton.model;

import io.milton.annotations.*;

import java.util.Date;

/**
 *
 * @author Hossein Fakhraei (HFakhraei@outlook.com)
 * @version 1 14 October 2018
 */
public class WebDavFolder {
    private String name;
    private Date createdDate;

    public WebDavFolder(String name) {
        this.name = name;
    }

    @Name
    public String getName() {
        return name;
    }

    @UniqueId
    public String getUniqueId() {
        return name;
    }

    @CreatedDate
    public Date getCreatedDate() {
        return createdDate;
    }
}
