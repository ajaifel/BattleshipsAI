package com.company;

import java.util.Scanner;

public class Main {

    private static int[][] grid = new int[10][10]; // Initialising the shell grid

    public static void main(String[] args) {
        selectConfig(); // selecting test case

        AI ai = new AI(10, 17); // creating AI with shell parameters
        int x;
        int y;
        int turns = 0;
        while (!ai.taskComplete) { // AI continues until it hits all targets
            ai.nextTurn(); // rolls the turn forwards, changing the hitLocation selected and updating the Point lists
            turns++;
            x = ai.getHitLocationX(); // grabs selected hit location from AI
            y = ai.getHitLocationY();
            ai.hitResult = testHit(x,y);
        }
        System.out.println("Task Complete");
        System.out.printf("%nAll Battleships Sunk in:%n%d turns", turns);

    }

    private static boolean testHit (int x, int y){
        if (x < 0) {
            System.out.println("why");
        }
        return grid[x][y] == 1;
    }



    private static void selectConfig(){ // turns 0 to 1 for ship location
        System.out.println("1. Simple Central Configuration");
        System.out.println("2. Spread Edge Configuration");
        System.out.println("3. Complex Random Configuration");
        System.out.println();
        System.out.println("Please select one of the above options(1,2,3):");
        Scanner scanner = new Scanner(System.in);
        String userIn;
        label:
        while (true){
            userIn = scanner.next();
            switch (userIn) {
                case "1":
                    grid[2][2] = 1;
                    grid[3][2] = 1;
                    grid[4][2] = 1;
                    grid[5][2] = 1;
                    grid[3][4] = 1;
                    grid[3][5] = 1;
                    grid[3][6] = 1;
                    grid[3][7] = 1;
                    grid[3][8] = 1;
                    grid[5][4] = 1;
                    grid[5][5] = 1;
                    grid[5][7] = 1;
                    grid[6][7] = 1;
                    grid[7][7] = 1;
                    grid[7][3] = 1;
                    grid[7][4] = 1;
                    grid[7][5] = 1;
                    break label;
                case "2":
                    grid[0][1] = 1;
                    grid[1][1] = 1;
                    grid[2][1] = 1;
                    grid[3][1] = 1;
                    grid[4][1] = 1;
                    grid[0][4] = 1;
                    grid[0][5] = 1;
                    grid[0][6] = 1;
                    grid[0][8] = 1;
                    grid[0][9] = 1;
                    grid[6][4] = 1;
                    grid[6][5] = 1;
                    grid[6][6] = 1;
                    grid[6][7] = 1;
                    grid[6][0] = 1;
                    grid[7][0] = 1;
                    grid[8][0] = 1;
                    break label;
                case "3":
                    grid[0][7] = 1;
                    grid[0][8] = 1;
                    grid[3][2] = 1;
                    grid[4][2] = 1;
                    grid[5][2] = 1;
                    grid[6][2] = 1;
                    grid[8][3] = 1;
                    grid[8][4] = 1;
                    grid[8][5] = 1;
                    grid[7][9] = 1;
                    grid[8][9] = 1;
                    grid[9][9] = 1;
                    grid[3][4] = 1;
                    grid[3][5] = 1;
                    grid[3][6] = 1;
                    grid[3][7] = 1;
                    grid[3][8] = 1;
                    break label;
                default:
                    System.out.println("That's not a valid input, please input 1, 2 or 3...");
                    break;
            }
        }
    }
}
