package com.ekncyln.wordapp.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.entities.Set;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

    public void AddCard(Card card){
        Map<String, Object> cardHash = new HashMap<>();
        cardHash.put("AnswerCount", card.AnswerCount);
        cardHash.put("AskCount", card.AskCount);
        cardHash.put("Level", card.Level);
        cardHash.put("Meaning", card.Meaning);
        cardHash.put("Sample", card.Sample);
        cardHash.put("SetName", card.SetName);
        cardHash.put("Word", card.Word);

        Cards.add(cardHash)
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

    public Task<QuerySnapshot> GetSet(){
        return Sets.get();
    }

    public Task<QuerySnapshot> GetCards(String title){
        return Cards.whereEqualTo("SetName", title).get();
    }
}
