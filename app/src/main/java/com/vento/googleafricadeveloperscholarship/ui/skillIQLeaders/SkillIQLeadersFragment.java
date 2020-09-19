package com.vento.googleafricadeveloperscholarship.ui.skillIQLeaders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vento.googleafricadeveloperscholarship.R;
import com.vento.googleafricadeveloperscholarship.databinding.FragmentSkillIQLeadersBinding;
import com.vento.googleafricadeveloperscholarship.model.LearningLeadersResponse;
import com.vento.googleafricadeveloperscholarship.model.SkillIQLeadersResponse;
import com.vento.googleafricadeveloperscholarship.ui.learningLeaders.LearningLeadersAdapter;
import com.vento.googleafricadeveloperscholarship.ui.learningLeaders.LearningLeadersViewModel;
import com.vento.googleafricadeveloperscholarship.utils.Constants;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseActivity;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseFragment;
import com.vento.googleafricadeveloperscholarship.utils.base.MutableHelper;

import java.util.List;

public class SkillIQLeadersFragment extends BaseFragment {

    Context context;
    View rootView;
    FragmentSkillIQLeadersBinding binding;
    SkillIQLeadersViewModel viewModel;
    SkillIQLeadersAdapter adapter;
    List<SkillIQLeadersResponse> skillIQLeadersResponses;


    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context ;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil
                .inflate(inflater, R.layout.fragment_skill_i_q_leaders, container, false);
        init();

        return rootView;
    }
    private void init(){
        viewModel = new ViewModelProvider(this).get(SkillIQLeadersViewModel.class);
        binding.setLifecycleOwner(this);
        rootView = binding.getRoot();
    }

    public void initView() {
        adapter = new SkillIQLeadersAdapter(null,activity);
        binding.skillIqLeadersRecyclerView.
                setLayoutManager(new LinearLayoutManager(activity));
        binding.skillIqLeadersRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        viewModel.SkillIQLeadersAPI();
        onClickListener();
    }

    private void onClickListener (){
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<MutableHelper>() {
            @Override
            public void onChanged(MutableHelper mutableHelper) {
                if (mutableHelper.key== Constants.SkillIQLeadersAPI_FAILED){
                    Toast.makeText(activity, mutableHelper.value.toString(), Toast.LENGTH_SHORT).show();

                }else if (mutableHelper.key==Constants.SkillIQLeadersAPI_success){
                    skillIQLeadersResponses = (List<SkillIQLeadersResponse>) mutableHelper.value;
                    adapter = new SkillIQLeadersAdapter(skillIQLeadersResponses,context);
                    binding.skillIqLeadersRecyclerView.setAdapter(adapter);
                }else if (mutableHelper.key==Constants.NETWORK_CONNECTION_ERROR){
                    Toast.makeText(activity, "NETWORK_CONNECTION_ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}