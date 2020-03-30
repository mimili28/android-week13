package com.mariailieva.recycleviewdemo3.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mariailieva.recycleviewdemo3.R;
import com.mariailieva.recycleviewdemo3.storage.CityStorage;
import com.mariailieva.recycleviewdemo3.view.MyViewHolder;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
//    private static final int WITH_IMAGE = 1;
//    private static final int TEXT_ONLY = 2;

    private CityStorage cityStorage;

    public MyAdapter(CityStorage cityStorage) {
        this.cityStorage = cityStorage;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = null;
//        if(viewType == TEXT_ONLY){
            linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.mylayout, parent,false);
//        }else if(viewType == WITH_IMAGE){
//            linearLayout = linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).
//                    inflate(R.layout.mylayout2, parent,false);
//
//        }

        return new MyViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(cityStorage.list.get(position));
    }

    @Override
    public int getItemCount() {
        return cityStorage.list.size();
    }

//    @Override
//    public int getItemViewType(int position){
//        if(position % 2 == 0){
//            return TEXT_ONLY;
//        }else{
//            return WITH_IMAGE;
//        }
//    }


}
