package com.peterford.scorecardandroid.model;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Peter on 1/17/2018.
 */

public class Score {
    int mHole;
    int mScore;

    public Score(int hole, int score) {
        mHole = hole;
        mScore = score;
    }

    public int getHole() {
        return mHole;
    }

    public void setHole(int hole) {
        this.mHole = hole;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        if( score < 0)
            this.mScore = 0;
        else
            this.mScore = score;
    }
}
