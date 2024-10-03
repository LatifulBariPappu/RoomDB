package com.example.roomdb;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import java.util.List;

public class FetchDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_data);

        recyclerView=findViewById(R.id.recyclerview);
        fetchRoomData();
    }

    private void fetchRoomData() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "room_db").allowMainThreadQueries().build();
        UserDao userDao=db.userDao();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<User> userList=userDao.getAll();
        UserAdapter userAdapter=new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
}