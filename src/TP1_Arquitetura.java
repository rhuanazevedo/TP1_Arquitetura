import java.io.File;

public class TP1_Arquitetura {

    public static void main(String[] args) {

        //é necessário modificar as probabilidades e avaliar o desempenho
        Canal canal = new Canal(0.0);

        Transmissor transm = new Transmissor(new File("src/database/Moby-Dick-Cap1.txt"), canal, Estrategia.CRC);
        //é necessário modificar a estratégia e avaliar o desempenho
        Receptor receber = new Receptor(canal, Estrategia.CRC);

        canal.conectaTransmissor(transm);
        canal.conectaReceptor(receber);

        //mensurando o tempo de execução
        long tempoI = System.currentTimeMillis();
        transm.enviaDado();
        receber.gravaMensArquivo();
        long tempoF = System.currentTimeMillis();

        System.out.println("Tempo total: " + (tempoF - tempoI));
    }
}
