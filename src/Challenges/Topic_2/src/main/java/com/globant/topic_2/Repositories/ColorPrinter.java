package com.globant.topic_2.Repositories;

import com.globant.topic_2.model.FileToPrint;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Repository
public class ColorPrinter implements Printer {

    private String detail = "----\tColor print\t----\n";

    @Override
    public void print(FileToPrint fileToPrint) throws IOException {
        if (!fileToPrint.getTypeDoc().equals(FileToPrint.IMAGE)) {
            try {
                Files.write(Paths.get(filename + "." + fileToPrint.getTypeDoc() + ".txt")
                        , (detail + fileToPrint.getText()).getBytes()
                        , StandardOpenOption.CREATE);
            } catch (Exception e) {
                System.err.println("-- file not printed, error ocurred:\t" + e.getMessage());
                throw new IOException("file not printed, error ocurred");
            } finally {
                return;
            }
        }
        try {
            Files.write(Paths.get(filename + "Image.txt")
                    , (detail + "this file represents a image printed").getBytes()
                    , StandardOpenOption.CREATE);
        } catch (Exception e) {
            System.err.println("-- file not printed, error ocurred:\t" + e.getMessage());
            throw new IOException("file not printed, error ocurred");
        }
    }
}
