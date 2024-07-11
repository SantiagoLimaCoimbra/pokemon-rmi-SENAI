package batalha;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class GameServer {

    public static final int PORT = 12345;
    private ServerSocket serverSocket;
    private List<PlayerSocket> players = new ArrayList<>();

    public void start() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor de jogo iniciado na porta: " + PORT);
        esperarConexoes();
    }

    public void esperarConexoes() throws IOException {
        while (players.size() < 2) {
            PlayerSocket socket = new PlayerSocket(serverSocket.accept());
            System.out.println("Jogador conectado: " + socket.getRemoteSocketAddress());
            players.add(socket);
        }
        iniciarJogo();
    }

    public void iniciarJogo() {
        Pokemon pokemonUm = new Pokemon("Pikachu", 100, 20, 10);
        Pokemon pokemonDois = new Pokemon("Bulbasaur", 100, 20, 10);

        while (pokemonUm.getVida() > 0 && pokemonDois.getVida() > 0) {

            int acaoUm = Integer.parseInt(players.get(0).receberMensagem());
            int acaoDois = Integer.parseInt(players.get(1).receberMensagem());

            processarAcoes(pokemonUm, pokemonDois, acaoUm, acaoDois);
            enviarEstado(pokemonUm, pokemonDois);

//            players.get(0).enviarMensagem("Escolha sua ação (1. Atacar 2. Defender):");
//            int acaoUm = Integer.parseInt(players.get(0).receberMensagem());
//
//            players.get(1).enviarMensagem("Escolha sua ação (1. Atacar 2. Defender):");
//            int acaoDois = Integer.parseInt(players.get(1).receberMensagem());


        }

        if (pokemonUm.getVida() <= 0) {
            players.get(1).enviarMensagem("Você venceu!");
            players.get(0).enviarMensagem("Você perdeu!");
            System.out.println("Jogador dois venceu");
        } else {
            players.get(0).enviarMensagem("Você venceu!");
            players.get(1).enviarMensagem("Você perdeu!");
            System.out.println("Jogador um venceu");
        }
    }

    public void processarAcoes(Pokemon pokemonUm, Pokemon pokemonDois, int acaoUm, int acaoDois) {
        if (acaoUm == 1 && acaoDois != 2) {
            int dano = pokemonUm.calcularDano();
            pokemonDois.receberDano(dano);
        }

        if (acaoDois == 1 && acaoUm != 2) {
            int dano = pokemonDois.calcularDano();
            pokemonUm.receberDano(dano);
        }
    }

    public void enviarEstado(Pokemon pokemonUm, Pokemon pokemonDois) {
        players.get(0).enviarMensagem("Seu Pokémon: " + pokemonUm.getVida() + " vida");
        players.get(0).enviarMensagem("Pokémon adversário: " + pokemonDois.getVida() + " vida");

        players.get(1).enviarMensagem("Seu Pokémon: " + pokemonDois.getVida() + " vida");
        players.get(1).enviarMensagem("Pokémon adversário: " + pokemonUm.getVida() + " vida");
    }

    public static void main(String[] args) {
        try {
            GameServer server = new GameServer();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
