public class Gerenciador {

    private static final int MAX_PECAS = 100;
    private static final int MAX_SERVICOS = 50;

    // Boa, parabéns! O uso de arrays estáticos unidimensionais ajuda a realizar o
    // armazenamento de dados corretamente sem depender de Collections.
    private static Peca[] pecas = new Peca[MAX_PECAS];
    private static Servico[] servicos = new Servico[MAX_SERVICOS];

    private static int totalPecas = 0;
    private static int totalServicos = 0;

    private static int proximoCodigoPeca = 1;
    private static int proximoCodigoServico = 1;

    // ==================== BUSCA LINEAR ====================

    // Boa, parabéns! O uso de um laço de repetição com verificação de nulidade
    // ajuda a realizar a Busca Linear com segurança para evitar
    // NullPointerException.
    // eu gostei da ideia do tamanhoAtual, pois reduz o laço, evitando iterar sobre
    // posições vazias
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

    public static int cadastrarPeca(String nomePeca, String fabricante, double precoCusto, double precoVenda,
            int quantidadeEstoque) {
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

        // Boa, parabéns! O uso da variável controladora totalPecas como índice ajuda a
        // realizar a inserção de forma eficiente no primeiro espaço vazio.
        pecas[totalPecas] = novaPeca;
        totalPecas++;
        proximoCodigoPeca++;

        System.out.println("[OK] Peça cadastrada. Código gerado: " + novaPeca.codigo);
        return novaPeca.codigo;
    }

    public static int cadastrarServico(String nomeServico, String descricao, int tempoEstimadoMinutos,
            double valorMaoObra) {
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

        // Boa, parabéns! O uso da variável controladora totalServicos como índice ajuda
        // a realizar a inserção de forma eficiente no primeiro espaço vazio.
        servicos[totalServicos] = novoServico;
        totalServicos++;
        proximoCodigoServico++;

        System.out.println("[OK] Serviço cadastrado. Código gerado: " + novoServico.codigo);
        return novoServico.codigo;
    }

    // ==================== READ ====================

    public static void listarPecas() {
        System.out.println("\n===== LISTA DE PEÇAS (" + totalPecas + "/" + MAX_PECAS + ") =====");
        if (totalPecas == 0) {
            System.out.println("Nenhuma peça cadastrada.");
            return; // isso é um hábito comum em programção, usando o early return, mas é opcional
                    // se você usar if/else,
            // mas ajuda na legibilidade e organização do código.
        }
        // aqui não teria sido melhor usar um for each?
        // for (Peca p : pecas) {
        for (int i = 0; i < totalPecas; i++) {
            Peca p = pecas[i];
            // Boa, parabéns! O uso de printf permite formatar a saída dos dados de forma
            // organizada e legível.
            // mas, talvez, não teria sido melhor imprimir uma linha com o cabeçalho antes
            // do laço de repetição?
            System.out.printf(
                    "Código: %d | Nome: %s | Fabricante: %s | Custo: R$ %.2f | Venda: R$ %.2f | Estoque: %d%n",
                    p.codigo, p.nomePeca, p.fabricante, p.precoCusto, p.precoVenda, p.quantidadeEstoque);
        }
    }

    public static void listarServicos() {
        System.out.println("\n===== LISTA DE SERVIÇOS (" + totalServicos + "/" + MAX_SERVICOS + ") =====");
        if (totalServicos == 0) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        // aqui não teria sido melhor usar um for each?
        // por exemplo:
        // for (Servico s : servicos)
        for (int i = 0; i < totalServicos; i++) {
            Servico s = servicos[i];
            // mesma coisa aqui também... não seria mlehor ter uma linha com o cabeçalho
            // antes do laço de repetição?
            System.out.printf("Código: %d | Nome: %s | Descrição: %s | Tempo: %d min | Mão de obra: R$ %.2f%n",
                    s.codigo, s.nomeServico, s.descricao, s.tempoEstimadoMinutos, s.valorMaoObra);
        }
    }

    // ==================== UPDATE ====================

    // Vocês estavam indo por um bom caminho, mas o método estava com visibilidade
    // de pacote em vez de public. Vocês poderiam fazer da seguinte forma:
    public static boolean atualizarPeca(int codigo, String nomePeca, String fabricante, double precoCusto,
            double precoVenda, int quantidadeEstoque) {
        // Boa, parabéns! O uso da chamada da busca linear já implementada ajuda a
        // realizar a reutilização de código no processo de atualização.
        int indice = buscarPecaPorCodigo(pecas, totalPecas, codigo);
        if (indice == -1) {
            System.out.println("[ERRO] Peça com código " + codigo + " não encontrada.");
            return false;
        }

        Peca p = pecas[indice];

        p.nomePeca = nomePeca;
        p.fabricante = fabricante;
        p.precoCusto = precoCusto;
        p.precoVenda = precoVenda;
        p.quantidadeEstoque = quantidadeEstoque;

        System.out.println("[OK] Peça código " + codigo + " atualizada.");
        return true;
    }

    // seguiu bem a lógica de chamar o método de busca
    public static boolean atualizarServico(int codigo, String nomeServico, String descricao, int tempoEstimadoMinutos,
            double valorMaoObra) {
        int indice = buscarServicoPorCodigo(servicos, totalServicos, codigo);
        if (indice == -1) {
            System.out.println("[ERRO] Serviço com código " + codigo + " não encontrado.");
            return false;
        }

        Servico s = servicos[indice];
        s.nomeServico = nomeServico;
        s.descricao = descricao;
        s.tempoEstimadoMinutos = tempoEstimadoMinutos;
        s.valorMaoObra = valorMaoObra;

        System.out.println("[OK] Serviço código " + codigo + " atualizado.");
        return true;
    }

    // ==================== DELETE ====================

    public static boolean removerPeca(int codigo) {
        int indice = buscarPecaPorCodigo(pecas, totalPecas, codigo);
        if (indice == -1) {
            System.out.println("[ERRO] Peça com código " + codigo + " não encontrada.");
            return false;
        }

        // Boa, parabéns! O uso de um laço for a partir do índice removido ajuda a
        // realizar o deslocamento dos elementos seguintes, reorganizando o array sem
        // deixar buracos.
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

    // ==================== ACESSO AUXILIAR (para testes) ====================

    public static Peca[] getPecas() {
        return pecas;
    }

    public static Servico[] getServicos() {
        return servicos;
    }

    public static int getTotalPecas() {
        return totalPecas;
    }

    public static int getTotalServicos() {
        return totalServicos;
    }
}
