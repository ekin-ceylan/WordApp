package com.ekncyln.wordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ekncyln.wordapp.adapters.WordCardAdapter;
import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.entities.Set;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {

    private TextView txtTitleSet;
    private TextView txtCountSet;
    private RecyclerView rcvCard;
    private Button btnStudy;
    private WordCardAdapter wordCardAdapter;

    private String setId;
    private ArrayList<Card> cards;

    private FirebaseDatabase firebaseWordAppDb;
    private DatabaseReference cardsRef;
    private DatabaseReference setsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        this.firebaseWordAppDb = FirebaseDatabase.getInstance();

        setId = getIntent().getStringExtra("setId");
        cards = new ArrayList<Card>();
        GetSet(setId);

        txtTitleSet = findViewById(R.id.txtTitleSet);
        txtCountSet = findViewById(R.id.txtCountSet);
        rcvCard = findViewById(R.id.rcvCard);
        btnStudy = findViewById(R.id.btnStudy);

        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSet = new Intent(v.getContext(), TestActivity.class);
                intentSet.putExtra("setId", setId);
                v.getContext().startActivity(intentSet);
            }
        });

        rcvCard.setHasFixedSize(true);
        rcvCard.setLayoutManager(new LinearLayoutManager(this));

        wordCardAdapter = new WordCardAdapter(cards);
        rcvCard.setAdapter(wordCardAdapter);
    }

    private void GetSet(String setId){
        this.setsRef = firebaseWordAppDb.getReference("Sets");
        Query query = this.setsRef.orderByKey().equalTo(setId);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Set set = d.getValue(Set.class);

                    txtTitleSet.setText(set.Title);
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

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Card card = d.getValue(Card.class);
                    card.Key = d.getKey();

                    cards.add(card);
                }

                txtCountSet.setText(String.valueOf(cards.size()));
                wordCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}