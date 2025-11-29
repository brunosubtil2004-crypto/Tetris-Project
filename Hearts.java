

/**
 * An implementation of the Symbol interface
 * @author PCO Team
 */
public enum Hearts implements Symbol {
	RED("❤️"),
	ORANGE("🧡"), 
	YELLOW("💛"), 
	GREEN("💚"), 
	BLUE("💙"), 
	EMPTY("🤍");
	
	private String rep;
	Hearts(String s) {
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
	public Hearts[] symbols() {
		return Hearts.values();
	}
}
