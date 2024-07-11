package batalha;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public abstract class Jogador implements Runnable {
    protected PlayerSocket playerSocket;
    protected Scanner scanner = new Scanner(System.in);
    protected Pokemon pokemon;

    public Jogador(String host, int port, Pokemon pokemon) throws IOException {
        this.playerSocket = new PlayerSocket(new Socket(host, port));
        this.pokemon = pokemon;
    }

    public abstract void jogar();

    public void enviarMensagem(String mensagem) {
        playerSocket.enviarMensagem(mensagem);
    }

    public String receberMensagem() {
        return playerSocket.receberMensagem();
    }

//    public void solicitarAcao() {
//        System.out.println("Escolha sua ação (1. Atacar 2. Defender): ");
//    }

//    public String receberMensagemComPrompt() {
//        String mensagem = receberMensagem();
//        while (mensagem != null && !mensagem.startsWith("Escolha sua ação")) {
//            System.out.println(mensagem);
//            mensagem = receberMensagem();
//        }
//        return mensagem;
//    }

    @Override
    public void run() {
        while (true) {
            String mensagem = receberMensagem();
            if (mensagem != null) {
                System.out.println(mensagem);
            }
        }
    }

    public void iniciar() throws IOException {
        new Thread(this).start();
        jogar();
    }
}
