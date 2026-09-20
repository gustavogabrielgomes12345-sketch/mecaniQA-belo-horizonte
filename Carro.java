public class Carro {
    private String modelo;
    private String placa;
    private int ano;
    private EstiloCarro estilo;

    public Carro(String modelo, String placa, int ano, EstiloCarro estilo) {
        this.modelo = modelo;
        this.placa = placa;
        this.ano = ano;
        this.estilo = estilo;
    }

    public String getModelo() { return modelo; }
    public String getPlaca() { return placa; }
    public int getAno() { return ano; }
    public EstiloCarro getEstilo() { return estilo; }

    public String toCSV() {
        return modelo + ";" + placa + ";" + ano + ";" + estilo.name();
    }

    public static Carro fromCSV(String linha) {
        String[] dados = linha.split(";");
        return new Carro(dados[0], dados[1], Integer.parseInt(dados[2]), EstiloCarro.valueOf(dados[3]));
    }

    @Override
    public String toString() {
        return String.format("%s (Placa: %s | Ano: %d | Estilo: %s)", modelo, placa, ano, estilo);
    }
}