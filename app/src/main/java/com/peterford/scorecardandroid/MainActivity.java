package com.peterford.scorecardandroid;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.peterford.scorecardandroid.adapter.HoleAdapter;
import com.peterford.scorecardandroid.model.Hole;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static String PREF_FILE = "com.peterford.scorecardandroid.preferences";
    private static String KEY_STROKECOUNT = "key_strokecount";
    private static String TAG = MainActivity.class.getSimpleName();
    private HoleAdapter mHoleAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private Hole[] mHoles;


    @BindView(R.id.main_recycler_view) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSharedPreferences = getSharedPreferences(PREF_FILE, MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        //intialize holes
        setUpHoles();

        setUpRecyclerView();
        Log.i(TAG, "Inside onCreate()");
    }

    private void setUpHoles() {
        mHoles = new Hole[18];
        int strokeCnt = 0;
        for ( int i=0; i<18; i++) {
            strokeCnt = mSharedPreferences.getInt(KEY_STROKECOUNT+i, 0);
            mHoles[i] = new Hole(i+1, strokeCnt);
        }
    }

    private void setUpRecyclerView() {
        mHoleAdapter = new HoleAdapter(this, mHoles);
        mRecyclerView.setAdapter(mHoleAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.main_action_clear_strokes:
                for(int i=0; i<mHoles.length;i++) {
                    mHoles[i].setStrokeCount(0);
                }
                mHoleAdapter.notifyDataSetChanged();
                mEditor.clear();
                mEditor.apply();
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();

        for(int i=0; i<mHoles.length; i++) {
            mEditor.putInt(KEY_STROKECOUNT +i, mHoles[i].getStrokeCount());
        }
        mEditor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
