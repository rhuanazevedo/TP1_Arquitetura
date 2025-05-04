import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Receptor {

    //mensagem recebida pelo transmissor
    private String mensagem;
    private final Estrategia tecnica;
    private final Canal canal;

    public Receptor(Canal canal, Estrategia tecnica) {
        //mensagem vazia no inicio da execução
        this.mensagem = "";
        this.tecnica = tecnica;
        this.canal = canal;
    }

    public String getMensagem() {
        return mensagem;
    }

    private void converteASCII(boolean[] bits) {
        int codigoAscii = 0;
        int expoente = bits.length - 1;

        //convertendo os "bits" para valor inteiro para então encontrar o valor tabela ASCII
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]) {
                codigoAscii += Math.pow(2, expoente);
            }
            expoente--;
        }

        //Concatenando cada simbolo na mensagem original
        this.mensagem += (char) codigoAscii;
    }

    private boolean decodificarDadoCRC(boolean[] bits) {
        boolean[] temp = Arrays.copyOf(bits, bits.length);

        for (int i = 0; i <= bits.length - Canal.polinomio.length; i++) {
            if (temp[i]) {
                for (int j = 0; j < Canal.polinomio.length; j++) {
                    temp[i + j] ^= Canal.polinomio[j];
                }
            }
        }

        // Verifica se o restante (CRC) é diferente de zero
        for (int i = bits.length - Canal.polinomio.length + 1; i < bits.length; i++) {
            if (temp[i]) {
                return false;
            }
        }

        // Extração dos dados originais (sem CRC)
        boolean[] dados = Arrays.copyOf(bits, bits.length - Canal.polinomio.length + 1);

        converteASCII(dados);

        return true;
    }

    private boolean decodificarDadoHamming(boolean[] bits) {
        int divisorBits = bits.length / 7;
        boolean[] dadosOriginais = new boolean[divisorBits * 4];

        for (int cont = 0; cont < divisorBits; cont++) {

            int base = cont * 7;
            boolean h1 = bits[base];
            boolean h2 = bits[base + 1];
            boolean b1 = bits[base + 2];
            boolean h3 = bits[base + 3];
            boolean b2 = bits[base + 4];
            boolean b3 = bits[base + 5];
            boolean b4 = bits[base + 6];

            // Bits de verificação
            boolean vf1 = h1 ^ b1 ^ b2 ^ b4;
            boolean vf2 = h2 ^ b1 ^ b3 ^ b4;
            boolean vf3 = h3 ^ b2 ^ b3 ^ b4;

            int posicaoErro = 0;
            if (vf1) {
                posicaoErro++;
            }
            if (vf2) {
                posicaoErro = posicaoErro + 2;
            }
            if (vf3) {
                posicaoErro = posicaoErro + 4;
            }

            if (posicaoErro > 0 && posicaoErro <= 7) {
                bits[base + posicaoErro - 1] = !bits[base + posicaoErro - 1];

                b1 = bits[base + 2];
                b2 = bits[base + 4];
                b3 = bits[base + 5];
                b4 = bits[base + 6];

            }

            dadosOriginais[cont * 4] = b1;
            dadosOriginais[cont * 4 + 1] = b2;
            dadosOriginais[cont * 4 + 2] = b3;
            dadosOriginais[cont * 4 + 3] = b4;
        }

        converteASCII(dadosOriginais);
        return true;
    }

    //recebe os dados do transmissor
    public void receberDadoBits() {
        boolean sucesso = false;

        if (this.tecnica == Estrategia.CRC) {
            sucesso = decodificarDadoCRC(this.canal.recebeDado());
        } else {
            sucesso = decodificarDadoHamming(this.canal.recebeDado());
        }

        this.canal.enviaFeedBack(sucesso);
    }

    public void gravaMensArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/database/mensagem.txt"))) {
            writer.write(this.getMensagem());
        } catch (IOException e) {
            System.out.println("Erro ao salvar o dado" + e.getMessage());
        }
    }
}
