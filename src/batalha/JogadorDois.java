package batalha;

import java.io.IOException;

public class JogadorDois extends Jogador {

    public JogadorDois(String host, int port, Pokemon pokemon) throws IOException {
        super(host, port, pokemon);
    }

    @Override
    public void jogar() {
        while (pokemon.getVida() > 0) {
            //receberMensagemComPrompt();
            //solicitarAcao();
            System.out.println("Escolha sua ação (1. Atacar 2. Defender): ");
            int acao = scanner.nextInt();
            enviarMensagem(String.valueOf(acao));
        }
    }

    public static void main(String[] args) {
        try {
            Pokemon bulbasaur = new Pokemon("Bulbasaur", 100, 20, 10); //tirar
            JogadorDois jogador = new JogadorDois("127.0.0.1", GameServer.PORT, bulbasaur);
            jogador.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
