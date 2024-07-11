package batalha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class PlayerSocket {

    private Socket socket;
    private BufferedReader in; //le mensagens recebidas do servidor
    private PrintWriter out; //enviar mensagens para o servidor

    public PlayerSocket(Socket socket) throws IOException {
        this.socket = socket;
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    //Lê uma mensagem do servidor
    public String receberMensagem() {
        try {
            return this.in.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    //Envia uma mensagem para o servidor
    public boolean enviarMensagem(String mensagem) {
        out.println(mensagem);
        return !out.checkError();
    }

    //Retorna o endereço do socket remoto (servidor).
    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

}
