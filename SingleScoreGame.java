import java.util.List;
import java.util.Random;

public class SingleScoreGame extends AbstractGame {

    private int currentScore;

    public SingleScoreGame(int r, int c, int diff, Symbol empty, Symbol[] values,
                              Random gen, Eliminator elim, Accomodator acc) {
        super(r, c, diff, empty, values, gen, elim, acc);
        this.currentScore = 0;
    }

    public void registerPlayScore(List<Integer> eliminated) {
        this.currentScore += 10;
    }


    public boolean finished() {
        // Termina se a grid estiver vazia OU se não couberem peças.
        if (super.finished()) {
            return true;
        }

        // Verificar se a grid está completamente vazia
        // Se todas as colunas tiverem espaço igual ao número total de linhas, está vazia.
        int totalCols = colsInGrid();
        int totalRows = linesInGrid();
        boolean isEmpty = true;

        for (int c = 0; c < totalCols; c++) {
            if (spaceInColumn(c) != totalRows) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }



    public int score() {
        return this.currentScore;
    }

    public String toString() {
        return "Score: " + score() + System.lineSeparator() + super.toString();
    }




}
