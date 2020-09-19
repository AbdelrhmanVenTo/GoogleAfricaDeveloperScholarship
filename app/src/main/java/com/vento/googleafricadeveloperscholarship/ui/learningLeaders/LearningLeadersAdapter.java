package com.vento.googleafricadeveloperscholarship.ui.learningLeaders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vento.googleafricadeveloperscholarship.R;
import com.vento.googleafricadeveloperscholarship.databinding.ResponseListItemBinding;
import com.vento.googleafricadeveloperscholarship.model.LearningLeadersResponse;

import java.util.List;

public class LearningLeadersAdapter extends RecyclerView.Adapter<LearningLeadersAdapter.ViewHolder> {

    List<LearningLeadersResponse> learningLeadersList;
    Context context;

    public LearningLeadersAdapter(List<LearningLeadersResponse> learningLeadersList, Context context) {
        this.learningLeadersList = learningLeadersList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.response_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        LearningLeadersResponse leadersResponse= learningLeadersList.get(position);
        viewHolder.itemBinding.learnerDetailText.setText(leadersResponse.getHours()+" Learning hours, "+leadersResponse.getCountry());
        viewHolder.itemBinding.learnerNameText.setText(leadersResponse.getName());
        Glide
                .with(context)
                .load(leadersResponse.getBadgeUrl())
                .into(viewHolder.itemBinding.learnerImage);

    }

    @Override
    public int getItemCount() {
        if (learningLeadersList == null)
            return 0;
        return learningLeadersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ResponseListItemBinding itemBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemBinding == null) {
                itemBinding = DataBindingUtil.bind(itemView);

            }
        }

    }


}
