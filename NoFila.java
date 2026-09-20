public class NoFila {
    public Servico dado;
    public NoFila proximo;

    public NoFila(Servico dado) {
        this.dado = dado;
        this.proximo = null;
    }
}