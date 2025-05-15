import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Mecanismo {

    private int solicitarChave(String operacao) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("Digite a chave (deslocamento) para %s: ", operacao);
        while (!sc.hasNextInt()) {
            System.out.print("Valor inválido! Digite um número inteiro: ");
            sc.next();
        }
        int chave = sc.nextInt();
        sc.nextLine(); 
        return chave;
    }

    public void executarCripto(String arquivoTexto, String arquivoCripto) {
        int chave = solicitarChave("criptografar");
        CifraCesar cifra = new CifraCesar(chave);
        processarArquivo(arquivoTexto, arquivoCripto, cifra::criptografar, "criptografia");
    }

    public void executarDecripto(String entradaCripto, String saidaDecripto) {
        int chave = solicitarChave("descriptografar");
        CifraCesar cifra = new CifraCesar(chave);
        processarArquivo(entradaCripto, saidaDecripto, cifra::descriptografar, "decriptografia");
    }

    
    private void processarArquivo(String entrada, String saida,
                                  java.util.function.Function<String, String> transformacao,
                                  String nomeProcesso) {
        Path origem = Paths.get(entrada);
        Path destino = Paths.get(saida);
       
        try {
            if (destino.getParent() != null) {
                Files.createDirectories(destino.getParent());
            }
        } catch (IOException e) {
            System.err.println("Falha ao criar diretório de saída: " + e.getMessage());
        }

        try (BufferedReader br = Files.newBufferedReader(origem);
             BufferedWriter bw = Files.newBufferedWriter(destino)) {

            String linha;
            while ((linha = br.readLine()) != null) {
                bw.write(transformacao.apply(linha));
                bw.newLine();
            }
            System.out.printf("Arquivo %s salvo em %s%n", nomeProcesso, destino.toString());
        } catch (IOException e) {
            System.err.printf("Erro no processo de %s: %s%n", nomeProcesso, e.getMessage());
        }
    }
}


