package sample;

import javafx.application.Platform;
import server.ClientClubList;
import util.LoginDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Client client;
    private Club clb;

    public ReadThread(Client client) {
        clb = new Club();
        this.client = client;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = client.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof ClientClubList) {
                        ClientClubList clubList = (ClientClubList) o;
                        LoginDTO loginDTO = clubList.getLoginDTO();
                        Club club = clubList.getClub();
                        clb = club;
                        List<Player> marketList = clubList.getMarketList();
                        System.out.println(marketList.size() + "  ");
                        List<Player> samePlayer = new ArrayList<>();

                        for(Player p : marketList){
                            if(p.getClub().equalsIgnoreCase(clb.getClubName()))
                                samePlayer.add(p);
                        }
                        for(Player p : samePlayer){
                            for(Player pc : marketList){
                                if(p.getClub().equalsIgnoreCase(pc.getClub())){
                                    marketList.remove(pc);
                                    break;
                                }
                            }
                        }
                         System.out.println(marketList.size());
                         // the following is necessary to update JavaFX UI components from user created threads
                         Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus()) {
                                    try {
                                        client.setClub(club);
                                        client.setClbName(loginDTO.getUserName());
                                        client.setMarketList(marketList);
                                        client.showMenuPage();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    client.showAlert("Login Unsuccessful");
                                }

                            }
                        });
                    }
                    if(o instanceof List){
                        List<Player> marketList = (List<Player>) o;
                        List<Player> samePlayer = new ArrayList<>();
                        System.out.println(marketList.size() + "   ");
                        for(Player p : marketList){
                            if(p.getClub().equalsIgnoreCase(clb.getClubName()))
                                samePlayer.add(p);
                        }
                        for(Player p : samePlayer){
                            for(Player pc : marketList){
                                if(p.getClub().equalsIgnoreCase(pc.getClub())){
                                    marketList.remove(pc);
                                    break;
                                }
                            }
                        }
                        System.out.println(marketList.size());
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                client.setMarketList(marketList);
                            }
                        });
                    }
                    if(o instanceof Player){
                        Player player = (Player) o;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                client.getClub().removePlayer(player);
                            }
                        });
                    }
                    if(o instanceof String){
                        String str = (String) o;
                        if(str.equalsIgnoreCase("Refresh list"))
                        {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if(client.getInMarket()){
                                        try {
                                            client.showMarketListPage();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    if(client.getInList()){
                                        try{
                                            client.showPlayerListPage();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                client.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



