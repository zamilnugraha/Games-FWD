/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.fwd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author user
 */
public class GamesFWD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        boolean b;
        boolean isCurrentX = false;
        do {
            isCurrentX = !isCurrentX;
            drawCanvas();
            System.out.println("Go " + (isCurrentX ? "TIM A" : "TIM B"));
            int n = getNumber();
            canvas[n] = isCurrentX ? 1 : 2;
            b = !isGameOver(n);
            if (isDraw()) {
                System.out.println("Draw");
                return;
            }
        } while (b);
        drawCanvas();
        System.out.println();

        System.out.println("Winner: " + (isCurrentX ? "TIM A" : "TIM B") + "!");
    }

    static int[] canvas
            = {0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0};

    static int getNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                int n = Integer.parseInt(reader.readLine());
                if (n >= 0 && n < canvas.length && canvas[n] == 0) {
                    return n;
                }
                System.out.println("Select a free field and enter its serial number");
            } catch (NumberFormatException e) {
                System.out.println("Please enter the serial number of the cell");
            } catch (IOException e) {
            }
        }
    }

    static boolean isGameOver(int n) {
        int row = n - n % 5;
        if (canvas[row] == canvas[row + 1]
                && canvas[row] == canvas[row + 2]
                && canvas[row] == canvas[row + 3]
                && canvas[row] == canvas[row + 4]) {
            return true;
        }

        int column = n % 5;
        if (canvas[column] == canvas[column + 5]) {
            if (canvas[column] == canvas[column + 10]) {
                if (canvas[column] == canvas[column + 15]) {
                    if (canvas[column] == canvas[column + 20]) {
                        return true;
                    }
                }
            }
        }

        if (n % 2 != 0) {
            return false;
        }

        if (n % 4 == 0) {

            if (canvas[0] == canvas[6]
                    && canvas[0] == canvas[12]
                    && canvas[0] == canvas[18]
                    && canvas[0] == canvas[24]) {
                return true;
            }
            if (n != 4) {
                return false;
            }
        }
        return canvas[2] == canvas[4]
                && canvas[2] == canvas[8]
                && canvas[2] == canvas[12]
                && canvas[2] == canvas[16]
                && canvas[2] == canvas[20];
    }

    static void drawCanvas() {
        System.out.println("     |     |     |     |     |");
        for (int i = 0; i < canvas.length; i++) {
            if (i != 0) {
                System.out.print("|");
                if (i % 5 == 0) {
                    System.out.println();
                    System.out.println("_____|_____|_____|_____|_____|");
                    System.out.println("     |     |     |     |     |");
                }
            }

            if (canvas[i] == 0) {
                if (i < 10) {
                    System.out.print("  " + i + "  ");
                } else {
                    System.out.print("  " + i + " ");
                }
            }
            if (canvas[i] == 1) {
                System.out.print("  X  ");
            }
            if (canvas[i] == 2) {
                System.out.print("  O  ");
            }
        }
        System.out.print("|");
        System.out.println();
        System.out.println("     |     |     |     |     |");
    }

    public static boolean isDraw() {
        for (int n : canvas) {
            if (n == 0) {
                return false;
            }
        }
        return true;
    }
}
