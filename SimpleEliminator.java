

/**
 * Implementação do interface Eliminator.
 * @author Grupo 02 (fc62249, 62817, fc60932)
 */

public class SimpleEliminator implements Eliminator {

    /**
     * Procura uma sequência de símbolos no array seq e substitui-os pelo símbolo nothing.
     * @param seq O array de símbolos onde procurar sequências.
     * @param blockSize O tamanho mínimo da sequência para esta ser eliminada.
     * @param nothing O símbolo a usar para substituir os símbolos eliminados (representa o vazio).
     * @return O número de símbolos que foram efetivamente eliminados.
     */

    @Override
    public int eliminateSequence(Symbol[] seq, int blockSize, Symbol nothing) {
        int eliminatedCount = 0;
        int i = 0;

        while (i < seq.length) {
            // Ignorar se a posição atual já for "vazia"
            if (seq[i] == nothing) {
                i++;
                continue;
            }
            // Conta quantos símbolos iguais existem consecutivamente a partir de i
            int count = 1;
            int j = i + 1;
            while (j < seq.length && seq[j] == seq[i]) {
                count++;
                j++;
            }
            // Se a sequência for grande o suficiente, elimina
            if (count >= blockSize) {
                for (int k = 0; k < count; k++) {
                    seq[i + k] = nothing; // Substitui pelo símbolo vazio
                }
                eliminatedCount += count;
            }
            // Avança o índice principal para depois da sequência analisada
            i += count;
        }
        return eliminatedCount;
    }


}
