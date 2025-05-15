public class App {
    public static void main(String[] args) {
        // Ajuste os caminhos conforme sua estrutura de pastas
        String entradaTexto = "src/texto/entradaTexto.txt";
        String saidaCripto = "src/texto/saidaCripto.txt";
        String saidaDecripto = "src/texto/saidaDecripto.txt";

        Mecanismo mec = new Mecanismo();
        mec.executarCripto(entradaTexto, saidaCripto);
        mec.executarDecripto(saidaCripto, saidaDecripto);
    }
}