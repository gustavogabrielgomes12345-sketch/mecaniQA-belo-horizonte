public class Ordenador {

    public static void ordenarPecas(Peca[] array, int tamanho, ChaveOrdenacao chave) {
        for (int i = 1; i < tamanho; i++) {
            Peca chaveAtual = array[i];
            int j = i - 1;

            while (j >= 0 && deveTrocarPeca(array[j], chaveAtual, chave)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chaveAtual;
        }
    }

    private static boolean deveTrocarPeca(Peca a, Peca b, ChaveOrdenacao chave) {
        if (chave == ChaveOrdenacao.NOME) {
            return a.nomePeca.compareToIgnoreCase(b.nomePeca) > 0;
        } else {
            return a.codigo > b.codigo;
        }
    }

    public static void ordenarServicos(Servico[] array, int tamanho, ChaveOrdenacao chave) {
        for (int i = 1; i < tamanho; i++) {
            Servico chaveAtual = array[i];
            int j = i - 1;

            while (j >= 0 && deveTrocarServico(array[j], chaveAtual, chave)) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chaveAtual;
        }
    }

    private static boolean deveTrocarServico(Servico a, Servico b, ChaveOrdenacao chave) {
        if (chave == ChaveOrdenacao.NOME) {
            return a.nomeServico.compareToIgnoreCase(b.nomeServico) > 0;
        } else {
            return a.codigo > b.codigo;
        }
    }
}