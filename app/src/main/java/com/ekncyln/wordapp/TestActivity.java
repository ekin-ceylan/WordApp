package com.ekncyln.wordapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.StackView;
import android.widget.TextView;

import com.ekncyln.wordapp.adapters.TestCardAdapter;
import com.ekncyln.wordapp.entities.Set;
import com.ekncyln.wordapp.entities.Store;

public class TestActivity extends AppCompatActivity {

    StackView stcViewCard;
    private Store store;
    private Set set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        int setId = getIntent().getIntExtra("setId", 0);
        store = (Store) getApplicationContext();
        set = store.Sets.get(setId);

        stcViewCard = findViewById(R.id.stcViewCard);
        TestCardAdapter testCardAdapter = new TestCardAdapter(set.Cards, R.layout.card_test, TestActivity.this);
        stcViewCard.setAdapter(testCardAdapter);


    }
}