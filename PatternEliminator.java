

import java.util.List;

/**
 * A class that represents eliminators that know how to eliminate 
 * any occurrence of any of a given set of pattern sequences
 * @author PCO Team
 */
public class PatternEliminator implements Eliminator {
	private List<Symbol[]> patterns;

	/**
	 * The new eliminator will know how to eliminate, 
	 * through method eliminateSequence, any occurrence of any
	 * of the pattern sequences defined in list patterns
	 * @param pattern List of pattern sequences
	 * @requires patterns != null
	 */
	public PatternEliminator(List<Symbol[]> patterns) {
		this.patterns = patterns;
	}
	
	/**
	 * Eliminates, in array sequence, any occurrence 
     * of any of the pattern sequences defined in the constructor 
     * (by order in that list of patterns);  
     * Eliminating a symbol means replacing it with symbols nothing;  
	 * @param sequence Array with symbols where the elimination is to
	 *        take place
	 * @param blockSize This parameter is ignored
	 * @param nothing The symbol to be used to replace eliminated
	 *                symbols, creating "holes"
	 * @requires sequence != null and nothing != null
	 * @return 0 in case no sequence has been eliminated; 
	 *         the number of eliminated elements otherwise
	 */
	@Override
	public int eliminateSequence(Symbol[] sequence, int blockSize, Symbol nothing) {
		// Eliminate one occurrence of the pattern in the sequence
		int nrEquals = 0;
		int eliminated = 0;
		int p = 0;
		while(p < this.patterns.size() && eliminated == 0) {
			Symbol[] patt = patterns.get(p);
			int i = 0;
			while(i < sequence.length && eliminated == 0) {
				int j = 0;
				int k = i;
				while(j < patt.length && k < sequence.length && sequence[k] == patt[j]) {
					nrEquals++;
					k++; j++;
				}
				if(nrEquals == patt.length) {
					eliminated = nrEquals;
					for(int l = i; l < i + nrEquals; l++)
						sequence[l] = nothing;

				} else {
					i++;
					nrEquals = 0;
				}
			}
			p++;
		}
		return eliminated;
	}

}
