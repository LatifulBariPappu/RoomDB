package com.example.roomdb;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class UserAdapter {
    class myviewholder extends RecyclerView.ViewHolder{
        TextView uid,ufname,ulname;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            uid=itemView.findViewById(R.id.tvuid);
            ufname=itemView.findViewById(R.id.tvufname);
            ulname=itemView.findViewById(R.id.tvulname);

        }
    }

}

