import java.util.ArrayList;
import java.util.List;

public class Gerenciador {

    private static final int MAX_PECAS = 100;
    private static final int MAX_SERVICOS = 50;

    private static Peca[] pecas = new Peca[MAX_PECAS];
    private static Servico[] servicos = new Servico[MAX_SERVICOS];

    private static int totalPecas = 0;      
    private static int totalServicos = 0;   

    private static int proximoCodigoPeca = 1;
    private static int proximoCodigoServico = 1;
    private static int proximoCodigoCliente = 1;
    private static int proximoCodigoPedido = 1;
    private static int proximoCodigoOS = 1;

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static List<OrdemServico> ordensServico = new ArrayList<>();
    private static FilaAtendimento filaAtendimento = new FilaAtendimento();

    // ==================== BUSCA LINEAR ====================

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

    public static Cliente buscarClientePorCodigo(int codigo) {
        for (Cliente c : clientes) {
            if (c.getCodigo() == codigo) return c;
        }
        return null;
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

    public static Cliente cadastrarCliente(String nome, String telefone, String email) {
        Cliente c = new Cliente(proximoCodigoCliente++, nome, telefone, email);
        clientes.add(c);
        return c;
    }

    public static Pedido criarPedido() {
        Pedido p = new Pedido(proximoCodigoPedido++);
        pedidos.add(p);
        return p;
    }

    public static OrdemServico criarOrdemServico(Cliente cliente, Carro carro) {
        OrdemServico os = new OrdemServico(proximoCodigoOS++, cliente, carro);
        ordensServico.add(os);
        return os;
    }

    // ==================== READ ====================

    public static void listarPecas() {
        System.out.println("\n===== LISTA DE PEÇAS (" + totalPecas + "/" + MAX_PECAS + ") =====");
        if (totalPecas == 0) {
            System.out.println("Nenhuma peça cadastrada.");
            return;
        }
        for (int i = 0; i < totalPecas; i++) {
            Peca p = pecas[i];
            System.out.printf("Código: %d | Nome: %s | Fabricante: %s | Custo: R$ %.2f | Venda: R$ %.2f | Estoque: %d%n",
                    p.codigo, p.nomePeca, p.fabricante, p.precoCusto, p.precoVenda, p.quantidadeEstoque);
        }
    }

    public static void listarServicos() {
        System.out.println("\n===== LISTA DE SERVIÇOS (" + totalServicos + "/" + MAX_SERVICOS + ") =====");
        if (totalServicos == 0) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        for (int i = 0; i < totalServicos; i++) {
            Servico s = servicos[i];
            System.out.printf("Código: %d | Nome: %s | Descrição: %s | Tempo: %d min | Mão de obra: R$ %.2f%n",
                    s.codigo, s.nomeServico, s.descricao, s.tempoEstimadoMinutos, s.valorMaoObra);
        }
    }

    // ==================== UPDATE ====================

    public static boolean atualizarPeca(int codigo, String nomePeca, String fabricante, double precoCusto, double precoVenda, int quantidadeEstoque) {
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

    public static boolean atualizarServico(int codigo, String nomeServico, String descricao, int tempoEstimadoMinutos, double valorMaoObra) {
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

    // ==================== RELATÓRIOS ORDENADOS (OAT 2) ====================

    public static void exibirRelatorioOrdenadoPorNome() {
        System.out.println("\n######################################################################");
        System.out.println("     RELATÓRIO DE CATÁLOGO: PEÇAS E SERVIÇOS (ORDEM ALFABÉTICA)       ");
        System.out.println("######################################################################");

        Peca[] pecasOrdenadas = new Peca[totalPecas];
        System.arraycopy(pecas, 0, pecasOrdenadas, 0, totalPecas);
        Ordenador.ordenarPecas(pecasOrdenadas, totalPecas, ChaveOrdenacao.NOME);

        System.out.println("\n--- PEÇAS ORDENADAS POR NOME ---");
        for (int i = 0; i < totalPecas; i++) {
            Peca p = pecasOrdenadas[i];
            System.out.printf("[Cód: %02d] %-30s | Fab: %-15s | Venda: R$ %8.2f | Estoque: %d%n",
                    p.codigo, p.nomePeca, p.fabricante, p.precoVenda, p.quantidadeEstoque);
        }

        Servico[] servicosOrdenados = new Servico[totalServicos];
        System.arraycopy(servicos, 0, servicosOrdenados, 0, totalServicos);
        Ordenador.ordenarServicos(servicosOrdenados, totalServicos, ChaveOrdenacao.NOME);

        System.out.println("\n--- SERVIÇOS ORDENADOS POR NOME ---");
        for (int i = 0; i < totalServicos; i++) {
            Servico s = servicosOrdenados[i];
            System.out.printf("[Cód: %02d] %-30s | Tempo: %3d min | Mão de Obra: R$ %8.2f%n",
                    s.codigo, s.nomeServico, s.tempoEstimadoMinutos, s.valorMaoObra);
        }
        System.out.println("######################################################################\n");
    }

    // ==================== MÉTODOS AUXILIARES DE I/O E TESTES ====================

    public static void adicionarPecaDireta(Peca p) {
        if (totalPecas < MAX_PECAS) {
            pecas[totalPecas++] = p;
            if (p.codigo >= proximoCodigoPeca) proximoCodigoPeca = p.codigo + 1;
        }
    }

    public static void adicionarServicoDireto(Servico s) {
        if (totalServicos < MAX_SERVICOS) {
            servicos[totalServicos++] = s;
            if (s.codigo >= proximoCodigoServico) proximoCodigoServico = s.codigo + 1;
        }
    }

    public static void adicionarClienteDireto(Cliente c) {
        clientes.add(c);
        if (c.getCodigo() >= proximoCodigoCliente) proximoCodigoCliente = c.getCodigo() + 1;
    }

    public static Peca[] getPecas() { return pecas; }
    public static Servico[] getServicos() { return servicos; }
    public static int getTotalPecas() { return totalPecas; }
    public static int getTotalServicos() { return totalServicos; }
    public static FilaAtendimento getFilaAtendimento() { return filaAtendimento; }
    public static List<Cliente> getClientes() { return clientes; }
    public static List<Pedido> getPedidos() { return pedidos; }
    public static List<OrdemServico> getOrdensServico() { return ordensServico; }
}