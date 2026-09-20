import java.io.*;
import java.util.List;

public class PersistenciaCSV {

    public static void salvarPecas(Peca[] pecas, int total, String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int i = 0; i < total; i++) {
                bw.write(pecas[i].toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO SALVAR PEÇAS] " + e.getMessage());
        }
    }

    public static void carregarPecas(String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                Peca p = Peca.fromCSV(linha);
                Gerenciador.adicionarPecaDireta(p);
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO CARREGAR PEÇAS] " + e.getMessage());
        }
    }

    public static void salvarServicos(Servico[] servicos, int total, String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (int i = 0; i < total; i++) {
                bw.write(servicos[i].toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO SALVAR SERVIÇOS] " + e.getMessage());
        }
    }

    public static void carregarServicos(String arquivo) {
        File f = new File(arquivo);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                Servico s = Servico.fromCSV(linha);
                Gerenciador.adicionarServicoDireto(s);
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO CARREGAR SERVIÇOS] " + e.getMessage());
        }
    }

    public static void salvarClientes(List<Cliente> clientes, String arquivoClientes, String arquivoCarros) {
        try (BufferedWriter bwClie = new BufferedWriter(new FileWriter(arquivoClientes));
             BufferedWriter bwCar = new BufferedWriter(new FileWriter(arquivoCarros))) {
            for (Cliente c : clientes) {
                bwClie.write(c.toCSV());
                bwClie.newLine();
                for (Carro car : c.getCarros()) {
                    bwCar.write(c.getCodigo() + ";" + car.toCSV());
                    bwCar.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO SALVAR CLIENTES/CARROS] " + e.getMessage());
        }
    }

    public static void carregarClientesECarros(String arquivoClientes, String arquivoCarros) {
        File fc = new File(arquivoClientes);
        if (!fc.exists()) return;
        try (BufferedReader brClie = new BufferedReader(new FileReader(fc))) {
            String linha;
            while ((linha = brClie.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                Cliente c = Cliente.fromCSV(linha);
                Gerenciador.adicionarClienteDireto(c);
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO CARREGAR CLIENTES] " + e.getMessage());
        }

        File fcar = new File(arquivoCarros);
        if (!fcar.exists()) return;
        try (BufferedReader brCar = new BufferedReader(new FileReader(fcar))) {
            String linha;
            while ((linha = brCar.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] partes = linha.split(";");
                int codCliente = Integer.parseInt(partes[0]);
                Carro carro = new Carro(partes[1], partes[2], Integer.parseInt(partes[3]), EstiloCarro.valueOf(partes[4]));
                Cliente c = Gerenciador.buscarClientePorCodigo(codCliente);
                if (c != null) {
                    c.adicionarCarro(carro);
                }
            }
        } catch (IOException e) {
            System.err.println("[ERRO AO CARREGAR CARROS] " + e.getMessage());
        }
    }
}