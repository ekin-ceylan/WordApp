package com.ekncyln.wordapp.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ekncyln.wordapp.entities.Card;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;

public class FirebaseHelper {
    // Access a Cloud Firestore instance from your Activity
    FirebaseFirestore DB;
    CollectionReference Sets;
    CollectionReference Cards;

    public FirebaseHelper() {
        DB = FirebaseFirestore.getInstance();
        Sets = DB.collection("Sets");
        Cards = DB.collection("Cards");
    }

    public void AddSet(String title){
        Map<String, Object> set = new HashMap<>();
        set.put("Title", title);

        Sets.add(set)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.e("id", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Başarısız", e.getMessage());
                    }
                });

    }

    public void PutCard(Card card){
        Map<String, Object> cardHash = new HashMap<>();
        cardHash.put("Answers", card.Answers);
        cardHash.put("Level", card.Level);
        cardHash.put("Meaning", card.Meaning);
        cardHash.put("Sample", card.Sample);
        cardHash.put("SetName", card.SetName);
        cardHash.put("Word", card.Word);

        Cards.document(card.Word).set(cardHash);
    }

    public Task<QuerySnapshot> GetSets(){
        return Sets.get(Source.SERVER);
    }

    public Task<DocumentSnapshot> GetSetByTitle(String title){
        return Sets.document(title).get();
    }

    public Task<QuerySnapshot> GetCards(String setName){
        return Cards.whereEqualTo("SetName", setName).get(Source.SERVER);
    }

    public Task<DocumentSnapshot> GetCardByTitle(String title){
        return Cards.document(title).get();
    }
}
