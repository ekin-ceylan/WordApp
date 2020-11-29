package com.ekncyln.wordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ekncyln.wordapp.adapters.SetCardAdapter;
import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.entities.Set;
import com.ekncyln.wordapp.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

// Liste Sayfası
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvSet;
    private FloatingActionButton btnAddSet;

    private SetCardAdapter setCardAdapter;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseHelper = new FirebaseHelper();
        Toast.makeText(MainActivity.this, "create", Toast.LENGTH_LONG).show();

        btnAddSet = findViewById(R.id.btnAddSet);
        rcvSet = findViewById(R.id.rcvSet);

        rcvSet.setHasFixedSize(true);
        rcvSet.setLayoutManager(new LinearLayoutManager(this));

        setCardAdapter = new SetCardAdapter(this, new ArrayList<Set>());
        rcvSet.setAdapter(setCardAdapter);

        GetSets();

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

    @Override
    protected void onRestart() {
        super.onRestart();
        setCardAdapter.sets= new ArrayList<Set>();
        setCardAdapter.notifyDataSetChanged();

        GetSets();
        Toast.makeText(MainActivity.this, "restart", Toast.LENGTH_LONG).show();
    }

    private void GetSets(){
        firebaseHelper.GetSets().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    int index = 0;

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Set set = document.toObject(Set.class);
                        setCardAdapter.sets.add(set);
                        GetCards(set, index++);
                    }

                    setCardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void GetCards(Set set, final int index){
        firebaseHelper.GetCards(set.Title).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        setCardAdapter.sets.get(index).Cards.add(document.toObject(Card.class));
                    }

                    setCardAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void InitializeDb() {
        Card overeat = new Card(){{
            Word = "overeat";
            Meaning = "gereğinden fazla yemek";
            Level = 7;
            Answers = new ArrayList<Boolean>(){{add(true); add(true); add(true); add(true);}};
            SetName = "Verb";
        }},
                punch = new Card(){{
                    Word = "punch";
                    Meaning = "aletle delmek";
                    Level = 4;
                    Answers = new ArrayList<Boolean>(){{add(false); add(false); add(true); add(true); add(true);}};
                    SetName = "Verb";
                }},
                excel = new Card(){{
                    Word = "excel";
                    Meaning = "bir işte üstün olmak";
                    Level = 7;
                    Sample = "She excelled in math.";
                    SetName = "Verb";
                }},
                plush = new Card(){{
                    Word = "plush";
                    Meaning = "pelüş";
                    Level = 7;
                    Answers = new ArrayList<Boolean>(){{add(true); add(true); add(true); add(true);}};
                    SetName = "Adjective";
                }},
                fatal = new Card(){{
                    Word = "fatal";
                    Meaning = "ölümcül";
                    Level = 6;
                    Answers = new ArrayList<Boolean>(){{add(false); add(true); add(true); add(true);}};
                    SetName = "Adjective";
                }},
                purport = new Card(){{
                    Word = "purport";
                    Meaning = "iddia etmek";
                    Level = 7;
                    Answers = new ArrayList<Boolean>(){{add(false); add(true); add(true); add(true);}};
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

        firebaseHelper.PutCard(overeat);
        firebaseHelper.PutCard(punch);
        firebaseHelper.PutCard(excel);
        firebaseHelper.PutCard(plush);
        firebaseHelper.PutCard(fatal);
        firebaseHelper.PutCard(purport);
        firebaseHelper.PutCard(withstand);
        firebaseHelper.PutCard(breakApart);
        firebaseHelper.PutCard(prefer);
    }
}