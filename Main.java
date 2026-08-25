public class Main {
    public static void main(String[] args) {

        System.out.println("########## CREATE ##########");
        int codPeca1 = Gerenciador.cadastrarPeca("Pastilha de Freio", "Bosch", 45.00, 89.90, 30);
        int codPeca2 = Gerenciador.cadastrarPeca("Filtro de Óleo", "Mann Filter", 12.50, 29.90, 50);
        int codPeca3 = Gerenciador.cadastrarPeca("Amortecedor Dianteiro", "Cofap", 180.00, 349.90, 12);

        int codServico1 = Gerenciador.cadastrarServico("Troca de Óleo", "Troca de óleo e filtro do motor", 40, 60.00);
        int codServico2 = Gerenciador.cadastrarServico("Alinhamento e Balanceamento", "Alinhamento de direção e balanceamento das rodas", 60, 120.00);

        System.out.println("\n########## READ ##########");
        Gerenciador.listarPecas();
        Gerenciador.listarServicos();

        System.out.println("\n########## BUSCA LINEAR ##########");
        int indice = Gerenciador.buscarPecaPorCodigo(Gerenciador.getPecas(), Gerenciador.getTotalPecas(), codPeca2);
        System.out.println("Peça código " + codPeca2 + " encontrada no índice: " + indice);

        int indiceInexistente = Gerenciador.buscarServicoPorCodigo(Gerenciador.getServicos(), Gerenciador.getTotalServicos(), 999);
        System.out.println("Busca por código inexistente (999) retornou: " + indiceInexistente + " (esperado: -1)");

        System.out.println("\n########## UPDATE ##########");
        Gerenciador.atualizarPeca(codPeca1, "Pastilha de Freio Dianteira", "Bosch", 48.00, 94.90, 25);
        Gerenciador.atualizarServico(codServico1, "Troca de Óleo Sintético", "Troca de óleo sintético e filtro do motor", 45, 75.00);
        Gerenciador.listarPecas();
        Gerenciador.listarServicos();

        System.out.println("\n########## DELETE ##########");
        Gerenciador.removerPeca(codPeca2); 
        Gerenciador.listarPecas();

        Gerenciador.removerServico(codServico2);
        Gerenciador.listarServicos();

        System.out.println("\n########## VALIDAÇÃO PÓS-REMOÇÃO ##########");
    
        int indiceAposRemocao = Gerenciador.buscarPecaPorCodigo(Gerenciador.getPecas(), Gerenciador.getTotalPecas(), codPeca2);
        System.out.println("Busca pela peça removida (código " + codPeca2 + ") retornou: " + indiceAposRemocao + " (esperado: -1)");
        Gerenciador.atualizarPeca(codPeca2, "x", "x", 0, 0, 0); 
        Gerenciador.removerServico(codServico2);               

        System.out.println("\n########## NOVO CADASTRO APÓS REMOÇÃO ##########");

        int codPeca4 = Gerenciador.cadastrarPeca("Vela de Ignição", "NGK", 15.00, 32.90, 100);
        Gerenciador.listarPecas();
        System.out.println("Novo código gerado (deve ser sequencial, não reaproveitado): " + codPeca4);
    }
}
