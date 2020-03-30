package com.mariailieva.recycleviewdemo3.storage;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mariailieva.recycleviewdemo3.MainActivity;
import com.mariailieva.recycleviewdemo3.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityStorage {

    public static List<City> list = new ArrayList<>();
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static MainActivity mainActivity;
    private static DocumentSnapshot lastVisible;
    private static int queryLimit = 5;
    private final static String path = "cities";

    public static void init(MainActivity activity){
        mainActivity = activity;
        getInitialNotes();

    }

    private static void getInitialNotes(){
        list.clear();
        addOneTimeQuery(db.collection(path).orderBy("name").limit(queryLimit));
    }

    public static void getNextCities() {
        addOneTimeQuery(db.collection(path).orderBy("name").startAfter(lastVisible).limit(queryLimit));
    }

    private static void addOneTimeQuery(Query query){
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(DocumentSnapshot snap : task.getResult()){
                        list.add(new City(snap.get("name").toString(), snap.get("image").toString()));
                    }
                    mainActivity.notifyAdapter();
                    if(task.getResult().size() > 0) {
                        lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);
                    }
                    if(task.getResult().size() < queryLimit){
                        mainActivity.setIsLastItemReached(true);
                    }
                }
            }
        });
    }



//    public CityStorage(){
//        list.add(new City("Copenhagen","copenhagen"));
//        list.add(new City("Prague","prague"));
//        list.add(new City("Paris","paris"));
//        list.add(new City("New York","newyork"));
//    }
}
