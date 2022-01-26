package Challenges.Topic_0;

import java.util.Arrays;
import java.util.Collections;

public class Memory {

    private String[] memoryheap;
    private int freeSpace;

    public Memory() {
        this.memoryheap = new String[200];
        this.freeSpace = 200;
        for (int i = 0; i < memoryheap.length; i++) {
            memoryheap[i] = "****";
        }
    }

    public void printMemory() {
        int index = 199;
        System.out.println("");
        for (int i = 0; i < 20; i++) {
            int row = 9;
            for (int j = 0; j < 10; j++) {
                System.out.print(memoryheap[index - row] + "\t");
                row--;
            }
            index -= 10;
            System.out.println("");
        }
        System.out.println("");
    }

    public void loadProcess(Process process) throws MemoryOverflowException {
        //  verify if is enough free space in memory to load the process
        if (process.getSpace() > freeSpace) {
            throw new MemoryOverflowException("Not enough space on memory, process not loaded");
        }
        int index = 200 - freeSpace;
        for (int i = 0; i < process.getSpace(); i++) {
            memoryheap[index] = process.getDetailCode();
            index++;
            freeSpace--;
        }
    }

    public void endProcess(Process process) throws Exception {
        for (int i = 0; i < 200; i++) {
            if (memoryheap[i].equals(process.getDetailCode())) {
                memoryheap[i] = "****";
            }
        }
        freeSpace += process.getSpace();
        rearrangeMemory();
    }

    private void rearrangeMemory() throws Exception {
        try {
            Arrays.sort(memoryheap, Collections.reverseOrder());
        } catch (Exception e) {
            throw new Exception("problem arranged memory heap: " + e.getMessage());
        }
    }
}
