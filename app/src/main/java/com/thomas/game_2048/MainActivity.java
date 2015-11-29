package com.thomas.game_2048;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public TextView tvScore;
    private Toolbar toolbar;
    public static MainActivity mainActivity = null;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.Id_toolbar);
        setSupportActionBar(toolbar);
        tvScore=(TextView)findViewById(R.id.Id_tvScore);
        tvScore.setTextSize(15);



        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.Id_new_game:
                        System.out.println("新游戏");
                        GameView.getGameView().startGame();
                        break;
                    case R.id.Id_share:
                        System.out.println("分享");
                        break;
                }
                return true;
            }
        });

    }


    public static MainActivity getMainActivity(){
        return mainActivity;
    }



    public void showScore(int scroe){
        tvScore.setText(scroe+"");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
