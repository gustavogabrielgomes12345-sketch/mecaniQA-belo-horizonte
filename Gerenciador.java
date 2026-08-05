public class Gerenciador {

    private static final int MAX_PECAS = 100;
    private static final int MAX_SERVICOS = 50;

    private static Peca[] pecas = new Peca[MAX_PECAS];
    private static Servico[] servicos = new Servico[MAX_SERVICOS];

    private static int totalPecas = 0;      
    private static int totalServicos = 0;  

    private static int proximoCodigoPeca = 1;
    private static int proximoCodigoServico = 1;

    public static int buscarPecaPorCodigo(Peca[] array, int tamanhoAtual, int codigo) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (array[i] != null && array[i].codigo == codigo) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarServicoPorCodigo(Servico[] array, int tamanhoAtual, int codigo) {
        for (int i = 0; i < tamanhoAtual; i++) {
            if (array[i] != null && array[i].codigo == codigo) {
                return i;
            }
        }
        return -1;
    }

    // ==================== CREATE ====================

    public static int cadastrarPeca(String nomePeca, String fabricante, double precoCusto, double precoVenda, int quantidadeEstoque) {
        if (totalPecas >= MAX_PECAS) {
            System.out.println("[ERRO] Capacidade máxima de peças (" + MAX_PECAS + ") atingida.");
            return -1;
        }

        Peca novaPeca = new Peca();
        novaPeca.codigo = proximoCodigoPeca;
        novaPeca.nomePeca = nomePeca;
        novaPeca.fabricante = fabricante;
        novaPeca.precoCusto = precoCusto;
        novaPeca.precoVenda = precoVenda;
        novaPeca.quantidadeEstoque = quantidadeEstoque;

        pecas[totalPecas] = novaPeca;
        totalPecas++;
        proximoCodigoPeca++;

        System.out.println("[OK] Peça cadastrada. Código gerado: " + novaPeca.codigo);
        return novaPeca.codigo;
    }

    public static int cadastrarServico(String nomeServico, String descricao, int tempoEstimadoMinutos, double valorMaoObra) {
        if (totalServicos >= MAX_SERVICOS) {
            System.out.println("[ERRO] Capacidade máxima de serviços (" + MAX_SERVICOS + ") atingida.");
            return -1;
        }

        Servico novoServico = new Servico();
        novoServico.codigo = proximoCodigoServico;
        novoServico.nomeServico = nomeServico;
        novoServico.descricao = descricao;
        novoServico.tempoEstimadoMinutos = tempoEstimadoMinutos;
        novoServico.valorMaoObra = valorMaoObra;

        servicos[totalServicos] = novoServico;
        totalServicos++;
        proximoCodigoServico++;

        System.out.println("[OK] Serviço cadastrado. Código gerado: " + novoServico.codigo);
        return novoServico.codigo;
    }

    // ==================== DELETE ====================

    public static boolean removerPeca(int codigo) {
        int indice = buscarPecaPorCodigo(pecas, totalPecas, codigo);
        if (indice == -1) {
            System.out.println("[ERRO] Peça com código " + codigo + " não encontrada.");
            return false;
        }

        for (int i = indice; i < totalPecas - 1; i++) {
            pecas[i] = pecas[i + 1];
        }
        pecas[totalPecas - 1] = null;
        totalPecas--;

        System.out.println("[OK] Peça código " + codigo + " removida.");
        return true;
    }

    public static boolean removerServico(int codigo) {
        int indice = buscarServicoPorCodigo(servicos, totalServicos, codigo);
        if (indice == -1) {
            System.out.println("[ERRO] Serviço com código " + codigo + " não encontrado.");
            return false;
        }

        for (int i = indice; i < totalServicos - 1; i++) {
            servicos[i] = servicos[i + 1];
        }
        servicos[totalServicos - 1] = null;
        totalServicos--;

        System.out.println("[OK] Serviço código " + codigo + " removido.");
        return true;
    }
}