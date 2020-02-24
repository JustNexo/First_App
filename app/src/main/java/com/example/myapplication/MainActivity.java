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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login();
    }

    public void login() {
        Continue = (Button) findViewById(R.id.Continue);
        CompanyName = (EditText) findViewById(R.id.CompanyName);

        Continue.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        if (Empty.equals(String.valueOf(CompanyName.getText())))
                        Toast.makeText(MainActivity.this, "Вы не ввели название фирмы!", Toast.LENGTH_SHORT).show();
                        else
                            startActivity(intent);
                    }
                }

        );
    }
}