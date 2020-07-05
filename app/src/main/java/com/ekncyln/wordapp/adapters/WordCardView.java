package com.ekncyln.wordapp.adapters;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ekncyln.wordapp.R;

public class WordCardView extends RecyclerView.ViewHolder {
    public TextView txtWord;
    public TextView txtMeaning;

    public WordCardView(@NonNull View itemView) {
        super(itemView);

        txtMeaning = itemView.findViewById(R.id.txtMeaning);
        txtWord = itemView.findViewById(R.id.txtWord);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), txtWord.getText(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
