package elamien.abdullah.teamplayersrx.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import elamien.abdullah.teamplayersrx.model.Player;
import elamien.abdullah.teamplayersrx.model.TeamPlayers;
import elamien.abdullah.teamplayersrx.repository.Repository;

/**
 * Created by AbdullahAtta on 7/15/2019.
 */
public class TeamPlayerViewModel extends ViewModel {
    private Repository mRepository = Repository.getInstance();

    public LiveData<TeamPlayers> getTeamPlayers() {
        return mRepository.getTeamPlayers();
    }

    public LiveData<List<Player>> getFilteredPlayers() {
        return mRepository.getFilteredPlayersWithFirstLetter();
    }

    public LiveData<List<Player>> getPlayersWhereBorn() {
        return mRepository.getPlayersWithDateBornGreaterOrEqualTo();
    }
}
