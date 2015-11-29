package com.thomas.game_2048;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 15-11-20.
 */
public class GameView extends GridLayout {

    private Card[][] cards;
    private List<Point> emptypoints;
    public int score=0;
    private boolean moved;
    public static GameView gameView = null;


    public GameView(Context context) {
        super(context);
        InitGameView();
        gameView = this;
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitGameView();
        gameView = this;
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        InitGameView();
        gameView = this;
    }

    private void InitGameView(){

        setColumnCount(4);
        setBackgroundColor(Color.parseColor("#ffffff"));

        setOnTouchListener(new OnTouchListener() {

            private float startX;
            private float startY;
            private float offsetX;
            private float offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;

                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -10) {
                                //System.out.println("向左");
                                swipeLeft();

                            } else if (offsetX > 10) {
                                //System.out.println("向右");
                                swipeRight();
                            }
                        } else if (offsetY < -10) {
                            //System.out.println("向上");
                            swipeUp();
                        } else if (offsetY > 10) {
                            //System.out.println("向下");
                            swipeDown();
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w,h)-10)/4;

        addCards(cardWidth, cardWidth);

        startGame();
    }

    private void addCards(int cardWidth,int cardHeight){

        Card card;
        cards = new Card[4][4];

        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){

                card = new Card(getContext());
                card.setNumber(0);
                addView(card,cardWidth,cardHeight);

                cards[x][y] = card;

            }
        }

    }

    private void setCardColor(Card card){

        String bgColor = "";
        switch (card.getNumber())
        {
            case 0:
                bgColor = "#CCC0B3";
                break;
            case 2:
                bgColor = "#EEE4DA";
                break;
            case 4:
                bgColor = "#EDE0C8";
                break;
            case 8:
                bgColor = "#F2B179";
                break;
            case 16:
                bgColor = "#F49563";
                break;
            case 32:
                bgColor = "#F5794D";
                break;
            case 64:
                bgColor = "#F55D37";
                break;
            case 128:
                bgColor = "#EEE863";
                break;
            case 256:
                bgColor = "#EDB04D";
                break;
            case 512:
                bgColor = "#ECB04D";
                break;
            case 1024:
                bgColor = "#EB9437";
                break;
            case 2048:
                bgColor = "#EA7821";
                break;
            default:
                bgColor = "#EA7821";
                break;
        }

        card.getlable().setBackgroundColor(Color.parseColor(bgColor));

    }

    public void startGame(){

        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                cards[x][y].setNumber(0);
                setCardColor(cards[x][y]);
            }
        }

        addRandomnumber();
        addRandomnumber();
        score=0;
        MainActivity.getMainActivity().showScore(score);
    }



    private void addRandomnumber(){

        emptypoints = new ArrayList<Point>();
        emptypoints.clear();

        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                if(cards[x][y].getNumber()<=0){
                    emptypoints.add(new Point(x,y));
                }
            }
        }

        Point point = emptypoints.remove((int)(Math.random()*emptypoints.size()));
        cards[point.x][point.y].setNumber(Math.random() > 0.1 ? 2 : 4);
        setCardColor(cards[point.x][point.y]);

    }

    private void swipeLeft(){

        moved = false;

        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                for(int x1=x+1;x1<4;x1++){
                    if(cards[x1][y].getNumber()>0){
                        if(cards[x][y].getNumber()<=0){
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x--;
                            moved=true;
                        }
                        else if(cards[x][y].getNumber()==cards[x1][y].getNumber()){
                            cards[x][y].setNumber(cards[x1][y].getNumber() * 2);
                            score=score+cards[x][y].getNumber();
                            MainActivity.getMainActivity().showScore(score);
                            cards[x1][y].setNumber(0);
                            moved=true;
                        }
                        break;
                    }
                }
            }
            for(int x=0;x<4;x++){
                setCardColor(cards[x][y]);
            }
        }
        if(moved){
            addRandomnumber();
        }

    }

    private void swipeRight(){

        moved = false;

        for(int y=0;y<4;y++){
            for(int x=3;x>=0;x--){
                for(int x1=x-1;x1>=0;x1--){
                    if(cards[x1][y].getNumber()>0){
                        if(cards[x][y].getNumber()<=0){
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x++;
                            moved=true;
                        }
                        else if(cards[x][y].getNumber()==cards[x1][y].getNumber()){
                            cards[x][y].setNumber(cards[x1][y].getNumber()*2);
                            score=score+cards[x][y].getNumber();
                            MainActivity.getMainActivity().showScore(score);
                            cards[x1][y].setNumber(0);
                            moved=true;
                        }
                        break;
                    }
                }
            }
            for(int x=3;x>=0;x--){
                setCardColor(cards[x][y]);
            }
        }
        if(moved){
            addRandomnumber();
        }

    }

    private void swipeUp(){
        moved = false;

        for(int x=0;x<4;x++){
            for(int y=0;y<4;y++){
                for(int y1=y+1;y1<4;y1++){
                    if(cards[x][y1].getNumber()>0){
                        if(cards[x][y].getNumber()<=0){
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y--;
                            moved=true;
                        }else if(cards[x][y].getNumber()==cards[x][y1].getNumber()){
                            cards[x][y].setNumber(cards[x][y1].getNumber()*2);
                            score=score+cards[x][y].getNumber();
                            MainActivity.getMainActivity().showScore(score);
                            cards[x][y1].setNumber(0);
                            moved=true;
                        }
                        break;
                    }
                }
            }
            for(int y=0;y<4;y++){
                setCardColor(cards[x][y]);
            }
        }
        if(moved){
            addRandomnumber();
        }

    }

    private void swipeDown(){
        moved = false;

        for(int x=0;x<4;x++){
            for(int y=3;y>=0;y--){
                for(int y1=y-1;y1>=0;y1--){
                    if(cards[x][y1].getNumber()>0){
                        if(cards[x][y].getNumber()<=0){
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y++;
                            moved=true;
                        }
                        else if(cards[x][y].getNumber()==cards[x][y1].getNumber()){
                            cards[x][y].setNumber(cards[x][y1].getNumber()*2);
                            score=score+cards[x][y].getNumber();
                            MainActivity.getMainActivity().showScore(score);
                            cards[x][y1].setNumber(0);
                            moved=true;
                        }
                        break;
                    }
                }
            }
            for(int y=3;y>=0;y--){
                setCardColor(cards[x][y]);
            }
        }
        if(moved){
            addRandomnumber();
        }

    }

    public static GameView getGameView(){
        return gameView;
    }

}
