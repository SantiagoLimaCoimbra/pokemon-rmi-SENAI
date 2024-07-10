package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {

    public static final int PORT = 12345;
    private ServerSocket serverSocker;
    private List<ClientSocket> clientes = new ArrayList<ClientSocket>();

    public void start() throws IOException {
        serverSocker = new ServerSocket(PORT);
        System.out.println("Servidor iniciado na porta: " + PORT);
        esperarConexoes();
    }

    public void esperarConexoes() throws IOException {
        while(true) {
            ClientSocket socket = new ClientSocket(serverSocker.accept());
            System.out.println("Cliente: " + socket.getRemoteSocketAddress() + " conectado!");
            clientes.add(socket);
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    esperarMensagens(socket);
                }
            };
            new Thread(runnable).start();
        }
    }

    public void esperarMensagens(ClientSocket socket) {
        try {
            String mensagem = "";
            while ((mensagem = socket.receberMensagem()) != null) {
                System.out.println("Mensagem recebida do cliente " +
                        socket.getRemoteSocketAddress() + " > " + mensagem);
                enviarMensagemParaTodosClientes(socket, mensagem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarMensagemParaTodosClientes(ClientSocket cliente, String mensagem) {
        for (ClientSocket socket : clientes) {
            if (!cliente.equals(socket)) {
                socket.enviarMensagem(mensagem);
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
