package com.lechot.esgi.timeup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lechot.esgi.timeup.R;
import com.lechot.esgi.timeup.Player;

/**
 * Created by Bastien on 18/05/2016.
 */
public class PlayersAdapter extends ArrayAdapter<Player> {

    private static final int layoutResource = R.layout.item_player;
    private boolean inEditMode = false;

    public PlayersAdapter(Context context) {
        super(context, layoutResource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        final Player player = getItem(position);
        TextView playerName = (TextView) convertView.findViewById(R.id.player_name_text_view);
        playerName.setText(player.getName());

        Button buttonDelete = (Button) convertView.findViewById(R.id.delete_player_button);
        buttonDelete.setVisibility(inEditMode ? View.VISIBLE : View.GONE);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(player);
            }
        });

        return convertView;
    }

    public void setEditMode(boolean inEditMode) {
        this.inEditMode = inEditMode;
        notifyDataSetChanged();
    }
}
