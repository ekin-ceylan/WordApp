package com.ekncyln.wordapp.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.ekncyln.wordapp.R;
import com.ekncyln.wordapp.entities.Card;

import java.util.List;

public class TestCardAdapter extends ArrayAdapter {

    public List<Card> cards;
    Context context;
    int itemLayout;
    private AnimatorSet flipAnimator;

    public TestCardAdapter(List<Card> cards, int resource, Context context) {

        super(context, resource, cards);
        flipAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_animator);

        this.cards = cards;
        this.context = context;
        this.itemLayout = resource;
    }

    @Override
    public int getCount() {
        return this.cards.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.cards.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        }

        if (position == 0){
            ViewCompat.setElevation(convertView, 8);
        }

        TextView txtTestWord = convertView.findViewById(R.id.txtTestWord);
        TextView txtTestMeaning = convertView.findViewById(R.id.txtTestMeaning);
        TextView txtTestSample = convertView.findViewById(R.id.txtTestSample);

        Card card = this.cards.get(position);

        txtTestWord.setText(card.Word);
        txtTestMeaning.setText(card.Meaning);
        txtTestSample.setText(card.Sample);

        if (card.Sample != null){
            txtTestSample.setVisibility(View.VISIBLE);
        }

        return convertView;
    }
}
