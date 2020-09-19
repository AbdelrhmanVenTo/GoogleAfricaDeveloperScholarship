package com.vento.googleafricadeveloperscholarship.ui.skillIQLeaders;

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
import com.vento.googleafricadeveloperscholarship.model.SkillIQLeadersResponse;

import java.util.List;

public class SkillIQLeadersAdapter extends RecyclerView.Adapter<SkillIQLeadersAdapter.ViewHolder> {

    List<SkillIQLeadersResponse> skillIQLeadersList;
    Context context;

    public SkillIQLeadersAdapter(List<SkillIQLeadersResponse> skillIQLeadersList, Context context) {
        this.skillIQLeadersList = skillIQLeadersList;
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
        SkillIQLeadersResponse skillIQLeadersResponse = skillIQLeadersList.get(position);
        viewHolder.itemBinding.learnerDetailText.setText(skillIQLeadersResponse.getScore()+" Skill IQ score, "+skillIQLeadersResponse.getCountry());
        viewHolder.itemBinding.learnerNameText.setText(skillIQLeadersResponse.getName());
        Glide
                .with(context)
                .load(skillIQLeadersResponse.getBadgeUrl())
                .into(viewHolder.itemBinding.learnerImage);
    }

    @Override
    public int getItemCount() {
        if (skillIQLeadersList == null)
            return 0;
        return skillIQLeadersList.size();
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
