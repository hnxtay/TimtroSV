package com.dev.kd1412.timtrosv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kd1412.timtrosv.BR;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.RoomItemBinding;

class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {
    private RoomItemBinding binding;
    @NonNull
    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                ,R.layout.room_item,parent,false);
        return new SearchResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder {
        private RoomItemBinding binding;
        public SearchResultViewHolder(@NonNull RoomItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Object o){
            binding.setVariable(BR.room, o);
            binding.hasPendingBindings();
        }
    }
}
