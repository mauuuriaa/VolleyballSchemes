package com.example.myapplication.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.domain.entity.Test;


import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    public interface OnTestClickListener {
        void onTestClick(Test test);
    }

    private List<Test> tests = new ArrayList<>();
    private OnTestClickListener listener;

    public TestAdapter(OnTestClickListener listener) {
        this.listener = listener;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests != null ? tests : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_test, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {
        Test test = tests.get(position);
        holder.bind(test, listener);
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    static class TestViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDescription;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_test_title);
            tvDescription = itemView.findViewById(R.id.tv_test_description);
        }

        public void bind(final Test test, final OnTestClickListener listener) {
            tvTitle.setText(test.getTitle());
            tvDescription.setText(test.getDescription());
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTestClick(test);
                }
            });
        }
    }
}
