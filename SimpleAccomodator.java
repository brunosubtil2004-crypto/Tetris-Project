



public class SimpleAccomodator implements Accomodator{

    @Override
    public void accomodate(Symbol[] seq, Symbol nothing) {
        // Vamos escrever do fim para o início (do fundo para o topo)
        int writeIndex = seq.length - 1;

        // 1. Percorrer o array original de trás para a frente
        for (int readIndex = seq.length - 1; readIndex >= 0; readIndex--) {
            // Se encontrarmos um símbolo válido (que não é "nothing")
            if (seq[readIndex] != nothing) {
                // Copiamo-lo para a posição de escrita mais baixa disponível
                seq[writeIndex] = seq[readIndex];
                writeIndex--;
            }
        }
        // 2. Preencher o restante do array (o topo) com 'nothing'
        // 'writeIndex' parou na última posição que ainda não foi preenchida
        while (writeIndex >= 0) {
            seq[writeIndex] = nothing;
            writeIndex--;
        }
    }

}
