

/**
 * A class that represents accomodators that simulate  
 * fatal falls of symbols. 
 * @author PCO Team
 *
 */
public class FatalFallAccomodator implements Accomodator {
	private int fatalHeight;
	
	/**
	 * Construtor
	 * @param fatalHeight Represents the “height” from which 
	 *                    all the symbols "above" the ones that 
	 *                    are accomodated should "die" 
	 */
	public FatalFallAccomodator(int fatalHeight) {
		this.fatalHeight = fatalHeight;
	}

	/**
	 * Accomodates all the nothing symbols in array sequence; 
	 * If the number of symbols to accomodate is greater than 
	 * the fatalHeight defined in the constructor, then the 
	 * symbols "above" the ones that are accomodated should 
	 * disappear; "above" means all the ones that are in the 
	 * positions closer to the beginning of the array sequence
	 * @param sequence Array with symbols to accomodate
	 * @param nothing The symbol representing holes
	 * @requires sequence != null and nothing != null
	 */
	@Override
	public void accomodate(Symbol[] sequence, Symbol nothing) {
		boolean needToAcomodate = true;
		while(needToAcomodate) {
			int howManyHoles = 0;
			int firstFilled = 0;
			for(int i = 0 ; i < sequence.length && sequence[i] == nothing ; i++) {
				firstFilled++;
			}
			// firstFilled has the position of the first element different from nothing
			int firstEmpty = firstFilled;
			for(int i = firstFilled ; i < sequence.length && sequence[i] != nothing ; i++) {
				firstEmpty++;
			}
			// firstEmpty has the position of the first element equal to nothing
			int l = firstEmpty;
			while(l < sequence.length && sequence[l] == nothing) {
				howManyHoles++; 
				l++;
			}
			// howManyHoles has the number of positions in the sequence that are "holes"
			if(howManyHoles >= this.fatalHeight) {
				// turns empty all positions between firstFilled and firstEmpty - 1
				for(int i = firstFilled ; i < firstEmpty ; i++) {
					sequence[i] = nothing;
				}
			} else {
				for(int i = firstEmpty + howManyHoles - 1 ; i >= howManyHoles ; i--) {
					sequence[i] = sequence[i - howManyHoles];
				}
				for(int i = firstFilled + howManyHoles - 1 ; i >= 0 ; i--) {
					sequence[i] = nothing;
				}				
			}
			needToAcomodate = howManyHoles != 0;
		}
	}

}
