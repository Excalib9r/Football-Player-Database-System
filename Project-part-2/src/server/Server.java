package server;

import sample.Club;
import sample.Player;
import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import sample.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private HashMap<String, String> userMap;
    private HashMap<String, NetworkUtil> clientList;
    private List<Player> marketList;
    private Collection c;
    private List<Club> clubs;


    Server() throws IOException {
        c = new Collection();
        clubs = c.getClubs();
        userMap = new HashMap<>();
        userMap.put("manchester united", "123");
        userMap.put("manchester city", "123");
        userMap.put("chelsea", "123");
        userMap.put("liverpool", "123");
        userMap.put("arsenal", "123");
        clientList = new HashMap<>();
        marketList = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public Collection getCollection(){
        return  c;
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap, networkUtil, clientList, clubs, marketList);
    }

    public static void main(String[] args) throws IOException {
        new Server();
    }
}
