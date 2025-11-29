

/**
 * An implementation of the Symbol interface
 * @author PCO Team
 */
public enum Balls implements Symbol {
	RED("🔴"),
	ORANGE("🟠"), 
	YELLOW("🟡"), 
	GREEN("🟢"), 
	BLUE("🔵"), 
	EMPTY("⚪️");
	
	private String rep;
	Balls(String s) {
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
	public Balls[] symbols() {
		return Balls.values();
	}

}
