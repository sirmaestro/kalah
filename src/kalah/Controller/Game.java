package kalah.Controller;

import com.qualitascorpus.testsupport.IO;
import kalah.Model.Board;
import kalah.View.KalahPrint;

public class Game {
    private Board board = new Board();
    private KalahPrint print = new KalahPrint();
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
            String input = print.getPlayerMove(player_turn);

            if (input.equals("q")) {
                gameOver();
                break;
            }

            int move = Integer.parseInt(input);

            if (!checkValidMove(player_turn, move)) {
                continue;
            }

            if (board.playerMove(player_turn, move)){
                continue;
            } else {
                switchPlayers();
            }

        }
    }

    public void switchPlayers() {
        if (player_turn == 1) {
            player_turn = 2;
        } else if (player_turn == 2) {
            player_turn = 1;
        }
    }

    public void gameOver() {
        game_over = true;
        print.gameOver();
        this.printGame();
    }

    public void gameOverWithScore() {
        game_over = true;
        this.printGame();
        print.gameOver();
        this.printGame();
        int[] scores = board.score();
        print.score(scores[0], scores[1]);
        if (scores[0] > scores[1]) {
            print.playerWin(1);
        } else if (scores[0] < scores[1]) {
            print.playerWin(2);
        } else if (scores[0] == scores[1]) {
            print.playerTie();
        }
    }

    public boolean checkValidMove(int player, int move) {
        int house = move - 1;
        if (board.checkHouseIsEmpty(player, house)) {
            print.houseEmpty();
            return false;
        }
        return true;
    }

    public void printGame() {
        print.game(board);
    }
}
