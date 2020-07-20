package com.dev.kd1412.timtrosv.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.UtilitiesItemBinding;
import com.dev.kd1412.timtrosv.model.Utilities;

import java.util.ArrayList;

public class UtilitiesAdapter extends RecyclerView.Adapter<UtilitiesAdapter.UtilitiesVH> {

    private ArrayList<Utilities> utilitiesList;
    private UtilitiesItemBinding utilitiesItemBinding;
    private Utilities utilities;

    public UtilitiesAdapter(ArrayList utilitiesList) {
        this.utilitiesList = utilitiesList;
    }

    @NonNull
    @Override
    public UtilitiesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        utilitiesItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.utilities_item, parent, false);

        return new UtilitiesVH(utilitiesItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UtilitiesVH holder, int position) {
        utilities = utilitiesList.get(position);
        switch (utilities.getmUtilitiesName()) {
            case "wifi":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Wifi");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.wifi);
                break;
            case "wc":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Phòng vệ sinh");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.wc);
                break;
            case "refrigerator":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Tủ Lạnh");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.fridge);
                break;
            case "park":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Chỗ để xe");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.parking);
                break;
            case "free time":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Tự do");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.free_time);
                break;
            case "kitchen":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Phòng bếp");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.kitchen);
                break;
            case "washing machine":
                holder.utilitiesItemBinding.tvUtilitiesName.setText("Máy giặt");
                holder.utilitiesItemBinding.imgUtilities.setImageResource(R.drawable.washing_machine);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return utilitiesList.size();
    }


    public class UtilitiesVH extends RecyclerView.ViewHolder {
        private UtilitiesItemBinding utilitiesItemBinding;

        public UtilitiesVH(@NonNull UtilitiesItemBinding utilitiesItemBinding) {
            super(utilitiesItemBinding.getRoot());
            this.utilitiesItemBinding = utilitiesItemBinding;
        }
    }
}