package batalha;

import java.io.IOException;

public class JogadorUm extends Jogador {

    public JogadorUm(String host, int port, Pokemon pokemon) throws IOException {
        super(host, port, pokemon);
    }

    @Override
    public void jogar() {
        while (pokemon.getVida() > 0) {

            //receberMensagemComPrompt(); // Espera pelas mensagens de status
            //solicitarAcao();
            System.out.println("Escolha sua ação (1. Atacar 2. Defender): ");

            int acao = scanner.nextInt();
            enviarMensagem(String.valueOf(acao));
        }
    }

    public static void main(String[] args) {
        try {
            Pokemon pikachu = new Pokemon("Pikachu", 100, 20, 10); //tirar
            JogadorUm jogador = new JogadorUm("127.0.0.1", GameServer.PORT, pikachu);
            jogador.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
