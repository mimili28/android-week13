package com.mariailieva.recycleviewdemo3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.AbsListView;

import com.mariailieva.recycleviewdemo3.adapter.MyAdapter;
import com.mariailieva.recycleviewdemo3.storage.CityStorage;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;;
    private MyAdapter myAdapter;
    private boolean isLastItemReached;
    private boolean isScrolling;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CityStorage.init((this));
        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(new CityStorage());
        recyclerView.setAdapter(myAdapter);
        setupScrollListener();
    }

    private void setupScrollListener(){
        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if(isScrolling && (firstVisibleItemPosition + visibleItemCount == totalItemCount) && !isLastItemReached){
                    isScrolling = false;
                    CityStorage.getNextCities();
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }
    public void notifyAdapter(){
        myAdapter.notifyDataSetChanged();
    }

    public void setIsLastItemReached(boolean b) {
        isLastItemReached = b;
    }
}
