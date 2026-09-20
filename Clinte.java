import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private int codigo;
    private String nome;
    private String telefone;
    private String email;
    private List<Carro> carros; 

    public Cliente(int codigo, String nome, String telefone, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.carros = new ArrayList<>();
    }

    public void adicionarCarro(Carro carro) {
        if (carro != null) {
            this.carros.add(carro);
        }
    }

    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public List<Carro> getCarros() { return carros; }

    public String toCSV() {
        return codigo + ";" + nome + ";" + telefone + ";" + email;
    }

    public static Cliente fromCSV(String linha) {
        String[] dados = linha.split(";");
        return new Cliente(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3]);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | Tel: %s | Email: %s | Veículos vinculados: %d",
                codigo, nome, telefone, email, carros.size());
    }
}