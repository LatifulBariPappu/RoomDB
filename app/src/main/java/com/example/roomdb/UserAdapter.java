package com.example.roomdb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.myviewholder>{
    List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        holder.uid.setText(String.valueOf(users.get(position).getUid()));
        holder.uname.setText(users.get(position).getFirstName());
        holder.ulname.setText(users.get(position).getLastName());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(holder.uid.getContext(),
                        AppDatabase.class, "room_db").allowMainThreadQueries().build();
                UserDao userDao=db.userDao();
                User user=new User();
                userDao.delete(user.uid);
                users.remove(holder.getAdapterPosition());
                notifyDataSetChanged();


            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class myviewholder extends RecyclerView.ViewHolder{
        TextView uid, uname,ulname;
        ImageButton delete;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            uid=itemView.findViewById(R.id.tvuid);
            uname =itemView.findViewById(R.id.tvufname);
            ulname=itemView.findViewById(R.id.tvulname);
            delete=itemView.findViewById(R.id.delBtn);
        }
    }

}

