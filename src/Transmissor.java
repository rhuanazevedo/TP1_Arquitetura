import java.io.*;

public class Transmissor {
    private String mensagem;
    private Canal canal;
    private File arquivo;
    private Estrategia tecnica;
    //O Polinômio utilizado é o 10011
    int polinomioLength = Canal.polinomio.length;

    public Transmissor(File arq, Canal canal, Estrategia tecnica) {
        this.arquivo = arq;
        this.canal = canal;
        this.tecnica = tecnica;
        carregarMensagemArquivo();
    }

    private void carregarMensagemArquivo() {
        if (!arquivo.exists()) {
            System.out.println("O arquivo não existe");
            return;
        }
        try {
            this.mensagem = new String(java.nio.file.Files.readAllBytes(arquivo.toPath()));
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + e.getMessage());
        }
    }

    //Convertendo um símbolo para "vetor" de boolean (bits)
    private boolean[] streamCaracter(char simbolo){
        //Cada símbolo da tabela ASCII é representado com 8 bits
        boolean bits[] = new boolean[8];
        //Convertendo um char para int (encontramos o valor do mesmo na tabela ASCII)
        int valorSimbolo = (int) simbolo;

        //Caracteres inválidos para UTF-8
        if(valorSimbolo > 255){
            valorSimbolo = 0; //quebra de linha
        }
        int indice = 7;

        //Convertendo cada "bits" do valor da tabela ASCII
        while(valorSimbolo >= 2){
            int resto = valorSimbolo % 2;
            valorSimbolo /= 2;
            bits[indice] = (resto == 1);
            indice--;
        }
        bits[indice] = (valorSimbolo == 1);

        return bits;
    }

    private boolean[] dadoBitsCRC(boolean[] bits) {
        //O Polinômio utilizado é 10011
        int dadoLength = bits.length;

        //Novo vetor para armazenar os bits a serem transmitidos + os bits CRC
        boolean[] dadoCRC = new boolean[dadoLength + polinomioLength - 1];

        //Fundir os 8 bits passados como parâmetro com a quantidade de bits referente ao grau do polinomio
        System.arraycopy(bits, 0, dadoCRC, 0, dadoLength);

        boolean[] resto = dadoCRC.clone();
        for (int i = 0; i < dadoLength; i++) {
            //1 = true | 0 = false
            if (resto[i]) {
                for (int j = 0; j < polinomioLength; j++) {
                    resto[i + j] ^= Canal.polinomio[j];
                }
            }
        }
        //Cópia dos bits CRC para dadoCRC[]
        System.arraycopy(resto, dadoLength, dadoCRC, dadoLength, polinomioLength - 1);

        return dadoCRC;
    }

    private boolean[] dadoBitsHamming(boolean[] bits) {
        int divisorBits = bits.length / 4;
        if (bits.length % 4 != 0) {
            divisorBits = divisorBits + 1;
        }
        boolean[] dadoHamming = new boolean[divisorBits * 7];

        for (int cont = 0; cont < divisorBits; cont++) {

            boolean b1 = getBit(bits, cont * 4);
            boolean b2 = getBit(bits, cont * 4 + 1);
            boolean b3 = getBit(bits, cont * 4 + 2);
            boolean b4 = getBit(bits, cont * 4 + 3);

            boolean h1 = b1 ^ b2 ^ b4;
            boolean h2 = b1 ^ b3 ^ b4;
            boolean h3 = b2 ^ b3 ^ b4;

            int base = cont * 7;
            dadoHamming[base] = h1;
            dadoHamming[base + 1] = h2;
            dadoHamming[base + 2] = b1;
            dadoHamming[base + 3] = h3;
            dadoHamming[base + 4] = b2;
            dadoHamming[base + 5] = b3;
            dadoHamming[base + 6] = b4;
        }

        return dadoHamming;

    }

    private boolean getBit(boolean[] bits, int index) {
        if (index < bits.length) {
            return bits[index];
        } else {
            return false;
        }
    }

    public void enviaDado() {
        System.out.println("Enviando dado...");
        for (int i = 0; i < this.mensagem.length(); i++) {
            do {
                //Converter o caractere para sequência de 8 bits
                boolean[] bits = streamCaracter(this.mensagem.charAt(i));

                if (this.tecnica == Estrategia.CRC) {
                    //Calcular bits CRC
                    bits = dadoBitsCRC(bits);
                } else if (this.tecnica == Estrategia.HAMMING) {
                    //Calcular bits Hamming
                    bits = dadoBitsHamming(bits);
                }

                //Enviar dado para o Receptor
                this.canal.enviarDado(bits);

            } while (this.canal.recebeFeedback() == false);
        }
        System.out.println("Dado enviado com sucesso!.");
    }
}
