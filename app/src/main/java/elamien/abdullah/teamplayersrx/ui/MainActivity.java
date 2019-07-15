package elamien.abdullah.teamplayersrx.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import elamien.abdullah.teamplayersrx.R;
import elamien.abdullah.teamplayersrx.adapters.PlayersAdapter;
import elamien.abdullah.teamplayersrx.databinding.ActivityMainBinding;
import elamien.abdullah.teamplayersrx.model.Player;
import elamien.abdullah.teamplayersrx.viewmodel.TeamPlayerViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    private TeamPlayerViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(TeamPlayerViewModel.class);
        loadAllPlayers();
        //loadPlayersWithFirstLetter();
        //loadPlayersPornIn90s();
    }

    private void loadAllPlayers() {
        mViewModel.getTeamPlayers().observe(this, teamPlayers -> handleResult(teamPlayers.getPlayer()));
    }

    private void loadPlayersWithFirstLetter() {
        mViewModel.getFilteredPlayers().observe(this, this::handleResult);
    }

    private void loadPlayersPornIn90s() {
        mViewModel.getPlayersWhereBorn().observe(this, this::handleResult);
    }

    private void handleResult(List<Player> teamPlayers) {
        PlayersAdapter adapter = new PlayersAdapter(this, teamPlayers);
        mainBinding.recyclerView.setAdapter(adapter);
    }
}