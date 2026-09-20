public class Servico {
    public int codigo;
    public String nomeServico;
    public String descricao;
    public int tempoEstimadoMinutos;
    public double valorMaoObra;

    public Servico() {}

    public Servico(int codigo, String nomeServico, String descricao, int tempoEstimadoMinutos, double valorMaoObra) {
        this.codigo = codigo;
        this.nomeServico = nomeServico;
        this.descricao = descricao;
        this.tempoEstimadoMinutos = tempoEstimadoMinutos;
        this.valorMaoObra = valorMaoObra;
    }

    public String toCSV() {
        return codigo + ";" + nomeServico + ";" + descricao + ";" + tempoEstimadoMinutos + ";" + valorMaoObra;
    }

    public static Servico fromCSV(String linha) {
        String[] dados = linha.split(";");
        return new Servico(
            Integer.parseInt(dados[0]),
            dados[1],
            dados[2],
            Integer.parseInt(dados[3]),
            Double.parseDouble(dados[4])
        );
    }
}