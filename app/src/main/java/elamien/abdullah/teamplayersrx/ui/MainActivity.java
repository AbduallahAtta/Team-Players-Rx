package elamien.abdullah.teamplayersrx.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

import elamien.abdullah.teamplayersrx.R;
import elamien.abdullah.teamplayersrx.adapters.PlayersAdapter;
import elamien.abdullah.teamplayersrx.databinding.ActivityMainBinding;
import elamien.abdullah.teamplayersrx.model.Player;
import elamien.abdullah.teamplayersrx.model.TeamPlayers;
import elamien.abdullah.teamplayersrx.rest.Client;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final CompositeDisposable mDisposables = new CompositeDisposable();
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loadTeamPlayers();
    }

    private void loadTeamPlayers() {
        Observable<TeamPlayers> teamPlayersObservable = Client.getsRetrofit().getTeamPlayers("arsenal");
        mDisposables.add(teamPlayersObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .map(teamPlayers -> filterPlayersWithFirstLetter(teamPlayers, "m"))
                .map(teamPlayers -> getPlayersWhereDateBornGreaterOrEqualsTo(teamPlayers, 1990))
                .subscribe(this::handleResult, this::handleError));
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

    @Override
    protected void onStop() {
        super.onStop();
        mDisposables.dispose();
    }

    private void handleResult(List<Player> teamPlayers) {
        PlayersAdapter adapter = new PlayersAdapter(this, teamPlayers);
        mainBinding.recyclerView.setAdapter(adapter);
    }

    private void handleError(Throwable e) {
        Log.d(TAG, "handleError: " + e.getMessage());
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
}