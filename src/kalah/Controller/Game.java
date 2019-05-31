package kalah.Controller;

import com.qualitascorpus.testsupport.IO;
import kalah.Model.AbstractBoard;
import kalah.Model.Board;
import kalah.View.AbstractPrint;
import kalah.View.KalahPrint;

public class Game {
    private AbstractBoard board = new Board();
    private AbstractPrint print = new KalahPrint();
    private boolean game_over = false;
    private int player_turn = 1;

    public void setup(int seeds, int houses, IO io) {
        board.setup(seeds, houses);
        print.setup(io);
    }

    public void start() {
        while (!game_over) {
            if (board.checkPlayerHousesAreEmpty(player_turn)) {
                gameOverWithScore();
                break;
            }

            this.printGame();
            String input = AbstractPrint.getPlayerMove(player_turn);

            if (input.equals("q")) {
                gameOver();
                break;
            }

            int move = Integer.parseInt(input);

            if (!checkValidMove(player_turn, move)) {
                continue;
            }

            if (!board.playerMove(player_turn, move)){
                switchPlayers();
            }
        }
    }

    private void switchPlayers() {
        if (player_turn == 1) {
            player_turn = 2;
        } else if (player_turn == 2) {
            player_turn = 1;
        }
    }

    private void gameOver() {
        game_over = true;
        AbstractPrint.gameOver();
        this.printGame();
    }

    private void gameOverWithScore() {
        game_over = true;
        this.printGame();
        AbstractPrint.gameOver();
        this.printGame();
        int[] scores = board.score();
        AbstractPrint.score(scores[0], scores[1]);
        if (scores[0] > scores[1]) {
            AbstractPrint.playerWin(1);
        } else if (scores[0] < scores[1]) {
            AbstractPrint.playerWin(2);
        } else {
            AbstractPrint.playerTie();
        }
    }

    private boolean checkValidMove(int player, int move) {
        int house = move - 1;
        if (board.checkHouseIsEmpty(player, house)) {
            AbstractPrint.houseEmpty();
            return false;
        }
        return true;
    }

    private void printGame() {
        print.game(board);
    }
}
