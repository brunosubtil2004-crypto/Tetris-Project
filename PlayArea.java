

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * The instances of this class represent areas where games can be
 * played. They perform necessary placements of pieces, with 
 * consequent eliminations and accomodations of elements of the grid.
 * 
 * @author PCO Team
 *
 */
public class PlayArea {
	private Symbol[][] grid;
	private Symbol[] values;
	public final Symbol empty;
	private Eliminator eliminator;
	private Accomodator accomodator;

	/**
	 * Constructor. Initializes the attributes of the new instance
	 * @param nRows Number of rows of the grid of this area
	 * @param nCols Number of columns of the grid of this area
	 * @param diffic Number of rows that should be initially filled with
	 *               randomly chosen symbols
	 * @param generator The Random generator
	 * @requires nRows > 0 and nCols > 0 and diffic >= 0 and diffic <= nRows
	 * @requires empty != null and symbs != null and generator != null
	 * @requires elim != null and acc != null
	 */
	public PlayArea(int nRows, int nCols, int diffic, 
			        Symbol empty, Symbol[] symbs, Random generator, 
			        Eliminator elim, Accomodator acc) {
		this.empty = empty;
		this.values = symbs;
		this.grid = new Symbol[nRows][nCols];
		// The element of this.grid at index nRows-1 corresponds
		// to the bottom line of the area; the top line corresponds
		// to index zero
		for(int i = 0 ; i < nRows - diffic ; i++) {
			for(int j = 0 ; j < nCols ; j++) {
				this.grid[i][j] = empty;
			}			
		}
		for(int i = nRows - diffic ; i < nRows ; i++) {
			for(int j = 0 ; j < nCols ; j++) {
				int rand = generator.nextInt(symbs.length);
				this.grid[i][j] = values[rand];
			}			
		}
		this.eliminator = elim;	
		this.accomodator = acc;	
		this.accomodate(); // to avoid beginning with holes
	}

	/**
	 * 
	 * @return
	 */
	public int[] gridDimensions() {
		return new int[] {grid.length,grid[0].length};
	}
	
	/**
	 * 
	 * @return
	 */
	public Symbol emptySymbol() {
		return this.empty;
	}

	/**
	 * 
	 * @return
	 */
	public Symbol[] allSymbols() {
		return this.values;
	}

	/**
	 * Places a given piece in a given column of this play area
	 * @param piece The piece to place
	 * @param column The column where to put the piece
	 * @requires column >= 0 and column < gridDimensions()[1] and piece != null
	 */
	public void placePiece(Piece piece, int column) {
		Symbol[] sequence = piece.symbols();
		int l = 0;
		while(l < this.grid.length && this.grid[l][column] == empty) {
			l++;
		}
        l--;

        for(int i = sequence.length - 1 ; i >= 0 ; i--) {
        	this.grid[l][column] = sequence[i];
        	l--;
        }
	}

	/**
	 * This play area has a given number or more of free positions 
	 * in some column?
	 * @param sizeOfPiece The number of positions that are of interest
	 * @return True if some column of this play area has sizeOfPiece or
	 *         more positions free; False otherwise
	 */
	public boolean hasEnoughSpace(int sizeOfPiece) {
		for(int j = 0 ; j < this.grid[0].length ; j++) {
			if(spaceInColumn(j) >= sizeOfPiece) {
				return true;
			}
		}
		return false;		
	}

	/**
	 * How many empty positions are there in a given column? 
	 * @param col The column of interest
	 * @requires column >= 0 and column < gridDimensions()[1]
	 */
	public int spaceInColumn(int col) {
		int empties = 0;

		boolean stillEmpty = true;
		for(int i = 0 ; i < this.grid.length && stillEmpty; i++){
			if(this.grid[i][col] == this.empty) {
				empties++;
			} else {
				stillEmpty = false;
			}
		}
		return empties;		
	}
	
