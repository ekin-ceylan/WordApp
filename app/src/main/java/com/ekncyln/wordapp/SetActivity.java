package com.ekncyln.wordapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ekncyln.wordapp.adapters.SetCardAdapter;
import com.ekncyln.wordapp.adapters.WordCardAdapter;
import com.ekncyln.wordapp.entities.Set;
import com.ekncyln.wordapp.entities.Store;

public class SetActivity extends AppCompatActivity {

    private TextView txtTitleSet;
    private TextView txtCountSet;
    private RecyclerView rcvCard;
    private Button btnStudy;
    private WordCardAdapter wordCardAdapter;

    private Store store;
    private Set set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        final int setId = getIntent().getIntExtra("setId", 0);
        store = (Store) getApplicationContext();
        set = store.Sets.get(setId);

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

        txtCountSet.setText(String.valueOf(set.Cards.size()));
        txtTitleSet.setText(set.Title);

        rcvCard.setHasFixedSize(true);
        rcvCard.setLayoutManager(new LinearLayoutManager(this));

        wordCardAdapter = new WordCardAdapter(set.Cards);
        rcvCard.setAdapter(wordCardAdapter);
    }
}