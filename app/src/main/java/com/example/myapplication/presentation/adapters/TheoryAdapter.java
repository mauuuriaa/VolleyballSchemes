package com.example.myapplication.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.entity.Theory;

import java.util.ArrayList;
import java.util.List;

public class TheoryAdapter extends RecyclerView.Adapter<TheoryAdapter.TheoryViewHolder> {

    public interface OnTheoryClickListener {
        void onTheoryClick(Theory theory);
    }

    private List<Theory> theories = new ArrayList<>();
    private OnTheoryClickListener listener;

    public TheoryAdapter(OnTheoryClickListener listener) {
        this.listener = listener;
    }

    public void setTheories(List<Theory> theories) {
        this.theories = theories != null ? theories : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_theory, parent, false);
        return new TheoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheoryViewHolder holder, int position) {
        Theory theory = theories.get(position);
        holder.bind(theory, listener);
    }

    @Override
    public int getItemCount() {
        return theories.size();
    }

    static class TheoryViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvStatus;
        private ImageView ivImage;

        public TheoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_theory_title);
            tvStatus = itemView.findViewById(R.id.tv_theory_status);
            ivImage = itemView.findViewById(R.id.iv_theory_image);
        }

        public void bind(final Theory theory, final OnTheoryClickListener listener) {
            tvTitle.setText(theory.getTitle());
            tvStatus.setText(theory.isCompleted() ? "Изучено" : "Не изучено");
            tvStatus.setTextColor(
                    itemView.getResources().getColor(
                            theory.isCompleted() ? android.R.color.holo_green_dark : android.R.color.holo_red_dark
                    )
            );

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTheoryClick(theory);
                }
            });
        }
    }
}
