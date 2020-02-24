package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main2Activity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener,
        View.OnClickListener                          {
        private long backPressedTime;
        private Toast backToast;
        private TextView textView;
        private int points = 0;
        private int pps = 10;
        private PointsCounter pointsCounter = new PointsCounter();
        private Typeface ttf;
        private Random random;
        private CountDownTimer toastCountDown;
        private String dollar = "$";

        private int[] Images = {R.drawable.engineer};
        private String[] Names = {"Расширение рабочей силы"};

        private String[] Description = {"+10 долларов в секунду"};


    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RelativeLayout relativeLayout = findViewById(R.id.container);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground(); //анимированый бекграунд
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        textView = findViewById(R.id.textView);
        ttf = Typeface.createFromAsset(getAssets(), "JandaManateeSolid.ttf");
        textView.setTypeface(ttf);
        random = new Random();
        }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imageView){
            Animation a = AnimationUtils.loadAnimation(this, R.anim.clothesanim);
            a.setAnimationListener(new SimpleAnimListener(){
                @Override
                public void onAnimationEnd(Animation animation){
                    clothesClick();
                }
            });
            v.startAnimation(a);
        } else if (v.getId() == R.id.btnShop){
            showShopFragment();
        }
    }//анимация


    private void clothesClick() {
        points++;
        textView.setText(Integer.toString(points));
        showToast(R.string.clicked);
    }//отслежевание кликов

    @SuppressLint("RtlHardcoded")
    private void showToast(int stringId) {
        final Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER|Gravity.LEFT, random.nextInt(600)+100 ,random.nextInt(600)-300);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView textView = new TextView(this);
        textView.setText(stringId);
        textView.setTextSize(40f);
        textView.setTextColor(Color.GREEN);
        textView.setTypeface(ttf);
        toast.setView(textView);
        toast.show();
    }//toast

    private void update() {
        points += pps/10;
        textView.setText(Integer.toString(points));
    }//прибавление при клике

    private void showShopFragment(){
        ViewGroup container = findViewById(R.id.container);
        ShopAdapter shopAdapter = new ShopAdapter();
        container.removeAllViews();
        container.addView(getLayoutInflater().inflate(R.layout.shop_activity, null));
        ((ListView)findViewById(R.id.listShop)).setAdapter(shopAdapter);
    }

    public class PointsCounter{
        private Timer timer;

        public  PointsCounter(){
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        update();
                    }
                });
                }
            }, 1000, 100);
        }
    }//таймер


    public class ShopAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Images.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.item_listview, null);

            ((ImageView)convertView.findViewById(R.id.imgItem)).setImageResource(Images[position]);
            ((TextView)convertView.findViewById(R.id.itmName)).setText(Names[position]);
            ((TextView)convertView.findViewById(R.id.itmDescription)).setText(Description[position]);

            return convertView;
        }
    }



    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else {
            backToast = Toast.makeText(getBaseContext(), "Нажмите еще раз чтобы выйти", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }//двойное нажатие чтобы выйти


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}


