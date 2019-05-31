package kalah.View;

import kalah.Model.AbstractBoard;

public class KalahPrint extends AbstractPrint {

    public void game(AbstractBoard board) {
        String braces = "+----+";
        String mid_brace = "|    |";
        String p2_print = "| P2 |";
        String p1_print;
        if (board.getStoreValue(2) >= 10) {
            p1_print = String.format("| %d |", board.getStoreValue(2));
        } else {
            p1_print = String.format("|  %d |", board.getStoreValue(2));
        }


        for (int i = 0, j = board.getHouses() - 1; i < board.getHouses(); i++, j--) {
            braces = braces.concat("-------+");
            mid_brace = mid_brace.concat("-------+");
            if (board.getSeedValue(2, j) >= 10) {
                p2_print = p2_print.concat(String.format(" %d[%d] |", j + 1, board.getSeedValue(2, j)));
            } else {
                p2_print = p2_print.concat(String.format(" %d[ %d] |", j + 1, board.getSeedValue(2, j)));
            }
            if (board.getSeedValue(1, i) >= 10) {
                p1_print = p1_print.concat(String.format(" %d[%d] |", i + 1, board.getSeedValue(1, i)));
            } else {
                p1_print = p1_print.concat(String.format(" %d[ %d] |", i + 1, board.getSeedValue(1, i)));
            }
        }

        braces = braces.concat("----+");
        mid_brace = mid_brace.substring(0, mid_brace.length() - 1);
        mid_brace = mid_brace.concat("|    |");
        if (board.getStoreValue(1) >= 10) {
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
}
