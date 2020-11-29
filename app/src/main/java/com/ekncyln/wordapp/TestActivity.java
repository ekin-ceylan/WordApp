package com.ekncyln.wordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ekncyln.wordapp.adapters.TestCardAdapter;
import com.ekncyln.wordapp.entities.Card;
import com.ekncyln.wordapp.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    private SwipeFlingAdapterView stcViewCard;
    private TextView txtWrong;
    private TextView txtCorrect;
    private Button btnRepeatDeck;
    private Button btnNewDeck;
    private int correct = 0;
    private int wrong = 0;
    private AnimatorSet flipAnimator;
    private boolean isBack = false;
    private boolean isLoaded = false;

    private String setId;
    private ArrayList<Card> tempCards;
    TestCardAdapter testCardAdapter;

    FirebaseHelper firebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        firebaseHelper = new FirebaseHelper();
        flipAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.flip_animator);

        txtWrong = findViewById(R.id.txtWrong);
        txtCorrect = findViewById(R.id.txtCorrect);
        btnRepeatDeck = findViewById(R.id.btnRepeatDeck);
        btnNewDeck = findViewById(R.id.btnNewDeck);

        btnRepeatDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FillCards(true);
            }
        });
        btnNewDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FillCards(false);
            }
        });

        tempCards = new ArrayList<Card>();

        setId = getIntent().getStringExtra("setId");
        FillCards(false);

        stcViewCard = findViewById(R.id.stcViewCard);
        testCardAdapter = new TestCardAdapter(new ArrayList<Card>(), R.layout.card_test, TestActivity.this);
        stcViewCard.setAdapter(testCardAdapter);

        stcViewCard.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                testCardAdapter.cards.remove(0);
                testCardAdapter.notifyDataSetChanged();
                isBack = false;
            }

            @Override
            public void onLeftCardExit(Object o) {
                Card card = (Card) o;
                card.Answers.add(false);
                firebaseHelper.PutCard(card);
                wrong++;
                txtWrong.setText(String.valueOf(wrong));
            }

            @Override
            public void onRightCardExit(Object o) {
                Card card = (Card) o;
                card.Answers.add(true);
                firebaseHelper.PutCard(card);
                correct++;
                txtCorrect.setText(String.valueOf(correct));
            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
                if(isLoaded && i == 0){
                    btnNewDeck.setVisibility(View.VISIBLE);
                    btnRepeatDeck.setVisibility(View.VISIBLE);
                }
                Toast.makeText(TestActivity.this, isLoaded + " " + i, Toast.LENGTH_LONG).show();

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

        stcViewCard.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(final int i, Object o) {
                View v = stcViewCard.getSelectedView();
                View lytBack = v.findViewById(R.id.lytBack);

                Float scale = getApplicationContext().getResources().getDisplayMetrics().density;
                v.setCameraDistance(8000 * scale);
                flipAnimator.setTarget(v);

                float[] values = isBack ? new float[] {1f, 0f} : new float[] {0f, 1f};

                isBack = !isBack;

                ObjectAnimator hideShowAnimator = ObjectAnimator.ofFloat(lytBack, "alpha", values);
                hideShowAnimator.setDuration(0);
                hideShowAnimator.setStartDelay(250);
                hideShowAnimator.start();

                flipAnimator.start();
                hideShowAnimator.start();
            }
        });
    }

    private void FillCards(boolean sameDeck){
        btnNewDeck.setVisibility(View.GONE);
        btnRepeatDeck.setVisibility(View.GONE);
        isLoaded = false;

        if (sameDeck){
            testCardAdapter.cards = (ArrayList<Card>) tempCards.clone();
            testCardAdapter.notifyDataSetChanged();
            isLoaded = true;
        }
        else{
            GetCards(setId);
        }

    }

    private void GetCards(String title){
        firebaseHelper.GetCards(title).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        testCardAdapter.cards.add(document.toObject(Card.class));
                        tempCards.add(document.toObject(Card.class));
                    }

                    testCardAdapter.notifyDataSetChanged();
                    isLoaded = true;
                }
            }
        });
    }

}