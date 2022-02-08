package com.globant.topic_2.Repositories;

import com.globant.topic_2.model.FileToPrint;

import java.io.IOException;

public interface Printer {
    String filename = "printed";
    void print(FileToPrint fileToPrint) throws IOException;
}
