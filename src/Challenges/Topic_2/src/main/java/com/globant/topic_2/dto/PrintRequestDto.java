package com.globant.topic_2.dto;

public class PrintRequestDto {

    private String fileType;    //  image, wordDoc, pdf or txt
    private String printer;     //  any printer created
    private String messageText; //  text to print (not for image type)

    public PrintRequestDto(String fileType, String printer, String messageText) {
        this.fileType = fileType;
        this.printer = printer;
        this.messageText = messageText;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
