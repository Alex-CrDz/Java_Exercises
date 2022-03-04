package com.globant.topic_2.Controllers;

import com.globant.topic_2.Services.PrinterService;
import com.globant.topic_2.dto.PrintRequestDto;
import com.globant.topic_2.model.FileToPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/print")
public class PrinterController {

    private final HashMap<String, Boolean> printersList = new HashMap<String, Boolean>();

    @Autowired
    private PrinterService printerService;

    /**
     * this method is a simple load method for the printers, in this case only manage a list, with the possibility
     * to add more printers in future o change the method implementation to add printers to list.
     */
    public void loadPrinters() {
        printersList.put("B/W_Printer_1", false);
        printersList.put("Color_Printer_1", true);
    }

    @PostMapping
    public ResponseEntity blackWhitePrinter(@RequestBody PrintRequestDto printRequest) {
        FileToPrint fileToPrint;
        boolean isColorPrint = false;
        if (printRequest.getFileType().equals("image"))
            fileToPrint = new FileToPrint(FileToPrint.IMAGE);
        else if (printRequest.getFileType().equals("wordDoc"))
            fileToPrint = new FileToPrint(FileToPrint.WORD, printRequest.getMessageText());
        else if (printRequest.getFileType().equals("pdf"))
            fileToPrint = new FileToPrint(FileToPrint.PDF, printRequest.getMessageText());
        else if (printRequest.getFileType().equals("txt"))
            fileToPrint = new FileToPrint(FileToPrint.TXT, printRequest.getMessageText());
        else
            return new ResponseEntity("wrong printer or wrong file type", HttpStatus.BAD_REQUEST);
        if (printersList.isEmpty())
            loadPrinters();
        if (!printersList.containsKey(printRequest.getPrinter())) {
            return new ResponseEntity("wrong printer name", HttpStatus.BAD_REQUEST);
        }
        isColorPrint = printersList.get(printRequest.getPrinter());
        try {
            printerService.toPrint(fileToPrint, isColorPrint);
            return new ResponseEntity("file printed at root directory", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
