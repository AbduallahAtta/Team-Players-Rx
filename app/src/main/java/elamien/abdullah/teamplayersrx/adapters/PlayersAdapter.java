package elamien.abdullah.teamplayersrx.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import elamien.abdullah.teamplayersrx.databinding.ListItemPlayersBinding;
import elamien.abdullah.teamplayersrx.model.Player;

/**
 * Created by AbdullahAtta on 7/10/2019.
 */
public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder> {
    private Context mContext;
    private List<Player> mPlayers;

    public PlayersAdapter(Context mContext, List<Player> mPlayers) {
        this.mContext = mContext;
        this.mPlayers = mPlayers;
    }

    @NonNull
    @Override
    public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ListItemPlayersBinding binding = ListItemPlayersBinding.inflate(inflater);
        return new PlayersViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewHolder playersViewHolder, int i) {
        playersViewHolder.bind(mPlayers.get(i));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class PlayersViewHolder extends RecyclerView.ViewHolder {
        ListItemPlayersBinding mBinding;

        public PlayersViewHolder(@NonNull ListItemPlayersBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        void bind(Player player) {
            mBinding.setPlayer(player);
        }
    }
}
