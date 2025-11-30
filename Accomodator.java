


/** Interface que define o comportamento de acomodamento de símbolos. */
public interface Accomodator {

    /**
     * Acomoda uma sequência de símbolos de modo a "fechar os buracos",
     * ou seja, todas as sequências contendo o símbolo nothing.
     * @param seq A sequência de símbolos a acomodar.
     * @param nothing O símbolo que representa o espaço vazio (buraco).
     */
    
    void accomodate(Symbol[] seq, Symbol nothing);


}
