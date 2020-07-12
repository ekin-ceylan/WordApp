package com.ekncyln.wordapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekncyln.wordapp.R;
import com.ekncyln.wordapp.entities.Set;

import java.util.List;

// Setlerin bilgilerini gösteren cardView ile setleri bağlayan adaptör
public class SetCardAdapter extends RecyclerView.Adapter<SetCardView> {
    private Context ctx;  // konteks
    private List<Set> sets;  // veri kümesi

    public SetCardAdapter(Context ctx, List<Set> sets) {
        this.ctx = ctx;
        this.sets = sets;
    }

    @NonNull
    @Override
    public SetCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_set, parent, false);
        return new SetCardView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SetCardView holder, int position) {
        Set set = sets.get(position);
        int prog = set.GetExpertCount();
        int secondaryProg = set.GetLearningCount();
        int size = set.Cards.size();

        holder.txtTitle.setText(set.Title);
        holder.txtCount.setText(String.valueOf(size));
        holder.barExpert.setProgress(prog);
        holder.barExpert.setSecondaryProgress(prog + secondaryProg);
        holder.nbrSetId.setText(set.Key);
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }
}
