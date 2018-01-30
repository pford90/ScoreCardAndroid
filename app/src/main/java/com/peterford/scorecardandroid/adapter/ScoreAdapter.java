package com.peterford.scorecardandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.peterford.scorecardandroid.R;
import com.peterford.scorecardandroid.model.Score;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private Score[] mScores;
    private Context mContext;

    public ScoreAdapter(Context context, Score[] scores) {
        this.mContext = context;
        this.mScores = scores;
    }

    public void swap(Score[] scores){
        mScores = scores;
        notifyDataSetChanged();
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item_score, parent, false);
        view.setBackground(mContext.getDrawable(R.drawable.main_scoreboard_list_view_border));
        ScoreViewHolder holder = new ScoreViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        holder.bindData(mContext, mScores[position]);
    }

    @Override
    public int getItemCount() {
        return mScores.length;
    }


    protected class ScoreViewHolder extends RecyclerView.ViewHolder {

        private String TAG = ScoreViewHolder.class.getSimpleName();

        @BindView(R.id.scoreValue) TextView mScoreValue;
        @BindView(R.id.holeLabel) TextView mHoleLabel;
        @BindView(R.id.minusButton) Button mMinusButton;
        @BindView(R.id.addButton) Button mAddButton;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Context context, Score score) {
            itemView.setTag(score);
            String holeLabelStr = String.format(context.getResources().getString(R.string.score_hole_label), score.getHole() );
            mScoreValue.setText(score.getScore() + "");
            mHoleLabel.setText( holeLabelStr );
        }

        @OnClick(R.id.addButton)
        public void addOnClick(View view) {
            Score score = (Score)itemView.getTag();
//            Score score = (Score) view.getTag();
            int scoreValue = score.getScore();
            scoreValue++;
            score.setScore(scoreValue);
            mScoreValue.setText(scoreValue + "");

            notifyDataSetChanged();
        }

        @OnClick(R.id.minusButton)
        public void minusOnClick(View view) {
            Score score = (Score)itemView.getTag();
//            Score score = (Score) view.getTag();
            int scoreValue = score.getScore();
            scoreValue--;
            score.setScore(scoreValue);
            mScoreValue.setText(scoreValue + "");
            notifyDataSetChanged();
        }
    }
}
