import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int codigo;
    private List<ItemPedido> itens;
    private StatusPedido status;
    private double percentualDesconto;

    public Pedido(int codigo) {
        this.codigo = codigo;
        this.itens = new ArrayList<>();
        this.status = StatusPedido.ABERTO;
        this.percentualDesconto = 0.0;
    }

    public void adicionarPeca(Peca peca, int quantidade) {
        if (status == StatusPedido.FINALIZADO) {
            throw new IllegalStateException("[ERRO] Pedido " + codigo + " já está FINALIZADO. Inclusão de peças bloqueada.");
        }
        if (quantidade <= 0) {
            System.out.println("[ERRO] Quantidade inválida.");
            return;
        }
        if (peca.quantidadeEstoque < quantidade) {
            System.out.println("[ERRO] Estoque insuficiente para a peça: " + peca.nomePeca);
            return;
        }
        peca.quantidadeEstoque -= quantidade;
        this.itens.add(new ItemPedido(peca, quantidade));
        System.out.println("[OK] Adicionado ao Pedido " + codigo + ": " + quantidade + "x " + peca.nomePeca);
    }

    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ItemPedido item : itens) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public void aplicarDesconto(double percentual) {
        if (status == StatusPedido.FINALIZADO) {
            throw new IllegalStateException("[ERRO] Pedido finalizado não aceita alteração de desconto.");
        }
        if (percentual < 0 || percentual > 100) {
            System.out.println("[ERRO] Percentual de desconto inválido.");
            return;
        }
        this.percentualDesconto = percentual;
    }

    public double calcularTotal() {
        double subtotal = calcularSubtotal();
        return subtotal - (subtotal * (percentualDesconto / 100.0));
    }

    public void finalizarPedido() {
        this.status = StatusPedido.FINALIZADO;
        System.out.println("[OK] Pedido " + codigo + " FINALIZADO com sucesso. Edições bloqueadas.");
    }

    public void imprimirRelatorio() {
        System.out.println("\n================ RELATÓRIO DO PEDIDO #" + codigo + " ================");
        System.out.println("Status: " + status);
        System.out.printf("%-6s | %-25s | %-5s | %-12s | %-12s%n", "CÓD", "PEÇA", "QTD", "UNITÁRIO", "SUBTOTAL");
        System.out.println("----------------------------------------------------------------------");
        for (ItemPedido item : itens) {
            System.out.printf("%-6d | %-25s | %-5d | R$ %-9.2f | R$ %-9.2f%n",
                    item.getPeca().codigo,
                    item.getPeca().nomePeca,
                    item.getQuantidade(),
                    item.getPeca().precoVenda,
                    item.getSubtotal());
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("Subtotal:            R$ %.2f%n", calcularSubtotal());
        System.out.printf("Desconto aplicado:   %.1f%%%n", percentualDesconto);
        System.out.printf("TOTAL FINAL:         R$ %.2f%n", calcularTotal());
        System.out.println("======================================================================\n");
    }

    public int getCodigo() { return codigo; }
    public StatusPedido getStatus() { return status; }
    public List<ItemPedido> getItens() { return itens; }
    public double getPercentualDesconto() { return percentualDesconto; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public String toCSV() {
        return codigo + ";" + status.name() + ";" + percentualDesconto;
    }
}