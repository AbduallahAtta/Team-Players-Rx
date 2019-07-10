package elamien.abdullah.teamplayersrx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by AbdullahAtta on 7/10/2019.
 */
public class TeamPlayers {

    @SerializedName("player")
    @Expose
    private List<Player> player = null;

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

}