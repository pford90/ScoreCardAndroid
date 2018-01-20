package com.peterford.scorecardandroid.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.peterford.scorecardandroid.R;
import com.peterford.scorecardandroid.model.Score;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Peter on 1/17/2018.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private Score[] mScores;
    private Context mContext;
    public ScoreAdapter(Context context, Score[] scores) {
        this.mContext = context;
        this.mScores = scores;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item_score, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(mScores[position]);
    }

    @Override
    public int getItemCount() {
        return mScores.length;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.scoreValue) TextView mScoreValue;
        @BindView(R.id.holeLabel) TextView mHoleLabel;
        @BindView(R.id.minusButton) Button mMinusButton;
        @BindView(R.id.addButton) Button mAddButton;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Score score) {
            mScoreValue.setText(String.valueOf(score.getScore()));
            mHoleLabel.setText(
                    String.format(mContext.getResources().getString(R.string.score_hole_label), score.getHole() )
            );
        }

        @OnClick(R.id.addButton)
        public void addToScore(View view) {
            int scoreValue = new Integer(mScoreValue.getText().toString());
            scoreValue++;
            mScoreValue.setText(scoreValue + "");

        }

        @OnClick(R.id.minusButton)
        public void minusToScore(View view) {
            int scoreValue = new Integer(mScoreValue.getText().toString());
            scoreValue--;

            if( scoreValue < 0)
                scoreValue = 0;

            mScoreValue.setText(scoreValue + "");
        }

    }

}
