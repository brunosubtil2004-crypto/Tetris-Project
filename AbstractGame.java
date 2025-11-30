import java.util.Random;


/**
 * Classe abstrata Game que implementa a interface Game
 *
 *
 */

public abstract class AbstractGame implements Game {

    PlayArea playArea;
    Piece currentPiece;
    Random random;
    Symbol[] symbols;




    public AbstractGame(int r, int c, int diff, Symbol empty, Symbol [] values, Random gen,
                        Eliminator elim, Accomodator acc) {

        this.random = gen;
        this.symbols = values;
        this.playArea = new PlayArea(r, c, diff, empty, values, gen, elim, acc);


        generatePiece();
    }


    public int linesInGrid(){
        return playArea.gridDimensions()[0];
    }




}
