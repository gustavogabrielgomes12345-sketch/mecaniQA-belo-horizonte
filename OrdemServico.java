import java.util.ArrayList;
import java.util.List;

public class OrdemServico {
    private int codigo;
    private Cliente cliente;
    private Carro carro;
    private List<Servico> servicos;
    private StatusOS status;

    public OrdemServico(int codigo, Cliente cliente, Carro carro) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.carro = carro;
        this.servicos = new ArrayList<>();
        this.status = StatusOS.EM_ABERTO;
    }

    public void adicionarServico(Servico servico) {
        if (status != StatusOS.EM_ABERTO) {
            throw new IllegalStateException("[ERRO] OS #" + codigo + " não está EM_ABERTO (Status atual: " + status + "). Alterações bloqueadas.");
        }
        if (servico != null) {
            this.servicos.add(servico);
            System.out.println("[OK] Serviço '" + servico.nomeServico + "' adicionado à OS #" + codigo);
        }
    }

    public void removerServico(int codigoServico) {
        if (status != StatusOS.EM_ABERTO) {
            throw new IllegalStateException("[ERRO] Remoção bloqueada: OS #" + codigo + " fechada.");
        }
        servicos.removeIf(s -> s.codigo == codigoServico);
    }

    public int quantidadeServicos() {
        return servicos.size();
    }

    public double valorTotal() {
        double total = 0.0;
        for (Servico s : servicos) {
            total += s.valorMaoObra;
        }
        return total;
    }

    public void imprimirTabelaServicos() {
        System.out.println("\n================ ORDEM DE SERVIÇO #" + codigo + " ================");
        System.out.println("Cliente: " + (cliente != null ? cliente.getNome() : "Não informado"));
        System.out.println("Veículo: " + (carro != null ? carro.toString() : "Não informado"));
        System.out.println("Status Atual: " + status);
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-6s | %-25s | %-12s | %-12s%n", "CÓD", "SERVIÇO", "TEMPO", "VALOR M.O.");
        System.out.println("-----------------------------------------------------------------");
        for (Servico s : servicos) {
            System.out.printf("%-6d | %-25s | %-4d min     | R$ %-9.2f%n",
                    s.codigo, s.nomeServico, s.tempoEstimadoMinutos, s.valorMaoObra);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("Quantidade de Serviços: %d%n", quantidadeServicos());
        System.out.printf("Valor Total da OS:      R$ %.2f%n", valorTotal());
        System.out.println("=================================================================\n");
    }

    public void avancarParaAguardandoExecucao(FilaAtendimento fila) {
        if (this.status != StatusOS.EM_ABERTO) {
            System.out.println("[AVISO] Transição inválida para AGUARDANDO_EXECUCAO.");
            return;
        }
        this.status = StatusOS.AGUARDANDO_EXECUCAO;
        for (Servico s : servicos) {
            fila.enfileirar(s);
        }
        System.out.println("[OK] OS #" + codigo + " paga/confirmada. Serviços despachados para a Fila de Atendimento.");
    }

    public void setStatus(StatusOS status) { this.status = status; }
    public int getCodigo() { return codigo; }
    public StatusOS getStatus() { return status; }
    public Cliente getCliente() { return cliente; }
    public Carro getCarro() { return carro; }
    public List<Servico> getServicos() { return servicos; }

    public String toCSV() {
        int codCliente = (cliente != null) ? cliente.getCodigo() : 0;
        String placaCarro = (carro != null) ? carro.getPlaca() : "";
        return codigo + ";" + codCliente + ";" + placaCarro + ";" + status.name();
    }
}