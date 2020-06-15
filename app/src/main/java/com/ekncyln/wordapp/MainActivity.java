package com.ekncyln.wordapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ekncyln.wordapp.adapters.SetCardAdapter;
import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.entities.CardSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// Liste Sayfası
public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvSet;
    private ArrayList<CardSet> cardSets;
    private SetCardAdapter setCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvSet = findViewById(R.id.rcvSet);
        rcvSet.setHasFixedSize(true);

        rcvSet.setLayoutManager(new LinearLayoutManager(this));

        cardSets = new ArrayList<CardSet>();
        cardSets.add(new CardSet(){{
            Title = "Verb";
            Cards = new ArrayList<Card>();
        }});
        cardSets.add(new CardSet(){{
            Title = "Adverb";
            Cards = new ArrayList<Card>();
        }});

        setCardAdapter = new SetCardAdapter(this, cardSets);
        rcvSet.setAdapter(setCardAdapter);
    }
}