public class ItemPedido {
    private Peca peca;
    private int quantidade;

    public ItemPedido(Peca peca, int quantidade) {
        this.peca = peca;
        this.quantidade = quantidade;
    }

    public Peca getPeca() { return peca; }
    public int getQuantidade() { return quantidade; }

    public double getSubtotal() {
        return peca.precoVenda * quantidade;
    }

    public String toCSV(int codigoPedido) {
        return codigoPedido + ";" + peca.codigo + ";" + quantidade;
    }
}