import java.util.List;
import java.util.Random;

public class MultipleRewardGame extends AbstractGame {

    private int currentScore;

    public MultipleRewardGame(int r, int c, int diff, Symbol empty, Symbol[] values,
                              Random gen, Eliminator elim, Accomodator acc) {
        super(r, c, diff, empty, values, gen, elim, acc);
        this.currentScore = 0;
    }

    public void registerPlayScore(List<Integer> eliminated) {
        // Pontuação base por cada jogada
        this.currentScore += 10;

        // Regra: 200 pts base (3 símbolos) + 50 pts por cada símbolo extra.
        // A 1ª sequência vale x1, a 2ª vale x2, a 3ª vale x3, etc.
        int multiplier = 1;

        for (int numSymbols : eliminated) {
            if (numSymbols > 0) {
                int pointsForThisSeq = 200 + ((numSymbols - 3) * 50);

                this.currentScore += pointsForThisSeq * multiplier;

                multiplier++;
            }
        }
    }

    public int score() {
        return this.currentScore;
    }

    public String toString() {
        return "Score: " + score() + System.lineSeparator() + super.toString();
    }

}
