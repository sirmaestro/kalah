package kalah.View;

import com.qualitascorpus.testsupport.IO;
import kalah.Model.Board;

public class KalahPrint {
    IO io;

    public void setup(IO io) {
        this.io = io;
    }

    public void game(Board board) {
        String braces = "+----+";
        String mid_brace = "|    |";
        String p2_print = "| P2 |";
        String p1_print;
        if (board.getStoreValue(2) >= 10){
            p1_print = String.format("| %d |", board.getStoreValue(2));
        } else {
            p1_print = String.format("|  %d |", board.getStoreValue(2));
        }


        for (int i=0, j=board.getHouses()-1; i<board.getHouses(); i++, j--) {
            braces = braces.concat("-------+");
            mid_brace = mid_brace.concat("-------+");
            if (board.getSeedValue(2,j) >= 10) {
                p2_print = p2_print.concat(String.format(" %d[%d] |", j+1, board.getSeedValue(2,j)));
            } else {
                p2_print = p2_print.concat(String.format(" %d[ %d] |", j+1, board.getSeedValue(2,j)));
            }
            if (board.getSeedValue(1,i) >= 10) {
                p1_print = p1_print.concat(String.format(" %d[%d] |", i+1, board.getSeedValue(1,i)));
            } else {
                p1_print = p1_print.concat(String.format(" %d[ %d] |", i+1, board.getSeedValue(1,i)));
            }
        }

        braces = braces.concat("----+");
        mid_brace = mid_brace.substring(0, mid_brace.length() - 1);
        mid_brace = mid_brace.concat("|    |");
        if (board.getStoreValue(1) >= 10){
            p2_print = p2_print.concat(String.format(" %d |", board.getStoreValue(1)));
        } else {
            p2_print = p2_print.concat(String.format("  %d |", board.getStoreValue(1)));
        }
        p1_print = p1_print.concat(" P1 |");

        io.println(braces);
        io.println(p2_print);
        io.println(mid_brace);
        io.println(p1_print);
        io.println(braces);
    }

    public void gameVertical(Board board) {
        String player_braces_top = "+---------------+";
        String player_braces_bottom = "+-------+-------+";

        String p2_print;
        String p1_print;
        String houses_print = "";

        if (board.getStoreValue(2) >= 10){
            p2_print = String.format("|       | P2 %d |", board.getStoreValue(2));
        } else {
            p2_print = String.format("|       | P2  %d |", board.getStoreValue(2));
        }

        io.println(player_braces_top);
        io.println(p2_print);
        io.println(player_braces_bottom);



        for (int i=0, j=board.getHouses()-1; i<board.getHouses(); i++, j--) {

            if (board.getSeedValue(1,i) >= 10) {
                houses_print = houses_print.concat(String.format("| %d[%d] |", i+1, board.getSeedValue(1,i)));
            } else {
                houses_print = houses_print.concat(String.format("| %d[ %d] |", i+1, board.getSeedValue(1,i)));
            }

            if (board.getSeedValue(2,j) >= 10) {
                houses_print = houses_print.concat(String.format(" %d[%d] |", j+1, board.getSeedValue(2,j)));
            } else {
                houses_print = houses_print.concat(String.format(" %d[ %d] |", j+1, board.getSeedValue(2,j)));
            }
            io.println(houses_print);
            houses_print = "";
        }

        if (board.getStoreValue(1) >= 10){
            p1_print = String.format("| P1 %d |       |", board.getStoreValue(1));
        } else {
            p1_print = String.format("| P1  %d |       |", board.getStoreValue(1));
        }

        io.println(player_braces_bottom);
        io.println(p1_print);
        io.println(player_braces_top);
    }

    public void score(int p1_score, int p2_score) {
        String line_1 = String.format("\tplayer 1:%d", p1_score);
        String line_2 = String.format("\tplayer 2:%d", p2_score);
        io.println(line_1);
        io.println(line_2);
    }

    public void playerWin(int player) {
        io.println(String.format("Player %d wins!", player));
    }

    public void playerTie() {
        io.println("A tie!");
    }

    public String getPlayerMove(int player) {
        String prompt = String.format("Player P%d's turn - Specify house number or 'q' to quit: ", player);
        String input = io.readFromKeyboard(prompt);
        return input;
    }

    public void houseEmpty() {
        io.println("House is empty. Move again.");
    }

    public void gameOver() {
        io.println("Game over");
    }
}
