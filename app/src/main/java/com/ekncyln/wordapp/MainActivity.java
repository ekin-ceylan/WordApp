package com.ekncyln.wordapp;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

// Liste Sayfası
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvSet;
    private FloatingActionButton btnAddSet;

    private ArrayList<Set> sets;
    private SetCardAdapter setCardAdapter;
    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddSet = findViewById(R.id.btnAddSet);
        rcvSet = findViewById(R.id.rcvSet);

        rcvSet.setHasFixedSize(true);
        rcvSet.setLayoutManager(new LinearLayoutManager(this));

        btnAddSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "eklendi", Toast.LENGTH_LONG).show();

                Intent intentSet = new Intent(MainActivity.this, SetActivity.class);
                intentSet.putExtra("setId", "Yeni");
                startActivity(intentSet);
                /*cardSets.add(new CardSet(){{
                    Title = "Verb";
                    Cards = new ArrayList<Card>();
                }});

                setCardAdapter.notifyDataSetChanged();*/
            }
        });

        store = (Store) getApplicationContext();
        setCardAdapter = new SetCardAdapter(this, store.Sets);
        rcvSet.setAdapter(setCardAdapter);
    }
}