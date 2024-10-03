package com.example.roomdb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    EditText ufname,ulname;
    Button saveBtn,fetchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ufname=findViewById(R.id.ufname);
        ulname=findViewById(R.id.ulname);
        saveBtn=findViewById(R.id.saveBtn);
        fetchBtn=findViewById(R.id.fetchBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new bgthread().start();
            }
        });
        fetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FetchDataActivity.class));
            }
        });
    }
    class bgthread extends Thread{
        public void run(){
            super.run();
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "room_db").build();
            UserDao userDao=db.userDao();
            User user=new User();
            userDao.insertAll(new User(user.getUid(),ufname.getText().toString(),ulname.getText().toString()));
            ufname.setText("");
            ulname.setText("");
        }
    }
}