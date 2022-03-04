package Challenges.Topic_0;

public class Process {

    public static final char SYSTEM = 's';
    public static final char APP = 'a';
    private static final int ATTRIBUTES = 4;
    private static final int ID = 0;
    private static final int TYPE = 1;
    private static final int SPACE = 2;
    private static final int DETAIL_CODE = 3;

    private String[] details;

    public Process(int id, char processType, int space) {
        this.details = new String[ATTRIBUTES];
        this.details[ID] = String.valueOf(id);           //  id process
        this.details[TYPE] = String.valueOf(processType);  //  type process
        this.details[SPACE] = String.valueOf(space);        //  space of process
        if (id <= 9)
            this.details[DETAIL_CODE] = details[TYPE] + "00" + details[ID];   //  string Detail Code
        else
            this.details[DETAIL_CODE] = details[TYPE] + "0" + details[ID];
    }

    public int getId() {
        return Integer.parseInt(details[ID]);
    }

    public String getType() {
        return details[TYPE];
    }

    public String getDetailCode() {
        return details[DETAIL_CODE];
    }

    public int getSpace(){
        return Integer.parseInt(details[SPACE]);
    }
}
