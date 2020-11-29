package com.ekncyln.wordapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekncyln.wordapp.R;
import com.ekncyln.wordapp.entities.Card;

import java.util.List;

// Kartların bilgilerini gösteren cardView ile kelimeleri bağlayan adaptör
public class WordCardAdapter extends RecyclerView.Adapter<WordCardView> {
    private List<Card> cards;

    public WordCardAdapter(List<Card> cards) {
        this.cards = cards;
    }

    @NonNull
    @Override
    public WordCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_word, parent, false);
        return new WordCardView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordCardView holder, int position) {
        Card card = cards.get(position);
        holder.txtMeaning.setText(card.Meaning);
        holder.txtWord.setText(card.Word);
        holder.txtSample.setText(card.Sample);
        holder.txtStatus.setText(card.getPerformance() + "%");
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
