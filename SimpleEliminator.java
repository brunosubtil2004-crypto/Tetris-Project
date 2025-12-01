

public class SimpleEliminator implements Eliminator {

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
