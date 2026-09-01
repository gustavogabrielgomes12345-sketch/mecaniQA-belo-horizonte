# Relatório de Revisão de Código - Estrutura de Dados

## 1. Arquivos Revisados
- `Peca.java`
- `Servico.java`
- `Gerenciador.java`

## 2. Grade de Avaliação

| Critério | Peso | Porcentagem Atingida | Nota Obtida | Justificativa |
| :--- | :--- | :--- | :--- | :--- |
| Nomes significativos de Classes, atributos e métodos (Aspectos do Código) | 0,5 | 100% - CUMPRIU | 0,5 | Nomes altamente descritivos que demonstram bem a intenção de uso do código. |
| Nomenclatura nas convenções Java (Aspectos do Código) | 0,5 | 100% - CUMPRIU | 0,5 | Padrões de PascalCase (Classes) e CamelCase (variáveis/métodos) seguidos adequadamente. |
| Estrutura Base (TADs e Gerenciador) | 1,0 | 100% - CUMPRIU | 1,0 | Classes `Peca` e `Servico` implementadas puramente como estruturas de dados, livres de regras de negócio. |
| Memória (TADs e Gerenciador) | 1,0 | 100% - CUMPRIU | 1,0 | Arrays dimensionados e inicializados de modo global para a aplicação gerenciar o fluxo corretamente. |
| Isolamento (TADs e Gerenciador) | 1,0 | 100% - CUMPRIU | 1,0 | Todas as rotinas lógicas se encontram centralizadas com os modificadores `static` no Gerenciador (com breve ajuste de visibilidade sugerido e aplicado). |
| Algoritmo de Busca (Listas Estáticas) | 1,0 | 100% - CUMPRIU | 1,0 | Lógica de busca linear muito bem estruturada retornando o índice exato ou -1 (Not Found). |
| Reuso na Atualização (Listas Estáticas) | 1,0 | 100% - CUMPRIU | 1,0 | Métodos de Update reaproveitaram as rotinas de busca, economizando código e centralizando comportamento. |
| Inserção de Peça (Listas Estáticas) | 1,0 | 100% - CUMPRIU | 1,0 | Inserção posicionada em O(1) pelo gerenciamento adequado do ponteiro `totalPecas`. |
| Remoção de Peça (Listas Estáticas) | 1,0 | 100% - CUMPRIU | 1,0 | Remoção física adequada através do shift-left dos elementos adjacentes. |
| Inserção de Serviço (Listas Estáticas) | 1,0 | 100% - CUMPRIU | 1,0 | Inserção posicionada em O(1) pelo gerenciamento adequado do ponteiro `totalServicos`. |
| Remoção de Serviço (Listas Estáticas) | 1,0 | 100% - CUMPRIU | 1,0 | Remoção física adequada através do shift-left, limpando a última posição e atualizando a variável controladora. |

## 3. Resultado Final
- **Nota Final**: 10,0 / 10,0
- **Considerações Gerais**: Excelente trabalho! A equipe demonstrou forte entendimento de como gerenciar listas através de arrays estáticos nativos na linguagem Java, que é o principal objetivo da restrição. Todas as regras estipuladas foram respeitadas — as classes de domínio (`Peca` e `Servico`) são apenas representações de dados (Structs), e o `Gerenciador` assumiu completamente o controle lógico usando a abordagem procedural através de escopo estático, sem uso de coleções. Foi feita apenas uma pequena ressalva para se lembrarem de colocar explicitamente o modificador `public` nos métodos do gerenciador (`atualizarPeca` possuía visibilidade de pacote), mas não comprometeu a avaliação e já foi ajustado no código original. Parabéns pelo resultado!
