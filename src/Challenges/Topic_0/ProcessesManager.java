package Challenges.Topic_0;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessesManager {

    private static Memory memory = new Memory();
    /* the processes list size is max 40, due to the max limit space in memory is 200
     * creating the smallest processes possible (which are system process with space of 5)
     * the memory will overflow over 40 processes
     * */
    private static HashMap<Integer, Process> processesList = new HashMap<Integer, Process>(40);
    private static int idCount = 1;

    /**
     * create the process, add it to processes list and load it into memory
     *
     * @param command is a String taken from input Stream with the type of process to create
     */
    public static void createProcess(String command) {
        if (command.length() > 2 && (command.charAt(1) != 's' || command.charAt(1) != 'a')) {     //  validate input command
            System.err.println("-- Invalid command");
            return;
        }
        try {
            Process newProcess;
            if (command.charAt(1) == 's') {
                newProcess = new Process(idCount++, Process.SYSTEM, ThreadLocalRandom.current().nextInt(5, 16));
            } else {
                newProcess = new Process(idCount++, Process.APP, ThreadLocalRandom.current().nextInt(10, 21));
            }
            processesList.put(newProcess.getId(), newProcess);
            memory.loadProcess(newProcess);     // load the process into memory
        } catch (Exception e) { //  in case the process is not created or not added to hashmap
            System.err.println("-- Something is wrong, process not created: " + e.getMessage());
        }
    }

    /**
     * search for the process and delete it from the memory
     *
     * @param command is a String taken from input Stream with the process id to delete
     */
    public static void deleteProcess(String command) {
        int processId;
        try {
            if (command.length() > 4 || Integer.parseInt(command.substring(1)) <= 0) {      //  validate input command
                System.err.println("-- Invalid command");
                return;
            }
        } catch (Exception e) {
            System.err.println("-- Invalid command");
            return;
        }
        processId = Integer.parseInt(command.substring(1));
        if (processesList.containsKey(processId)) {     //  search for process in the processes list
            // delete process if exist from memory and free space
            try {
                memory.endProcess(processesList.get(processId));
                processesList.remove(processId);
            } catch (Exception e) {
                System.err.println("-- Something is wrong: " + e.getMessage());
            }
            return;
        }
        System.err.println("-- Invalid command");
    }

    public static void run() {  //  controls the execution flow
        Scanner read = new Scanner(System.in);
        String input;

        while (true) {
            input = read.nextLine();
            if (input.toLowerCase().charAt(0) == 'c') {
                createProcess(input.toLowerCase());    //  calls create method
                memory.printMemory();
                continue;
            } else if (input.toLowerCase().charAt(0) == 'd') {
                deleteProcess(input.toLowerCase());     //  calls delete method
                memory.printMemory();
                continue;
            } else if (input.toLowerCase().charAt(0) == 'e' || input.toLowerCase().equals("exit")) {    //  to exit
                break;
            } else {
                continue;
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}
