package com.hfakhraei.samples.springboot.milton.controllers;

import com.hfakhraei.samples.springboot.milton.model.WebDavFile;
import com.hfakhraei.samples.springboot.milton.model.WebDavFolder;
import io.milton.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Hello World Controller
 *
 * @author Hossein Fakhraei (HFakhraei@outlook.com)
 * @version 1 14 October 2018
 */

@ResourceController
public class HelloWorldController {
    private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);
    private static HashMap<String, WebDavFile> webDavFiles = new HashMap<>();

    static {
        try {
            byte[] bytes  = "Hello World".getBytes("UTF-8");
            webDavFiles.put("file1.txt", new WebDavFile("file1.txt", bytes));
            webDavFiles.put("file2.txt", new WebDavFile("file2.txt", bytes));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Root
    public HelloWorldController getRoot() {
        return this;
    }

    @ChildrenOf
    public List<WebDavFolder> getWebDavFolders(HelloWorldController root) {
        List<WebDavFolder> webDavFolders = new ArrayList<WebDavFolder>();
        webDavFolders.add(new WebDavFolder("folder1"));
        webDavFolders.add(new WebDavFolder("folder2"));
        return webDavFolders;
    }

    @ChildrenOf
    public Collection<WebDavFile> getWebDavFiles(WebDavFolder webDavFolder) {
        return webDavFiles.values();
    }

    @Get
    public InputStream getChild(WebDavFile webDavFile) {
        return new ByteArrayInputStream(webDavFiles.get(webDavFile.getName()).getBytes());
    }

    @PutChild
    public void putChild(WebDavFile parent, String name, byte[] bytes) {
        if(name!=null) {
            webDavFiles.put(name, new WebDavFile(name, bytes));
        } else {
            parent.setBytes(bytes);
            webDavFiles.put(parent.getName(), parent);
        }
    }

    @Delete
    public void delete(WebDavFile webDavFile) {
        webDavFiles.remove(webDavFile.getName());
    }

    @Name
    public String getWebDavFile(WebDavFile webDavFile) {
        return webDavFile.getName();
    }

    @DisplayName
    public String getDisplayName(WebDavFile webDavFile) {
        return webDavFile.getName();
    }

    @UniqueId
    public String getUniqueId(WebDavFile webDavFile) {
        return webDavFile.getName();
    }

    @ModifiedDate
    public Date getModifiedDate(WebDavFile webDavFile) {
        return webDavFile.getModifiedDate();
    }

    @CreatedDate
    public Date getCreatedDate(WebDavFile webDavFile) {
        return webDavFile.getCreatedDate();
    }

}
