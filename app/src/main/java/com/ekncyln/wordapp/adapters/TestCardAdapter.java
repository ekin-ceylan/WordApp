package com.ekncyln.wordapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ekncyln.wordapp.R;
import com.ekncyln.wordapp.entities.Card;

import java.util.List;

public class TestCardAdapter extends ArrayAdapter {

    List<Card> cards;
    Context context;
    int itemLayout;

    public TestCardAdapter(List<Card> cards, int resource, Context context) {

        super(context, resource, cards);

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

        TextView txtTestWord = convertView.findViewById(R.id.txtTestWord);
        TextView txtTestMeaning = convertView.findViewById(R.id.txtTestMeaning);
        TextView txtTestSample = convertView.findViewById(R.id.txtTestSample);

        Card card = this.cards.get(position);

        txtTestWord.setText(card.Word);
        txtTestMeaning.setText(card.Meaning);
        txtTestSample.setText(card.Sample);

        return convertView;
    }
}
