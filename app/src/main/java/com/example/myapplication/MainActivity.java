package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button Continue;
    private EditText CompanyName;
    private static final String Empty = "";
    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
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

    public void login() {  //вход в игру
        Continue = (Button) findViewById(R.id.Continue);
        CompanyName = (EditText) findViewById(R.id.CompanyName);

        Continue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        if (Empty.equals(String.valueOf(CompanyName.getText())))
                        Toast.makeText(MainActivity.this, "Вы не ввели название фирмы!", Toast.LENGTH_SHORT).show(); //проверка на пустое поле

                        else {
                            startActivity(intent); //старт новой активити
                            finish();
                        }
                    }
                }

        );
    } //вход в игру
}