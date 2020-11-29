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
import com.ekncyln.wordapp.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SetActivity extends AppCompatActivity {

    private TextView txtTitleSet;
    private TextView txtCountSet;
    private RecyclerView rcvCard;
    private Button btnStudy;
    private WordCardAdapter wordCardAdapter;

    private String setId;
    private ArrayList<Card> cards;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        firebaseHelper = new FirebaseHelper();

        setId = getIntent().getStringExtra("setId");
        cards = new ArrayList<Card>();
        GetCards(setId);

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

    private void GetCards(String title){
        firebaseHelper.GetCards(title).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        cards.add(document.toObject(Card.class));
                    }

                    txtTitleSet.setText(setId);
                    txtCountSet.setText(String.valueOf(cards.size()));
                    wordCardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}