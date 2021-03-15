package tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    enum State {
        PLAY,
        X_WINS,
        O_WINS,
        DRAW;
    }

    static int move = 1;
    static State state = State.PLAY;
    static char[][] array = new char[3][3];

    public static void printGrid() {
        System.out.println("---------");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (j == 0 || j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(array[i][j] + " ");
                if ((j + 1) % 3 == 0) {
                    System.out.println("|");
                }
            }
        }
        System.out.println("---------");
    }

    public static void enterMove() {
        boolean coord = false;
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!coord) {
            System.out.println("Enter the coordinates: ");
            input = scanner.nextLine();
            if (input.matches("\\d+\\s\\d+")) {
                String[] string = input.split(" ");
                int x = Integer.parseInt(string[0]);
                int y = Integer.parseInt(string[1]);
                if (x < 1 || x > 3 || y < 1 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
                if (array[x - 1][y - 1] != '_') {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue;
                }

                if (move % 2 == 0) {
                    array[x - 1][y - 1] = 'O';
                } else {
                    array[x - 1][y - 1] = 'X';
                }
                move++;
                coord = true;
            } else {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static void analyzeGame() {
        int countX = 0;
        int countO = 0;
        //rows
        for (int i = 0; i < array.length; i++) {
            countX = 0;
            countO = 0;
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 'X') {
                    countX++;
                } else if (array[i][j] == 'O') {
                    countO++;
                }
            }
            if (countX == 3) {
                state = State.X_WINS;
                return;
            } else if (countO == 3) {
                state = State.O_WINS;
                return;
            }
        }

        //diagonal
        countX = 0;
        countO = 0;
        for (int j = 0; j < array[0].length; j++) {
            if (array[j][j] == 'X') {
                countX++;
            } else if (array[j][j] == 'O') {
                countO++;
            }
        }
        if (countX == 3) {
            state = State.X_WINS;
            return;
        } else if (countO == 3) {
            state = State.O_WINS;
            return;
        }
        countX = 0;
        countO = 0;
        int k = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            for (; k >= 0;) {
                if (array[i][k] == 'X') {
                    countX++;
                } else if (array[i][k] == 'O') {
                    countO++;
                }
                k--;
                break;
            }
        }
        if (countX == 3) {
            state = State.X_WINS;
            return;
        } else if (countO == 3) {
            state = State.O_WINS;
            return;
        }

        //columns
        for (int i = 0; i < array.length; i++) {
            countX = 0;
            countO = 0;
            for (int j = 0; j < array[i].length; j++) {
                if (array[j][i] == 'X') {
                    countX++;
                } else if (array[j][i] == 'O') {
                    countO++;
                }
            }
            if (countX == 3) {
                state = State.X_WINS;
                return;
            } else if (countO == 3) {
                state = State.O_WINS;
                return;
            }
        }

        //draw
        int countSpace = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '_') {
                    countSpace++;
                }
            }
        }
        if (countSpace == 0) {
            state = State.DRAW;
        }
    }

    public static void main(String[] args) {
        for (char[] chars : array) {
            Arrays.fill(chars, '_');
        }

        printGrid();
        while (state != State.X_WINS && state != State.O_WINS && state != State.DRAW) {
            enterMove();
            printGrid();
            analyzeGame();
        }

        if (state == State.X_WINS) {
            System.out.println("X wins");
        } else if (state == State.O_WINS) {
            System.out.println("O wins");
        } else if (state == State.DRAW) {
            System.out.println("Draw");
        }
    }
}
