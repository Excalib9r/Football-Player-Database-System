package server;

import sample.Club;
import sample.Player;
import util.LoginDTO;
import util.NetworkUtil;

import java.io.IOException;
import sample.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    private List<Club> clubs;
    private HashMap<String, String> userMap;
    private HashMap<String, NetworkUtil> clientList;
    private List<Player> marketList;
    private Club club;

    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil, HashMap<String, NetworkUtil> clientList, List<Club> clubs, List<Player> marketList) {
        club = new Club();
        this.userMap = map;
        this.clientList = clientList;
        this.marketList = marketList;
        this.clubs = clubs;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        String password = userMap.get(loginDTO.getUserName());
                        loginDTO.setStatus(loginDTO.getPassword().equals(password));
                        if(loginDTO.getPassword().equals(password)){
                            clientList.put(loginDTO.getUserName(), this.networkUtil);
                        }
                        for(Club c : clubs){
                            if(c.getClubName().equalsIgnoreCase(loginDTO.getUserName()))
                                club = c;
                        }
                        ClientClubList clubList = new ClientClubList(loginDTO, club, marketList);
                        networkUtil.write(clubList);
                    }
                    if(o instanceof Player){
                        Player player = (Player) o;
                        boolean in = false;
                        if(marketList.size() == 0){
                            marketList.add(player);
                        }
                        else {
                            for(Player p : marketList){
                                if(p.getName().equalsIgnoreCase(player.getName()))
                                    in = true;
                            }
                            if(!in)
                                marketList.add(player);
                        }
                        for(Map.Entry<String, NetworkUtil> entry : clientList.entrySet()){
                            entry.getValue().write(marketList);
                            entry.getValue().write("Refresh list");
                        }

                    }
                    if(o instanceof SellablePlayer){
                        SellablePlayer sellablePlayer = (SellablePlayer) o;
                        Player player = sellablePlayer.getPlayer();

                        for(Club c : clubs){
                            if(c.getClubName().equalsIgnoreCase(player.getClub()))
                                c.removePlayer(player);
                        }

                        NetworkUtil net = clientList.get(player.getClub().toLowerCase(Locale.ROOT));
                        if(net != null){
                            if(!sellablePlayer.getSameClub())
                            net.write(player);
                        }

                        for(Player p : marketList){
                            if(p.getName().equalsIgnoreCase(player.getName())){
                                marketList.remove(p);
                                break;
                            }
                        }

                        for(Map.Entry<String, NetworkUtil> entry : clientList.entrySet()){
                            entry.getValue().write(marketList);
                            entry.getValue().write("Refresh list");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



