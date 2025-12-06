import java.util.List;
import java.util.Random;
/**
 * Implementação do modo de jogo MultipleRewardGame.
 * @author Grupo 02 (fc62249, 62817, fc60932)
 */


public class MultipleRewardGame extends AbstractGame {

    // Contadores separados
    private int playPoints;
    private int eliminationPoints;

    /**
     * Construtor do MultipleRewardGame.
     * Inicializa o jogo e coloca a pontuação a zero.
     * @param r Número de linhas.
     * @param c Número de colunas.
     * @param diff Dificuldade inicial.
     * @param empty Símbolo vazio.
     * @param values Símbolos possíveis.
     * @param gen Gerador aleatório.
     * @param elim Eliminador a usar.
     * @param acc Acomodador a usar.
     */
    public MultipleRewardGame(int r, int c, int diff, Symbol empty, Symbol[] values,
                              Random gen, Eliminator elim, Accomodator acc) {
        super(r, c, diff, empty, values, gen, elim, acc);
        this.playPoints = 0;
        this.eliminationPoints = 0;
    }

    /**
     * Atualiza a pontuação do jogo com base nas regras de recompensa.
     * - 10 pontos por jogada.
     * - 200 pontos base por 3 símbolos (+50 por cada extra).
     * - Multiplicador (x1, x2, x3) aplicado sequencialmente a cada eliminação na mesma jogada.
     * @param eliminated Lista que contém o número de símbolos eliminados em cada passo da jogada.
     */
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

    /**
     * Calcula a pontuação total do jogo.
     * @return A soma dos pontos de jogada e pontos de eliminação.
     */
    @Override
    public int score() {
        return this.playPoints + this.eliminationPoints;
    }

    /**
     * Devolve a representação textual do jogo, incluindo a grelha, a peça
     * e a pontuação.
     * @return String formatada com o estado do jogo.
     */
    @Override
    public String toString() {
        String base = super.toString();
        String scoreLine = "Score of plays: " + this.playPoints +
                "   Score of eliminations: " + this.eliminationPoints;

        return base + scoreLine;
    }


}