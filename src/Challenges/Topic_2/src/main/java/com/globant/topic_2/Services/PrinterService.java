package com.globant.topic_2.Services;

import com.globant.topic_2.model.FileToPrint;

import java.io.IOException;

/**
 * this defines the printer service to make implementation separated from the controller
 * and allow change the repository implementation without the need change controller code
 */
public interface PrinterService {
    public void toPrint(FileToPrint fileToPrint, boolean isColorPrint) throws IOException;
}
