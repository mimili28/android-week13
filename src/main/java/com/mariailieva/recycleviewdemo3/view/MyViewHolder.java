package com.mariailieva.recycleviewdemo3.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mariailieva.recycleviewdemo3.R;
import com.mariailieva.recycleviewdemo3.model.City;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageView imageView;

    Context context;

    public MyViewHolder(@NonNull View itemView){
        super(itemView);
        LinearLayout linearLayout = (LinearLayout) itemView;
        textView = linearLayout.findViewById(R.id.textView);
        imageView = linearLayout.findViewById(R.id.imageView);
        context= itemView.getContext();
    }


    public void setData(City city) {
        textView.setText(city.getName());
        imageView.setImageResource(context.getResources().
                getIdentifier(city.getImage(), "drawable", context.getPackageName()));
    }
}
