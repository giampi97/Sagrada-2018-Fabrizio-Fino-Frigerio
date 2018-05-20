package it.polimi.se2018.utils.network;

import it.polimi.se2018.utils.network.Connection;
import it.polimi.se2018.utils.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Connessione socket con il client
 * @author Alessio
 */
public class SocketConnection extends Thread implements Connection {
    private Socket socket;
    private NetworkHandler networkHandler;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    /**
     * Costruttore
     * @param networkHandler server
     * @param socket socket della connessione istaurata con il client
     */
    public SocketConnection(NetworkHandler networkHandler, Socket socket) {
        this.networkHandler = networkHandler;
        this.socket = socket;
        try {
            out = new ObjectOutputStream(this.socket.getOutputStream());
            in = new ObjectInputStream(this.socket.getInputStream());
        }catch(IOException e){
            close();
        }
    }

    /**
     * Invia messaggio
     * @param message messaggio da inviare
     */
    public boolean sendMessage(Message message){
        try{
            out.writeObject(message);
            out.flush();
            return true;
        }catch (IOException e){
            close();
            return false;
        }
    }

    /**
     * Chiude connessione
     */
    public void close(){
        try{
            out.close();
        }catch (IOException e){}

        try {
            in.close();
        } catch (IOException e){}

        try{
            socket.close();
        }catch (IOException e){}

        networkHandler.removeConnection(this);
    }

    @Override
    public void run() {
        Message message;
        while (true) {
            try{
                message = (Message) in.readObject();
                networkHandler.reciveMessage(message,this);
            }catch (IOException | ClassNotFoundException e){
                break;
            }

        }
        close();
    }
}