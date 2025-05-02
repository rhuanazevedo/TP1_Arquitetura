import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Transmissor {
    private String mensagem;
    private Canal canal;
    private File arquivo;
    private Estrategia tecnica;

    int polinomioLength = Canal.polinomio.length;
    int dadoLength;

    public Transmissor(String mensagem, Canal canal, Estrategia tecnica) {
        this.mensagem = mensagem;
        this.canal = canal;
        this.tecnica = tecnica;
    }

    public Transmissor(File arq, Canal canal, Estrategia tecnica) {
        this.arquivo = arq;
        this.canal = canal;
        this.tecnica = tecnica;
        carregarMensagemArquivo(); //src/livro/Moby-Dick-Cap1.txt
    }

    private void carregarMensagemArquivo() {

        if (!arquivo.exists()) {
            System.out.println("O arquivo não existe");
            return;
        }
        try {
            this.mensagem = new String(java.nio.file.Files.readAllBytes(arquivo.toPath()));

        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquibo: " + e.getMessage());
        }
    }


    //convertendo um símbolo para "vetor" de boolean (bits)
    private boolean[] streamCaracter(char simbolo) {

        //cada símbolo da tabela ASCII é representado com 8 bits
        boolean bits[] = new boolean[8];

        //convertendo um char para int (encontramos o valor do mesmo na tabela ASCII)
        int valorSimbolo = (int) simbolo;

        //caracteres inválidos para UTF-8
        if (valorSimbolo > 255) {
            valorSimbolo = 0; //quebra de linha
        }
        int indice = 7;

        //convertendo cada "bits" do valor da tabela ASCII
        while (valorSimbolo >= 2) {
            int resto = valorSimbolo % 2;
            valorSimbolo /= 2;
            bits[indice] = (resto == 1);
            indice--;
        }
        bits[indice] = (valorSimbolo == 1);

        return bits;
    }

    private boolean[] dadoBitsCRC(boolean bits[]) {
        //Polinomio 100000111
        dadoLength = bits.length;

        boolean[] dadoCRC = new boolean[dadoLength + polinomioLength - 1];
        //Fundir o "bits[]" com os bits 0 referente ao grau do polinomio
        System.arraycopy(bits, 0, dadoCRC, 0, dadoLength);

        boolean[] temp = dadoCRC.clone();

        for (int i = 0; i < dadoLength; i++) {
            if (temp[i]) {
                for (int j = 0; j < polinomioLength; j++) {
                    temp[i + j] ^= Canal.polinomio[j];
                }
            }
        }

        System.arraycopy(temp, dadoLength, dadoCRC, dadoLength, polinomioLength - 1);

        return dadoCRC;
    }

    private boolean[] dadoBitsHamming(boolean bits[]) {

        int divisorBits = bits.length / 4;
        if (bits.length % 4 != 0) {
            divisorBits = divisorBits + 1;
        }
        boolean[] resultado = new boolean[divisorBits * 7];

        for (int cont = 0; cont < divisorBits; cont++) {

            boolean b1 = getBit(bits, cont * 4);
            boolean b2 = getBit(bits, cont * 4 + 1);
            boolean b3 = getBit(bits, cont * 4 + 2);
            boolean b4 = getBit(bits, cont * 4 + 3);

            boolean h1 = b1 ^ b2 ^ b4;
            boolean h2 = b1 ^ b3 ^ b4;
            boolean h3 = b2 ^ b3 ^ b4;

            int base = cont * 7;
            resultado[base] = h1;
            resultado[base + 1] = h2;
            resultado[base + 2] = h3;
            resultado[base + 3] = b1;
            resultado[base + 4] = b2;
            resultado[base + 5] = b3;
            resultado[base + 6] = b4;
        }

        return resultado;

    }

    private boolean getBit(boolean[] bits, int index) {
        if (index < bits.length) {
            return bits[index];
        } else {
            return false;
        }
    }

    public void enviaDado() {
        for (int i = 0; i < this.mensagem.length(); i++) {
            do {
                boolean[] bits = streamCaracter(this.mensagem.charAt(i));

                if (this.tecnica == Estrategia.CRC) {
                    bits = dadoBitsCRC(bits);
                } else if (this.tecnica == Estrategia.HAMMING) {
                    bits = dadoBitsHamming(bits);
                }

                this.canal.enviarDado(bits);

            } while (this.canal.recebeFeedback() == false);
        }
    }
}
