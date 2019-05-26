package kalah;

import kalah.Controller.Game;
import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.View.KalahPrint;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 * 		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
 * 		io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
 * 		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
 * 		io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
 * 		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
 */
public class Kalah {
	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}
	public void play(IO io) {
		Game game = new Game();

		game.setup(4,6, io);

		game.start();

	}
}
