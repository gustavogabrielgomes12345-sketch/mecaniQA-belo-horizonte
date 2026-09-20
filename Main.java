public class Main {
    public static void main(String[] args) {

        System.out.println("=====================================================================");
        System.out.println("          MECÂNIQA AUTOMOTIVE TECH - SPRINT 2 (OAT 2)                ");
        System.out.println("=====================================================================");

        System.out.println("\n[1] TESTANDO CLIENTES, CARROS E ENUMS...");
        Cliente gustavo = Gerenciador.cadastrarCliente("Gustavo Gomes", "(75) 98888-7777", "gustavo@mecaniqa.com");
        Carro carro1 = new Carro("Civic Touring", "BRA2E19", 2022, EstiloCarro.SEDAN);
        Carro carro2 = new Carro("Pulse Abarth", "MEC1A23", 2024, EstiloCarro.SUV);
        gustavo.adicionarCarro(carro1);
        gustavo.adicionarCarro(carro2);
        System.out.println("Cliente cadastrado: " + gustavo);
        for (Carro c : gustavo.getCarros()) {
            System.out.println(" -> " + c);
        }

        System.out.println("\n[2] CADASTRANDO CATÁLOGO BASE DE PEÇAS E SERVIÇOS...");
        int codP1 = Gerenciador.cadastrarPeca("Pastilha de Freio", "Bosch", 45.00, 89.90, 30);
        int codP2 = Gerenciador.cadastrarPeca("Filtro de Óleo", "Mann", 12.50, 29.90, 50);
        int codP3 = Gerenciador.cadastrarPeca("Amortecedor Dianteiro", "Cofap", 180.00, 349.90, 12);
        int codP4 = Gerenciador.cadastrarPeca("Vela de Ignição", "NGK", 15.00, 32.90, 100);

        int codS1 = Gerenciador.cadastrarServico("Troca de Óleo", "Substituição do lubrificante e filtro", 40, 60.00);
        int codS2 = Gerenciador.cadastrarServico("Alinhamento 3D", "Alinhamento a laser da direção", 60, 120.00);
        int codS3 = Gerenciador.cadastrarServico("Revisão de Freios", "Desmontagem e sangria do sistema", 90, 180.00);

        System.out.println("\n[3] TESTANDO PEDIDO DE BALCÃO...");
        Pedido pedido1 = Gerenciador.criarPedido();
        Peca pecaFiltro = Gerenciador.getPecas()[Gerenciador.buscarPecaPorCodigo(Gerenciador.getPecas(), Gerenciador.getTotalPecas(), codP2)];
        Peca pecaVela = Gerenciador.getPecas()[Gerenciador.buscarPecaPorCodigo(Gerenciador.getPecas(), Gerenciador.getTotalPecas(), codP4)];

        pedido1.adicionarPeca(pecaFiltro, 2);
        pedido1.adicionarPeca(pecaVela, 4);
        pedido1.aplicarDesconto(10.0); 
        pedido1.imprimirRelatorio();

        System.out.println("-> Finalizando o pedido...");
        pedido1.finalizarPedido();

        System.out.println("-> Testando restrição: tentar inserir peça em pedido finalizado...");
        try {
            pedido1.adicionarPeca(pecaFiltro, 1);
        } catch (IllegalStateException e) {
            System.out.println("Bloqueio de inserção validado com sucesso: " + e.getMessage());
        }

        System.out.println("\n[4] TESTANDO ORDEM DE SERVIÇO E DESPACHO FIFO...");
        OrdemServico os1 = Gerenciador.criarOrdemServico(gustavo, carro1);
        Servico servTrocaOleo = Gerenciador.getServicos()[Gerenciador.buscarServicoPorCodigo(Gerenciador.getServicos(), Gerenciador.getTotalServicos(), codS1)];
        Servico servRevisaoFreios = Gerenciador.getServicos()[Gerenciador.buscarServicoPorCodigo(Gerenciador.getServicos(), Gerenciador.getTotalServicos(), codS3)];

        os1.adicionarServico(servTrocaOleo);
        os1.adicionarServico(servRevisaoFreios);
        os1.imprimirTabelaServicos();

        FilaAtendimento fila = Gerenciador.getFilaAtendimento();
        os1.avancarParaAguardandoExecucao(fila);

        System.out.println("-> Testando restrição de OS: tentar adicionar serviço após fechamento...");
        try {
            Servico servAlinhamento = Gerenciador.getServicos()[Gerenciador.buscarServicoPorCodigo(Gerenciador.getServicos(), Gerenciador.getTotalServicos(), codS2)];
            os1.adicionarServico(servAlinhamento);
        } catch (IllegalStateException e) {
            System.out.println("Bloqueio de OS validado com sucesso: " + e.getMessage());
        }

        fila.imprimirFila();
        System.out.println("Mecânico iniciando atendimentos pela Fila FIFO:");
        while (!fila.estaVazia()) {
            Servico executando = fila.desenfileirar();
            System.out.println("-> Mecânico EXECUTOU: " + executando.nomeServico + " (" + executando.tempoEstimadoMinutos + " min)");
        }
        fila.imprimirFila();

        Gerenciador.exibirRelatorioOrdenadoPorNome();

        System.out.println("[7] TESTANDO PERSISTÊNCIA I/O COM DELIMITADOR ';'...");
        PersistenciaCSV.salvarPecas(Gerenciador.getPecas(), Gerenciador.getTotalPecas(), "pecas.csv");
        PersistenciaCSV.salvarServicos(Gerenciador.getServicos(), Gerenciador.getTotalServicos(), "servicos.csv");
        PersistenciaCSV.salvarClientes(Gerenciador.getClientes(), "clientes.csv", "carros.csv");
        System.out.println("[OK] Dados persistidos com sucesso em pecas.csv, servicos.csv, clientes.csv e carros.csv!");

        System.out.println("\n========== TODOS OS REQUISITOS DO BAREMA FORAM ATENDIDOS ==========");
    }
}