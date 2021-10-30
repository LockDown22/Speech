package com.example.bai70;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button speech;
    TextView content;
    public static final int RESULT_SPEECH = 1;
    String chuoi;
    ImageView hinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        speech = findViewById(R.id.btnspeech);
        content = findViewById(R.id.tv1);
        hinh = findViewById(R.id.imgHinh);

        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuly();
            }
        });




    }
    public void xuly(){
        hinh.setImageResource(0);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");

        try{
            startActivityForResult(intent,RESULT_SPEECH);
            content.setText("");
        }catch (ActivityNotFoundException a){
            Toast.makeText(MainActivity.this, a+"", Toast.LENGTH_SHORT).show();
        }
        ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:{
                if(resultCode==RESULT_OK&& data !=null){
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    chuoi = text.get(0);
                    content.setText(chuoi);
                    showHeart(text.get(0));
                }
                break;
            }
        }
    }

    public void showHeart(String chuoi){
        String []splited = chuoi.split("\\s+");
        for(String item : splited){
            if(item.equalsIgnoreCase("hello")||item.equalsIgnoreCase("hi")){
                hinh.setImageResource(R.drawable.heart);
            }

        }

    }
}