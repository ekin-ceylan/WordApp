package com.ekncyln.wordapp.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ekncyln.wordapp.MainActivity;
import com.ekncyln.wordapp.R;
import com.ekncyln.wordapp.SetActivity;

// Setlerin bilgilerini gösteren cardView
public class SetCardView extends RecyclerView.ViewHolder {
    public TextView txtTitle;
    public CardView crdSet;
    public TextView txtCount;
    public ProgressBar barExpert;
    public TextView nbrSetId;

    public SetCardView(@NonNull View itemView) {
        super(itemView);

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtCount = itemView.findViewById(R.id.txtCount);
        crdSet = itemView.findViewById(R.id.crdSet);
        barExpert = itemView.findViewById(R.id.barExpert);
        nbrSetId = itemView.findViewById(R.id.nbrSetId);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSet = new Intent(v.getContext(), SetActivity.class);

                intentSet.putExtra("setId", Integer.parseInt(nbrSetId.getText().toString()));

                Toast.makeText(v.getContext(), nbrSetId.getText(), Toast.LENGTH_LONG).show();

                v.getContext().startActivity(intentSet);
            }
        });
    }
}
