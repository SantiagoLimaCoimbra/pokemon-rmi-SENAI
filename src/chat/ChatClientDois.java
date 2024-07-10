package chat;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientDois implements Runnable{

    private static final String HOST = "127.0.0.1";
    private ClientSocket clientSocket;
    private Scanner scanner = new Scanner(System.in);

    public void start() throws IOException {
        clientSocket = new ClientSocket(new Socket(HOST, ChatServer.PORT));
        System.out.println("Cliente conectado ao servidor.");
        new Thread(this).start();
        enviarMensagens();
    }

    public void enviarMensagens() {
        String mensagem = "";
        while(!mensagem.equalsIgnoreCase("sair")) {
            System.out.println("Digite a mensagem ou SAIR para finalizar: ");
            mensagem = scanner.nextLine();
            clientSocket.enviarMensagem(mensagem);
        }
    }

    public static void main(String[] args) {
        try {
            ChatClient client = new ChatClient();
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Cliente finalizado!");
    }

    @Override
    public void run() {
        String mensagem = "";
        while((mensagem = clientSocket.receberMensagem()) != null) {
            System.out.println("Mensagem recebida: " + mensagem);
        }
    }

}
