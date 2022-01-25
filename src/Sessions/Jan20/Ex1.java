package Sessions.Jan20;

public class Ex1 {

    private static int[] wall = {3, 4, 2, 4, 2};

    public static int getBlocksAmount() {
        int blocksAmount = 0;
        for (int i = 0; i < wall.length; i++) {
            blocksAmount += wall[i];
        }
        return blocksAmount;
    }

    public static int getAvg(int blocksAmount) {
        return blocksAmount / wall.length;
    }

    public static int getMax() {
        int max = 0;
        for (int i = 0; i < wall.length; i++) {
            if (wall[i] > max)
                max = wall[i];
        }
        return max;
    }

    public static int wallSize(int blockAvg) {
        int blocksToMove = 0;
        for (int i = 0 ; i < wall.length; i++) {
            if (wall[i] > blockAvg) {
                blocksToMove = blocksToMove + (wall[i] - blockAvg);
            }
        }
        return blocksToMove;
    }

    public static int maximizeBlocksCount(int max) {
        int blocks2Increase = 0;
        for (int i = 0; i < wall.length; i++) {
            if (wall[i] < max)
                blocks2Increase += max - wall[i];
        }
        return blocks2Increase;
    }

    public static void main(String[] args) {
        int blocksAmount = getBlocksAmount();
        System.out.println("blocks amount:\t" + blocksAmount);
        System.out.println("avegare:\t" + getAvg(blocksAmount));
        System.out.println("blocks to move:\t" + wallSize(getAvg(blocksAmount)));
        System.out.println("blocks to increase:\t" + maximizeBlocksCount(getMax()));

    }
}