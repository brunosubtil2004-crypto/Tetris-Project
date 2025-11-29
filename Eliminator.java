

/** Interface que define o comportamento de eliminação de sequências. */
public interface Eliminator {

    /**
     * Procura uma sequência de símbolos contíguos no array seq e substitui-os pelo símbolo nothing.
     * @param seq O array de símbolos onde procurar.
     * @param blockSize O tamanho mínimo da sequência para ser eliminada.
     * @param nothing O símbolo a usar para substituir os eliminados.
     * @return O número de símbolos eliminados.
     */

    int eliminateSequence(Symbol[] seq, int blockSize, Symbol nothing);

    
}