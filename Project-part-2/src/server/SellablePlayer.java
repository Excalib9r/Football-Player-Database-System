package server;

import sample.Player;

import java.io.Serializable;

public class SellablePlayer implements Serializable {
    private Player player;
    private boolean sameClub;

    public SellablePlayer(){
        player = new Player();
        sameClub = false;
    }

    public SellablePlayer(Player player, boolean sameClub){
        this.player = player;
        this.sameClub = sameClub;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return  this.player;
    }

    public void setSameClub(boolean sameClub){
        this.sameClub = sameClub;
    }
    public boolean getSameClub(){
        return this.sameClub;
    }
}
