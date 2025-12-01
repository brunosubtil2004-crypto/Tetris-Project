import java.util.List;
import java.util.Random;

public class SingleScoreGame extends AbstractGame {

    private int currentScore;

    public SingleScoreGame(int r, int c, int diff, Symbol empty, Symbol[] values,
                           Random gen, Eliminator elim, Accomodator acc) {
        super(r, c, diff, empty, values, gen, elim, acc);
        this.currentScore = 0;
    }

    @Override
    public void registerPlayScore(List<Integer> eliminated) {
        this.currentScore += 10;
    }

    @Override
    public int score() {
        return this.currentScore;
    }

    @Override
    public boolean finished() {
        if (super.finished()) return true;

        int totalRows = linesInGrid();
        for (int c = 1; c <= colsInGrid(); c++) {
            if (spaceInColumn(c) < totalRows) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String base = super.toString();
        return base + "Score: " + score();
    }


}