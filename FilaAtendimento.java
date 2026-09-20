public class FilaAtendimento {
    private NoFila inicio;
    private NoFila fim;
    private int tamanho;

    public FilaAtendimento() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    // Política FIFO: Inserção sempre no fim da fila
    public void enfileirar(Servico servico) {
        NoFila novo = new NoFila(servico);
        if (estaVazia()) {
            this.inicio = novo;
            this.fim = novo;
        } else {
            this.fim.proximo = novo;
            this.fim = novo;
        }
        tamanho++;
    }

    // Política FIFO: Remoção sempre no início da fila
    public Servico desenfileirar() {
        if (estaVazia()) {
            System.out.println("[FILA VAZIA] Não há serviços pendentes de execução.");
            return null;
        }
        Servico servicoAtendido = this.inicio.dado;
        this.inicio = this.inicio.proximo;
        if (this.inicio == null) {
            this.fim = null;
        }
        tamanho--;
        return servicoAtendido;
    }

    public Servico espiar() {
        if (estaVazia()) return null;
        return this.inicio.dado;
    }

    public boolean estaVazia() {
        return this.inicio == null;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void imprimirFila() {
        System.out.println("\n========== FILA DE ATENDIMENTO DE SERVIÇOS (FIFO) ==========");
        if (estaVazia()) {
            System.out.println("Fila vazia. Nenhum serviço aguardando execução.");
            return;
        }
        NoFila atual = inicio;
        int posicao = 1;
        while (atual != null) {
            System.out.printf("%dº da Fila -> [Cód: %d] %s (Tempo: %d min | R$ %.2f)%n",
                    posicao++, atual.dado.codigo, atual.dado.nomeServico,
                    atual.dado.tempoEstimadoMinutos, atual.dado.valorMaoObra);
            atual = atual.proximo;
        }
        System.out.println("============================================================\n");
    }
}