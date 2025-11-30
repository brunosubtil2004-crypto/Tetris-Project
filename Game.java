import java.util.List;

/** Interface que define um jogo. **/

public interface Game {

    static final int SIZE_OF_PIECE = 3;

    /**
     * Devolve o número de linhas na área do jogo.
     * @return O número de linhas na área do jogo.
     */
    int linesInGrid();

    /**
     * Devolve o número de colunas na área do jogo.
     * @return O número de colunas na área do jogo.
     */
    int colsInGrid();

    /**
     * Aplica uma permutação de n posições na peça corrente do jogo.
     * @param col int
     */
    void permutatePiece(int col);

    /**
     * Coloca na coluna col da área de jogo a peça corrente do jogo e aplica as necessárias
     * eliminações e acomodações e calcula e atualiza a pontuação do jogo.
     * @param col int
     */
    void placePiece (int col);

    /**
     * Gera uma nova peça, esta peça passa a ser peça corrente do jogo.
     */
    void generatePiece();

    /**
     * Devolve o número de espaços vazios na coluna col.
     * @param col int
     */
    int spaceInColumn(int col);

    /**
     * Devolve True se o jogo já acabou.
     */
    boolean finished();

    /**
     * Devolve uma representação textual da peça corrente do jogo.
     */
    String currentPiece();


    /**
     * Atualiza a pontuação do jogo com os resultados de uma jogada.
     *
     * @param eliminated List integer
     * @requires eliminated contém o número de símbolos que foram eliminados na área de jogo
     */
    void registerPlayScore(List<Integer> eliminated);

    /**
     * Retorna a pontuação do jogo atual
     * @return score do jogo
     */
    int score();

}
