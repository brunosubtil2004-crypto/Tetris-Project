import java.util.List;
import java.util.Random;


/**
 * Classe abstrata Game que implementa a interface Game
 * @author grupo 02 (fc62249, 62817, fc60932)
 *
 */

public abstract class AbstractGame implements Game {

    PlayArea playArea;
    Piece currentPiece;
    Random random;
    Symbol[] symbols;

    /**
     * Construtor
     * Inicializa a área de jogo e gera a primeira peça.
     * @param row Número de linhas.
     * @param col Número de colunas.
     * @param diff Dificuldade (linhas preenchidas).
     * @param empty Símbolo vazio.
     * @param values Símbolos possíveis.
     * @param gen Gerador aleatório.
     * @param elim Eliminador a usar.
     * @param acc Acomodador a usar.
     */

    public AbstractGame(int row, int col, int diff, Symbol empty, Symbol [] values, Random gen,
                        Eliminator elim, Accomodator acc) {

        this.random = gen;
        this.symbols = values;

        // Inicializa a área de jogo com os parâmetros recebidos
        this.playArea = new PlayArea(row, col, diff, empty, values, gen, elim, acc);
    }


    /**
     * Devolve o número de linhas na área do jogo.
     * @return O número de linhas na área do jogo.
     */
    @Override
    public int linesInGrid(){
        return playArea.gridDimensions()[0];
    }

    /**
     * Devolve o número de colunas na área do jogo.
     * @return O número de colunas na área do jogo.
     */
    @Override
    public int colsInGrid(){
        return playArea.gridDimensions()[1];
    }


    /**
     * Aplica uma permutação de n posições na peça corrente do jogo.
     * @param n int
     */
    @Override
    public void permutatePiece(int n) {
        if (currentPiece != null) {
            currentPiece.permutation(n);
        }
    }

    /**
     * Coloca na coluna col da área de jogo a peça corrente do jogo e aplica as necessárias
     * eliminações e acomodações e calcula e atualiza a pontuação do jogo.
     * @param col int
     */
    @Override
    public void placePiece(int col){
        playArea.placePiece(currentPiece,  col-1);

        List<Integer> eliminatedCount = playArea.eliminateAccomodateAll(Game.SIZE_OF_PIECE);
        registerPlayScore(eliminatedCount);
    }

    /**
     * Gera uma nova peça, esta peça passa a ser peça corrente do jogo.
     */
    @Override
    public void generatePiece(){
        this.currentPiece = new Piece(random, Game.SIZE_OF_PIECE, playArea.emptySymbol(), symbols);

    }

    /**
     * Devolve o número de espaços vazios na coluna col.
     * @param col int
     * @return devolve os espaços vazios na coluna.
     */
    @Override
    public int spaceInColumn(int col){
        return playArea.spaceInColumn(col-1);
    }

    /**
     * Devolve uma representação textual da peça corrente do jogo.
     * @return String da peça corrente do jogo.
     */
    @Override
    public String currentPiece(){
        return currentPiece.toString();
    }

    /**
     * Verifica se o jogo terminou.
     * Considera-se terminado se não houver espaço para colocar uma peça
     * (de tamanho SIZE_OF_PIECE) em nenhuma das colunas.
     * @return true se o jogo acabou, false caso contrário.
     */
    @Override
    public boolean finished() {
        for (int c = 1; c <= colsInGrid(); c++) {
            if (spaceInColumn(c) >= Game.SIZE_OF_PIECE) {
                return false;
            }
        }
        return true;
    }

    /**
     * Devolve a representação textual do estado atual do jogo (Grelha e Peça).
     * @return String formatada com a grelha e a peça corrente.
     */
    @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            sb.append("Current grid: ").append(System.lineSeparator());
            sb.append(playArea.currentGrid());

            sb.append("Current piece: ").append(System.lineSeparator());
            if (currentPiece != null) {
                sb.append(currentPiece.toString());
            }

            return sb.toString();
        }


    }

