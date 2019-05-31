package kalah.View;

import com.qualitascorpus.testsupport.IO;
import kalah.Model.Board;

public abstract class AbstractPrint {
    static IO io;

    public void setup(IO io) {
        this.io = io;
    }

    public abstract void game(Board board);

    public static void playerWin(int player) {
        io.println(String.format("Player %d wins!", player));
    }

    public static void playerTie() {
        io.println("A tie!");
    }

    public static String getPlayerMove(int player) {
        String prompt = String.format("Player P%d's turn - Specify house number or 'q' to quit: ", player);
        String input = io.readFromKeyboard(prompt);
        return input;
    }

    public static void houseEmpty() {
        io.println("House is empty. Move again.");
    }

    public static void gameOver() {
        io.println("Game over");
    }

    public static void score(int p1_score, int p2_score) {
        String line_1 = String.format("\tplayer 1:%d", p1_score);
        String line_2 = String.format("\tplayer 2:%d", p2_score);
        io.println(line_1);
        io.println(line_2);
    }
}
