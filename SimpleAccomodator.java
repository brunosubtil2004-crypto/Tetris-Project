

/**
 * Implementação do interface Accomodator.
 * @author Grupo 02 (fc62249, 62817, fc60932)
 */

public class SimpleAccomodator implements Accomodator{


    /**
     * Acomoda uma sequência de símbolos de modo a "fechar os buracos" (símbolos 'nothing').
     * Os símbolos válidos são deslocados para o final do array (fundo da coluna).
     * @param seq A sequência de símbolos a acomodar.
     * @param nothing O símbolo que representa o espaço vazio (buraco).
     */

    @Override
    public void accomodate(Symbol[] seq, Symbol nothing) {
        // Vamos escrever do fim para o início (do fundo para o topo)
        int escreveIndex = seq.length - 1;

        // Percorrer o array original de trás para a frente
        for (int lerIndex = seq.length - 1; lerIndex >= 0; lerIndex--) {
            // Se encontrarmos um símbolo válido (que não é "nothing")
            if (seq[lerIndex] != nothing) {
                // Copiamo-lo para a posição de escrita mais baixa disponível
                seq[escreveIndex] = seq[lerIndex];
                escreveIndex--;
            }
        }
        // Preencher o restante do array (o topo) com 'nothing'
        // 'escreveIndex' parou na última posição que ainda não foi preenchida
        while (escreveIndex >= 0) {
            seq[escreveIndex] = nothing;
            escreveIndex--;
        }
    }

}
