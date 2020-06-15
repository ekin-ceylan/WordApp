package com.ekncyln.wordapp.adapters;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ekncyln.wordapp.R;

// Setlerin bilgilerini gösteren cardView
public class SetCardView extends RecyclerView.ViewHolder {
    public TextView txtTitle;
    public CardView crdSet;
    public TextView txtCount;
    public ProgressBar barExpert;

    public SetCardView(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtCount = itemView.findViewById(R.id.txtCount);
        crdSet = itemView.findViewById(R.id.crdSet);
        barExpert = itemView.findViewById(R.id.barExpert);
    }
}
