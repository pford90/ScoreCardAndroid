package com.peterford.scorecardandroid.adapter;

import android.content.Context;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.peterford.scorecardandroid.R;
import com.peterford.scorecardandroid.model.Hole;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Peter on 1/27/2018.
 */

public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder> {


    private Context mContext;
    private Hole[] mHoles;

    public HoleAdapter(Context context, Hole[] holes) {
        this.mContext = context;
        this.mHoles = holes;
    }

    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_list_item_hole, parent, false);
        HoleViewHolder holder = new HoleViewHolder(view);
        view.setBackground(mContext.getDrawable(R.drawable.main_scoreboard_list_view_border));

        return holder;
    }

    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position) {
        holder.bindHole(mContext, mHoles[position]);
    }

    @Override
    public int getItemCount() {
        return mHoles.length;
    }

    protected class HoleViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.list_item_add_button) Button mAddButton;
        @BindView(R.id.list_item_minus_button) Button mMinusButton;
        @BindView(R.id.list_item_hole_label) TextView mHoleLabel;
        @BindView(R.id.list_item_score_value) TextView mScoreValue;


        public HoleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView );
        }

        public void bindHole(Context context, Hole hole) {
            itemView.setTag(hole);
            String holeLabelStr = String.format(context.getResources().getString(R.string.score_hole_label), hole.getHolePosition() );
            mScoreValue.setText(hole.getStrokeCount() + "");
            mHoleLabel.setText( holeLabelStr );
        }

        @OnClick(R.id.list_item_add_button)
        public void addOnClick(View view) {
            Hole hole = (Hole) itemView.getTag();
            int strokeCnt = hole.getStrokeCount();
            strokeCnt++;
            hole.setStrokeCount(strokeCnt);
            notifyDataSetChanged();
        }

        @OnClick(R.id.list_item_minus_button)
        public void minusOnClick(View view) {
            Hole hole = (Hole) itemView.getTag();
            int strokeCnt = hole.getStrokeCount();
            strokeCnt--;
            hole.setStrokeCount(strokeCnt);
            notifyDataSetChanged();
        }


    }

}
