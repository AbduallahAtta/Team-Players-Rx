package elamien.abdullah.teamplayersrx.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import java.util.ArrayList;
import java.util.List;

import elamien.abdullah.teamplayersrx.model.Player;
import elamien.abdullah.teamplayersrx.model.TeamPlayers;
import elamien.abdullah.teamplayersrx.rest.Client;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by AbdullahAtta on 7/15/2019.
 */
public class Repository {
    private static final String TAG = "Repository";
    private static Repository sRepository;

    private Repository() {
    }

    public static Repository getInstance() {
        if (sRepository == null) {
            sRepository = new Repository();
        }
        return sRepository;
    }

    /**
     * @return All team players
     */
    public LiveData<TeamPlayers> getTeamPlayers() {
        return LiveDataReactiveStreams
                .fromPublisher(Client.getsRetrofit().getTeamPlayers("arsenal")
                        .subscribeOn(Schedulers.io()));
    }

    /**
     * @return filtered player with the first letter of their names
     */
    public LiveData<List<Player>> getFilteredPlayersWithFirstLetter() {
        return LiveDataReactiveStreams
                .fromPublisher(Client.getsRetrofit().getTeamPlayers("arsenal")
                        .subscribeOn(Schedulers.io())
                        .map(teamPlayers -> filterPlayersWithFirstLetter(teamPlayers, "A")));
    }

    private List<Player> filterPlayersWithFirstLetter(TeamPlayers players, String prefix) {
        List<Player> list = new ArrayList<>();
        for (Player p : players.getPlayer()) {
            if (p.getStrPlayer().toUpperCase().startsWith(prefix.toUpperCase())) {
                list.add(p);
            }
        }
        return list;
    }

    /**
     * @return players born in 90s or greater
     */
    public LiveData<List<Player>> getPlayersWithDateBornGreaterOrEqualTo() {
        return LiveDataReactiveStreams
                .fromPublisher(Client.getsRetrofit().getTeamPlayers("arsenal")
                        .subscribeOn(Schedulers.io())
                        .map(teamPlayers -> getPlayersWhereDateBornGreaterOrEqualsTo(teamPlayers, 1990)));
    }

    private List<Player> getPlayersWhereDateBornGreaterOrEqualsTo(TeamPlayers teamPlayers, int prefix) {
        List<Player> list = new ArrayList<>();
        for (Player p : teamPlayers.getPlayer()) {
            try {
                int yearBorn = Integer.parseInt(p.getDateBorn().substring(0, 4));

                if (yearBorn >= prefix) {
                    list.add(p);
                }
            } catch (NumberFormatException e) {
                Log.d(TAG, "getPlayersWhereDateBornGreaterOrEqualsTo: " + e.getMessage());
            }
        }

        return list;
    }
}
