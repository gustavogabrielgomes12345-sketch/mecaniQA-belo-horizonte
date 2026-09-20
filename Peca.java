public class Peca {
    public int codigo;
    public String nomePeca;
    public String fabricante;
    public double precoCusto;
    public double precoVenda;
    public int quantidadeEstoque;

    public Peca() {}

    public Peca(int codigo, String nomePeca, String fabricante, double precoCusto, double precoVenda, int quantidadeEstoque) {
        this.codigo = codigo;
        this.nomePeca = nomePeca;
        this.fabricante = fabricante;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String toCSV() {
        return codigo + ";" + nomePeca + ";" + fabricante + ";" + precoCusto + ";" + precoVenda + ";" + quantidadeEstoque;
    }

    public static Peca fromCSV(String linha) {
        String[] dados = linha.split(";");
        return new Peca(
            Integer.parseInt(dados[0]),
            dados[1],
            dados[2],
            Double.parseDouble(dados[3]),
            Double.parseDouble(dados[4]),
            Integer.parseInt(dados[5])
        );
    }
}