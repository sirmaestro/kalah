package kalah.Model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBoard {

    List<GameNode> p1_state = new ArrayList<>();
    List<GameNode> p2_state = new ArrayList<>();
    int houses;

    public abstract boolean playerMove(int player, int house);

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
