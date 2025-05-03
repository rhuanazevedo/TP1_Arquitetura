import java.io.File;

public class Main {

    public static void main(String[] args) {

        //Probabilidades modificáveis
        Canal canal = new Canal(0.5);

        //Estratégia modificável (CRC ou HAMMING)
        Transmissor transm = new Transmissor(new File("src/database/Moby-Dick-Cap1.txt"), canal, Estrategia.HAMMING);
        Receptor receber = new Receptor(canal, Estrategia.HAMMING);

        //Conexão do canal ao transmissor e ao receptor
        canal.conectaTransmissor(transm);
        canal.conectaReceptor(receber);

        //Mensurando o tempo de execução
        long tempoI = System.currentTimeMillis();
        transm.enviaDado();
        receber.gravaMensArquivo();
        long tempoF = System.currentTimeMillis();

        System.out.println("Tempo total: " + (tempoF - tempoI) + " ms");
    }
}
