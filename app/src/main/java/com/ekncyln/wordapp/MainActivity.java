package com.ekncyln.wordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ekncyln.wordapp.adapters.SetCardAdapter;
import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.entities.Set;
import com.ekncyln.wordapp.entities.Store;
import com.ekncyln.wordapp.helpers.FirebaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// Liste Sayfası
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvSet;
    private FloatingActionButton btnAddSet;

    private ArrayList<Set> sets;
    private SetCardAdapter setCardAdapter;
    private Store store;

    private FirebaseDatabase firebaseWordAppDb;
    private DatabaseReference cardsRef;
    private DatabaseReference setsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseHelper helper = new FirebaseHelper();

        this.firebaseWordAppDb = FirebaseDatabase.getInstance();
        this.sets = new ArrayList<Set>();

        this.GetSets();

        btnAddSet = findViewById(R.id.btnAddSet);
        rcvSet = findViewById(R.id.rcvSet);

        rcvSet.setHasFixedSize(true);
        rcvSet.setLayoutManager(new LinearLayoutManager(this));

        //store = (Store) getApplicationContext();
        setCardAdapter = new SetCardAdapter(this, this.sets);
        rcvSet.setAdapter(setCardAdapter);

        btnAddSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "eklendi", Toast.LENGTH_LONG).show();

                Intent intentSet = new Intent(MainActivity.this, SetActivity.class);
                intentSet.putExtra("setId", "Yeni");
                startActivity(intentSet);
            }
        });
    }

    private void GetSets(){
        this.setsRef = firebaseWordAppDb.getReference("Sets");

        this.setsRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Set set = d.getValue(Set.class);
                    set.Key = d.getKey();

                    sets.add(set);
                    GetCards(set);
                }

                setCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void GetCards(final Set set){
        this.cardsRef = firebaseWordAppDb.getReference("Cards");
        Query query = this.cardsRef.orderByChild("SetName").equalTo(set.Title);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Card card = d.getValue(Card.class);
                    card.Key = d.getKey();

                    set.Cards.add(card);
                }

                setCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void InitializeDb() {
        Card overeat = new Card(){{
            Word = "overeat";
            Meaning = "gereğinden fazla yemek";
            Level = 7;
            AskCount = 4;
            AnswerCount = 4;
            SetName = "Verb";
        }},
                punch = new Card(){{
                    Word = "punch";
                    Meaning = "aletle delmek";
                    Level = 4;
                    AskCount = 5;
                    AnswerCount = 3;
                    SetName = "Verb";
                }},
                excel = new Card(){{
                    Word = "excel";
                    Meaning = "bir işte üstün olmak";
                    Level = 7;
                    AskCount = 0;
                    AnswerCount = 0;
                    Sample = "She excelled in math.";
                    SetName = "Verb";
                }},
                plush = new Card(){{
                    Word = "plush";
                    Meaning = "pelüş";
                    Level = 7;
                    AskCount = 4;
                    AnswerCount = 4;
                    SetName = "Adjective";
                }},
                fatal = new Card(){{
                    Word = "fatal";
                    Meaning = "ölümcül";
                    Level = 6;
                    AskCount = 4;
                    AnswerCount = 3;
                    SetName = "Adjective";
                }},
                purport = new Card(){{
                    Word = "purport";
                    Meaning = "iddia etmek";
                    Level = 7;
                    AskCount = 4;
                    AnswerCount = 3;
                    SetName = "Verb";
                }},
                withstand = new Card(){{
                    Word = "withstand";
                    Meaning = "üstesinden gelmek";
                    Level = 7;
                    SetName = "Verb";
                }},
                breakApart = new Card(){{
                    Word = "break apart";
                    Meaning = "parçalara ayırmak";
                    Level = 7;
                    SetName = "Verb";
                }},
                prefer = new Card(){{
                    Word = "prefer";
                    Meaning = "bir şeyi veya bir kimseyi diğerine tercih etmek";
                    Level = 3;
                    Sample = "Some people prefer camping to staying in hotels.";
                    SetName = "Verb";
                }};

        FirebaseHelper fhelp = new FirebaseHelper();
    }
}