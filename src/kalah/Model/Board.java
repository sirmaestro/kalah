package kalah.Model;

import java.util.List;

public class Board extends AbstractBoard {

    public boolean playerMove(int player, int house) {
        int seeds = 0;
        List<GameNode> p_state = null;
        if (player == 1) {
            seeds = p1_state.get(house - 1).getSeeds();
            ((House) p1_state.get(house - 1)).removeAllSeeds();
            p_state = p1_state;
        } else if (player == 2) {
            seeds = p2_state.get(house - 1).getSeeds();
            ((House) p2_state.get(house - 1)).removeAllSeeds();
            p_state = p2_state;
        }

        int counter = house;

        for (int i = 0; i < seeds; i++, counter++) {
            if (counter == houses + 1) {
                if (p_state.equals(p1_state)) {
                    p_state = p2_state;
                    counter = 0;
                } else if (p_state.equals(p2_state)) {
                    p_state = p1_state;
                    counter = 0;
                }
            }
            if (counter == houses &&
                    ((p_state.equals(p2_state) && player == 1) || (p_state.equals(p1_state) && player == 2))) {
                i--;
                continue;
            }
            p_state.get(counter).incrementSeed();
        }


        if (p_state.get(counter - 1) instanceof Store) {
            return true;
        } else if (p_state.get(counter - 1).getSeeds() == 1 &&
                ((p_state.equals(p1_state) && player == 1) || (p_state.equals(p2_state) && player == 2))) {
            playerCapture(player, counter - 1);
        }
        return false;
    }

    private void playerCapture(int player, int house) {
        int house_to_cap = houses - house - 1;
        int seeds;
        List<GameNode> p_state = null;
        List<GameNode> other_p_state = null;

        if (player == 1) {
            p_state = p1_state;
            other_p_state = p2_state;
        } else if (player == 2) {
            p_state = p2_state;
            other_p_state = p1_state;
        }

        if (other_p_state.get(house_to_cap).getSeeds() == 0) {
            return;
        }
        seeds = other_p_state.get(house_to_cap).getSeeds() + p_state.get(house).getSeeds();
        ((House) other_p_state.get(house_to_cap)).removeAllSeeds();
        ((House) p_state.get(house)).removeAllSeeds();
        ((Store) p_state.get(houses)).addSeeds(seeds);
    }

}
