import java.util.List;
import java.util.Random;


/**
 * Classe abstrata Game que implementa a interface Game
 * @author grupo XX (fc62249, 62817, fc60932)
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

    @Override
    public int linesInGrid(){
        return playArea.gridDimensions()[0];
    }

    @Override
    public int colsInGrid(){
        return playArea.gridDimensions()[1];
    }

    @Override
    public void permutatePiece(int n) {
        if (currentPiece != null) {
            currentPiece.permutation(n);
        }
    }

    @Override
    public void placePiece(int col){
        playArea.placePiece(currentPiece,  col-1);

        List<Integer> eliminatedCount = playArea.eliminateAccomodateAll(Game.SIZE_OF_PIECE);
        registerPlayScore(eliminatedCount);
    }

    @Override
    public void generatePiece(){
        this.currentPiece = new Piece(random, Game.SIZE_OF_PIECE, playArea.emptySymbol(), symbols);

    }

    @Override
    public int spaceInColumn(int col){
        return playArea.spaceInColumn(col-1);
    }

    @Override
    public String currentPiece(){
        return currentPiece.toString();
    }

    @Override
    public boolean finished() {
        for (int c = 1; c <= colsInGrid(); c++) {
            if (spaceInColumn(c) >= Game.SIZE_OF_PIECE) {
                return false;
            }
        }
        return true;
    }

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

