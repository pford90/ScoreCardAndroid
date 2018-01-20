package com.peterford.scorecardandroid.model;

import java.util.List;

/**
 * Created by Peter on 1/17/2018.
 */

public class ScoreBoard {
    Score[] mScores;

    private static final int DEFAULT_SCORE_SIZE = 18;

    public ScoreBoard() {
        new ScoreBoard(DEFAULT_SCORE_SIZE);
    }

    public ScoreBoard(int scoreSize) {
        mScores = new Score[scoreSize];
        for( int i=0; i < scoreSize; i++) {
            mScores[i] = new Score(i+1, 0);
        }
    }

    public Score[] getScores() {
        return mScores;
    }

    public void setScores(Score[] scores) {
        this.mScores = scores;
    }
}
