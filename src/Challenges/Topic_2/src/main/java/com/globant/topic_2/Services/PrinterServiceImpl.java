package com.globant.topic_2.Services;

import com.globant.topic_2.model.FileToPrint;
import com.globant.topic_2.Repositories.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PrinterServiceImpl implements PrinterService{

    @Qualifier("blackAndWhitePrinter")
    @Autowired
    private Printer blackWhitePrinter;

    @Qualifier("colorPrinter")
    @Autowired
    private Printer colorPrinter;

    @Override
    public void toPrint(FileToPrint fileToPrint, boolean isColorPrint) throws IOException {
        if (isColorPrint)
            colorPrinter.print(fileToPrint);
        else
            blackWhitePrinter.print(fileToPrint);
    }
}
