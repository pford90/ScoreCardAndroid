package com.peterford.scorecardandroid.model;

/**
 * Created by Peter on 1/27/2018.
 */

public class Hole {

    private int mHolePosition;
    private int mStrokeCount;

    public Hole(int holePosition, int strokeCount) {
        mHolePosition = holePosition;
        mStrokeCount = strokeCount;
    }

    public int getHolePosition() {
        return mHolePosition;
    }

    public void setHole(int holePosition) {
        this.mHolePosition = holePosition;
    }

    public int getStrokeCount() {
        return mStrokeCount;
    }

    public void setStrokeCount(int strokeCount) {

        if(strokeCount < 0 )
            strokeCount = 0;
        this.mStrokeCount = strokeCount;
    }
}
