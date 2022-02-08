package com.globant.topic_2.model;

public class FileToPrint {
    /*  object that represent a printable doc/file */
    public static final String PDF = "pdf";
    public static final String IMAGE = "IMAGE";
    public static final String WORD = "docx";
    public static final String TXT = "txt";

    private String typeDoc;
    private String text;

    public FileToPrint(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public FileToPrint(String typeDoc, String text) {
        this.typeDoc = typeDoc;
        this.text = text;
    }

    public String getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(String typeDoc) {
        this.typeDoc = typeDoc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
