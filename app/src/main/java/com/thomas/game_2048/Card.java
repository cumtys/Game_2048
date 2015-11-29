package com.thomas.game_2048;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by thomas on 15-11-20.
 */
public class Card extends FrameLayout {

    private int number=0;
    private TextView lable;
    public Card(Context context) {
        super(context);

        lable = new TextView(getContext());
        lable.setTextSize(32);
        lable.setGravity(Gravity.CENTER);
        lable.setTextColor(0x33000000);
        lable.setBackgroundColor(0x33bfbfbf);


        LayoutParams lp = new LayoutParams(-1,-1);
        lp.setMargins(10,10,10,10);
        addView(lable,lp);

        setNumber(0);
    }

    public TextView getlable(){

        return lable;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;

        if(number<=0){
            lable.setText("");
        }
        else {
            lable.setText(number + "");
        }


    }

    public boolean equals(Card o) {

        return getNumber() == o.getNumber();
    }
}
