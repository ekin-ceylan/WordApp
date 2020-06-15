package com.ekncyln.wordapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekncyln.wordapp.R;
import com.ekncyln.wordapp.entities.CardSet;

import java.util.List;

// Setlerin bilgilerini gösteren cardView ile setleri bağlayan adaptör
public class SetCardAdapter extends RecyclerView.Adapter<SetCardView> {
    private Context ctx;  // konteks
    private List<CardSet> cardSets;  // veri kümesi

    public SetCardAdapter(Context ctx, List<CardSet> cardSets) {
        this.ctx = ctx;
        this.cardSets = cardSets;
    }

    @NonNull
    @Override
    public SetCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_set, parent, false);
        return new SetCardView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SetCardView holder, int position) {
        CardSet cardSet = cardSets.get(position);
        int prog = cardSet.GetExpertCount();
        int secondaryProg = cardSet.GetLearningCount();
        int size = cardSet.Cards.size();

        holder.txtTitle.setText(cardSet.Title);
        holder.txtCount.setText(String.valueOf(size));
        holder.barExpert.setProgress(prog);
        holder.barExpert.setSecondaryProgress(prog + secondaryProg);

    }

    @Override
    public int getItemCount() {
        return cardSets.size();
    }
}
