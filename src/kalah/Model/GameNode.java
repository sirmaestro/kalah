package kalah.Model;

public class GameNode {
    protected int seeds;

    public void setup(int seeds) {
        this.seeds = seeds;
    }

    public int getSeeds() {
        return this.seeds;
    }

    public void incrementSeed() {
        this.seeds += 1;
    }
}