	/**
	 * Perform all necessary eliminations and consequent accomodations
	 * on the grid of this play area
	 * @param blockSize The minimum number of positions to be
	 *                  eliminated
	 * @return A list containing the number of eliminated elements
	 *         in the several elimination rounds
	 */
	public List<Integer> eliminateAccomodateAll(int blockSize) {
		List<Integer> result = new ArrayList<Integer>();
		int eliminated = eliminate(blockSize);

		while(eliminated != 0) {
			result.add(eliminated);
			accomodate();
			eliminated = eliminate(blockSize);
		}
		return result;
	}
	
	/** *
	 * Textual representation of the grid of this play area 
	 * @return The symbols of the grid, line by line, separated
	 *         by a vertical line 
	*/
	public String currentGrid() {
		StringBuilder result = new StringBuilder();
		for(Symbol[] line : this.grid) {
			for(Symbol simb : line) {
				result.append("|" + simb.toString());
			}
			result.append("|" + System.lineSeparator());
		}

		return result.toString();
	}

/*
 * ***********************************************
 * ************  Auxiliary methods ***************
 * ***********************************************
 */

	/**
	 * Accomodates all internal positions of the grid of this play area
	 * containing the symbol nothing()
	 */
	private void accomodate() {
		Symbol[][] transpose = new Symbol[grid[0].length][grid.length];
		for(int i = 0 ; i < grid.length ; i++) {
			for(int j = 0 ; j < grid[0].length ; j++) {
				transpose[j][i] = grid[i][j];
			}			
		}	
		for(int i = 0 ; i < transpose.length ; i++) {
			this.accomodator.accomodate(transpose[i], this.empty);
		}
		for(int i = 0 ; i < transpose.length ; i++) {
			for(int j = 0 ; j < transpose[0].length ; j++) {
				grid[j][i] = transpose[i][j];
			}			
		}
	}

	/**
	 * Performs the necessary eliminations on the grid 
	 * @param blockSize The minimum number of positions to be
	 *                  eliminated
	 * @return The number of eliminated elements
	 */
	private int eliminate(int blockSize) {

		int nLin = this.grid.length;
		int nCol = this.grid[0].length;
		int eliminPoints = 0;

		int i = 0;
		while(eliminPoints == 0 && i < nLin) {

			Symbol[] linha = this.grid[i];
			eliminPoints = this.eliminator.eliminateSequence(linha, blockSize,this.empty);
			this.grid[i] = linha;

			i++;
		}

		int j = 0;
		// If eliminPoints is zero, it means that no horizontal
		// eliminations happened, so it's time for vertical eliminations
		// If eliminPoints is not zero, it means that one or more 
		// horizontal eliminations happened, so it's not time yet for 
		// vertical eliminations (first the area must be accomodated)
		while(eliminPoints == 0 && j < nCol) {

			Symbol[] coluna = obtainColumn(j);
			eliminPoints = this.eliminator.eliminateSequence(coluna, blockSize,this.empty);
			fillColumn(coluna, j);

			j++;
		}
		return eliminPoints;
	}
	
	/**
	 * The symbols of a given column of the grid of this play area
	 * @param colIndex The column number
	 * @return An array with the symbols of column number colIndex
	 * @requires colIndex >= 0 and colIndex < gridDimensions()[1]
	 */
	private Symbol[] obtainColumn(int colIndex) {

		Symbol[] column = new Symbol[this.grid.length];

		for(int i = 0; i < this.grid.length; i++)
			column[i] = this.grid[i][colIndex];

		return column;
	}

	/**
	 * Fills a given column of the grid of this play area with the symbols
	 * in a given array of symbols
	 * @param symbs The array of symbols to be used to fill the column
	 * @param colIndex The column number
	 * @requires symbs != null and colIndex >= 0 and colIndex < gridDimensions()[1]
	 */
	private void fillColumn(Symbol[] symbs, int colIndex) {

		for(int i = 0; i < this.grid.length; i++) {
			this.grid[i][colIndex] = symbs[i];
		}
	}
}
