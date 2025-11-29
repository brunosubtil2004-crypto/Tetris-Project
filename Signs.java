

/**
 * An implementation of the Symbol interface
 * @author PCO Team
 */
public enum Signs implements Symbol {
	CROSS("❌"),
	CIRCLE("⭕️"), 
	BALL("🛑"), 
	BANGS("‼️"), 
	CANNOT("🚫"), 
	EMPTY("❕");
	
	private String rep;
	Signs(String s) {
		this.rep = s;
	}

	/**
	 * The textual representation of this symbol
	 */
	public String toString() {
		return this.rep;
	}

	/**
	 * All the symbols of the enum
	 */
	public Signs[] symbols() {
		return Signs.values();
	}
}
