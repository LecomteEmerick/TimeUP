package com.lechot.esgi.timeup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Emerick Lecomte on 20/05/2016.
 */
public class PlayerStatsAdapter extends ArrayAdapter<Player> {

    private static final int layoutResource = R.layout.stats_player;

    public PlayerStatsAdapter(Context context) {
        super(context, layoutResource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        final Player player = getItem(position);
        TextView playerName = (TextView) convertView.findViewById(R.id.PlayerName);
        playerName.setText(player.getName());

        TextView rightAnswer = (TextView) convertView.findViewById(R.id.RightAnswerValue);
        rightAnswer.setText(Integer.toString(player.RightAnswerScore));

        TextView skipAnswer = (TextView) convertView.findViewById(R.id.SkipAnswerValue);
        skipAnswer.setText(Integer.toString(player.SkipAnswerScore));

        return convertView;
    }
}
