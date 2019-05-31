package kalah.Model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<GameNode> p1_state = new ArrayList<>();
    private List<GameNode> p2_state = new ArrayList<>();
    private int houses;

    public void setup(int seeds, int houses) {
        this.houses = houses;
        for (int i=0; i<houses; i++) {
            House p1_house = new House();
            p1_house.setup(seeds);
            House p2_house = new House();
            p2_house.setup(seeds);

            p1_state.add(p1_house);
            p2_state.add(p2_house);
        }

        Store p1_store = new Store();
        p1_store.setup(0);
        Store p2_store = new Store();
        p2_store.setup(0);

        p1_state.add(p1_store);
        p2_state.add(p2_store);

    }

    public int getSeedValue(int player, int house) {
        if (player == 1) {
            return p1_state.get(house).getSeeds();
        } else if (player == 2) {
            return p2_state.get(house).getSeeds();
        }
        return -1;
    }

    public int getStoreValue(int player) {
        if (player == 1) {
            return p1_state.get(houses).getSeeds();
        } else if (player == 2) {
            return p2_state.get(houses).getSeeds();
        }
        return -1;
    }

    public int getHouses() {
        return houses;
    }

    public boolean checkHouseIsEmpty(int player, int house) {
        if (player == 1) {
            return (p1_state.get(house).getSeeds() == 0);
        } else if (player == 2) {
            return (p2_state.get(house).getSeeds() == 0);
        }
        return false;
    }

    public boolean checkPlayerHousesAreEmpty(int player) {
        int seeds = 0;
        List<GameNode> p_state = null;
        if (player == 1) {
            p_state = p1_state;
        } else if (player == 2) {
            p_state = p2_state;
        }

        for (int i=0; i<houses; i++) {
            seeds = seeds + p_state.get(i).getSeeds();
        }

        return (seeds == 0);
    }

    public boolean playerMove(int player, int house) {
        int seeds = 0;
        List<GameNode> p_state = null;
        if (player == 1) {
            seeds = p1_state.get(house-1).getSeeds();
            ((House)p1_state.get(house-1)).removeAllSeeds();
            p_state = p1_state;
        } else if (player == 2) {
            seeds = p2_state.get(house-1).getSeeds();
            ((House)p2_state.get(house-1)).removeAllSeeds();
            p_state = p2_state;
        }

        int counter = house;

        for (int i=0; i<seeds; i++,counter++) {
            if (counter == houses+1) {
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


        if (p_state.get(counter-1) instanceof Store) {
            return true;
        } else if (p_state.get(counter-1).getSeeds() == 1 &&
                ((p_state.equals(p1_state) && player == 1) || (p_state.equals(p2_state) && player == 2))) {
            playerCapture(player,counter-1);
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

        if (other_p_state.get(house_to_cap).getSeeds() == 0){
            return;
        }
        seeds = other_p_state.get(house_to_cap).getSeeds() + p_state.get(house).getSeeds();
        ((House)other_p_state.get(house_to_cap)).removeAllSeeds();
        ((House)p_state.get(house)).removeAllSeeds();
        ((Store)p_state.get(houses)).addSeeds(seeds);
    }

    public int[] score() {
        int p1_score = 0, p2_score = 0;

        for (int i=0; i<houses+1; i++) {
            p1_score += p1_state.get(i).getSeeds();
            p2_score += p2_state.get(i).getSeeds();
        }

        int[] scores = {p1_score, p2_score};
        return scores;
    }

}
