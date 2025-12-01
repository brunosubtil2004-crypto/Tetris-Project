import java.util.List;
import java.util.Random;

public class MultipleRewardGame extends AbstractGame {

    private int playPoints;
    private int eliminationPoints;

    public MultipleRewardGame(int r, int c, int diff, Symbol empty, Symbol[] values,
                              Random gen, Eliminator elim, Accomodator acc) {
        super(r, c, diff, empty, values, gen, elim, acc);
        this.playPoints = 0;
        this.eliminationPoints = 0;
    }

    @Override
    public void registerPlayScore(List<Integer> eliminated) {
        this.playPoints += 10;

        int multiplier = 1;

        for (int numSymbols : eliminated) {
            if (numSymbols > 0) {
                // Cálculo: 200 + (excesso * 50)
                int pointsForThisSeq = 200 + ((numSymbols - 3) * 50);

                this.eliminationPoints += pointsForThisSeq * multiplier;

                multiplier++;
            }
        }
    }

    @Override
    public int score() {
        return this.playPoints + this.eliminationPoints;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String scoreLine = "Score of plays: " + this.playPoints +
                "   Score of eliminations: " + this.eliminationPoints;

        return base + scoreLine;
    }


}