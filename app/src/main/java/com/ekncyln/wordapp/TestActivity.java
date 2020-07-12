package com.ekncyln.wordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ekncyln.wordapp.adapters.TestCardAdapter;
import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.entities.Set;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestActivity extends AppCompatActivity {

    private SwipeFlingAdapterView stcViewCard;
    private TextView txtWrong;
    private TextView txtCorrect;
    private int correct = 0;
    private int wrong = 0;

    private String setId;
    private ArrayList<Card> cards;
    TestCardAdapter testCardAdapter;

    private FirebaseDatabase firebaseWordAppDb;
    private DatabaseReference cardsRef;
    private DatabaseReference setsRef;

    private boolean testFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        txtWrong = findViewById(R.id.txtWrong);
        txtCorrect = findViewById(R.id.txtCorrect);

        this.firebaseWordAppDb = FirebaseDatabase.getInstance();
        cards = new ArrayList<Card>();

        setId = getIntent().getStringExtra("setId");
        GetSet(setId);

        stcViewCard = findViewById(R.id.stcViewCard);
        testCardAdapter = new TestCardAdapter(cards, R.layout.card_test, TestActivity.this);
        stcViewCard.setAdapter(testCardAdapter);

        stcViewCard.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                cards.remove(0);
                testCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Card card = (Card) o;
                UpdateAnswerCount(card.Key , card.AnswerCount , ++card.AskCount);
                wrong++;
                txtWrong.setText(String.valueOf(wrong));
                Toast.makeText(TestActivity.this, card.Word + " Soldan Çıktı", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRightCardExit(Object o) {
                Card card = (Card) o;
                UpdateAnswerCount(card.Key , ++card.AnswerCount , ++card.AskCount);
                correct++;
                txtCorrect.setText(String.valueOf(correct));
                Toast.makeText(TestActivity.this, card.Word + " Sağdan Çıktı", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

                Toast.makeText(TestActivity.this, "Bitiyor " + i, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

                View crd = stcViewCard.getSelectedView();
                TextView txtStudyAgain = crd.findViewById(R.id.txtStudyAgain);
                TextView txtGotIt = crd.findViewById(R.id.txtGotIt);
                txtStudyAgain.setAlpha(-scrollProgressPercent);
                txtGotIt.setAlpha(scrollProgressPercent);
                //Toast.makeText(TestActivity.this, scrollProgressPercent < 0 ? "Sola gidiyor" : "Sağa Gidiyor", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void UpdateAnswerCount(String key, int answer, int ask){
        Map<String, Object> data = new HashMap<>();

        data.put("AskCount", ask);
        data.put("AnswerCount", answer);

        cardsRef.child(key).updateChildren(data);
    }

    private void GetSet(String setId){
        this.setsRef = firebaseWordAppDb.getReference("Sets");
        Query query = this.setsRef.orderByKey().equalTo(setId);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Set set = d.getValue(Set.class);

                    GetCards(set.Title);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void GetCards(String title){
        this.cardsRef = firebaseWordAppDb.getReference("Cards");
        Query query = this.cardsRef.orderByChild("SetName").equalTo(title);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (testFlag)
                    return;

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Card card = d.getValue(Card.class);
                    card.Key = d.getKey();

                    cards.add(card);
                }

                testFlag = true;
                testCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}