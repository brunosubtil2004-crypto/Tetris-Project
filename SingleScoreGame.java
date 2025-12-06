import java.util.List;
import java.util.Random;


/**
 * Implementação do modo de jogo SingleScoreGame.
 * @author Grupo 02 (fc62249, 62817, fc60932)
 */

public class SingleScoreGame extends AbstractGame {

    private int points;
    public static final int PLAY_SCORE = 10;

    /**
     * Construtor do SingleScoreGame.
     * Inicializa o jogo e coloca a pontuação a zero.
     * @param r Número de linhas.
     * @param c Número de colunas.
     * @param diff Dificuldade inicial (linhas preenchidas).
     * @param empty Símbolo vazio.
     * @param values Símbolos possíveis.
     * @param gen Gerador aleatório.
     * @param elim Eliminador a usar.
     * @param acc Acomodador a usar.
     */

    public SingleScoreGame(int r, int c, int diff, Symbol empty, Symbol[] values,
                           Random gen, Eliminator elim, Accomodator acc) {
        super(r, c, diff, empty, values, gen, elim, acc);
        this.points = 0;
    }


    /**
     * Atualiza a pontuação do jogo.
     * Neste modo, por cada jogada válida efetuada, o utilizador recebe 10 pontos,
     * independentemente do número de eliminações.
     * @param eliminated Lista que contém o número de símbolos eliminados.
     */
    @Override
    public void registerPlayScore(List<Integer> eliminated) {
        this.points += PLAY_SCORE;
    }


    /**
     * Devolve a pontuação corrente do jogo.
     * @return A pontuação atual.
     */
    @Override
    public int score() {
        return this.points;
    }


    /**
     * Verifica se o jogo terminou.
     * O jogo termina se não houver espaço para jogar (regra base) OU
     * se a área de jogo estiver completamente vazia.
     * @return true se o jogo acabou, false caso contrário.
     */
    @Override
    public boolean finished() {
        if (super.finished()) return true;

        int totalLines = linesInGrid();
        for (int c = 1; c <= colsInGrid(); c++) {
            if (spaceInColumn(c) < totalLines) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devolve a representação textual do jogo.
     * Adiciona a pontuação simples no final da representação base.
     * @return String formatada com a grelha, peça e score.
     */
    @Override
    public String toString() {
        String base = super.toString();
        return base + "Score: " + score();
    }


}