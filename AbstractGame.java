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

        // Cria a primeira peça do jogo
        generatePiece();
    }

    public int linesInGrid(){
        return playArea.gridDimensions()[0];
    }

    public int colsInGrid(){
        return playArea.gridDimensions()[1];
    }

    public void permutaPiece(int n){
        if (currentPiece != null) {
            currentPiece.permutation(n);
        }
    }

    public void placePiece(int col){
        playArea.placePiece(currentPiece,  col);

        List<Integer> eliminatedCount = playArea.eliminateAccomodateAll(Game.SIZE_OF_PIECE);
        registerPlayScore(eliminatedCount);

        generatePiece();
    }

    public void generatePiece(){
        this.currentPiece = new Piece(random, Game.SIZE_OF_PIECE, playArea.emptySymbol(), symbols);

    }

    public int spaceInColumn(int col){
        return playArea.spaceInColumn(col);
    }

    public String currentPiece(){
        return currentPiece.toString();
    }

    public boolean finished(){
        return !playArea.hasEnoughSpace(Game.SIZE_OF_PIECE);
    }

    public String toString(){
        return playArea.currentGrid();
    }



}
