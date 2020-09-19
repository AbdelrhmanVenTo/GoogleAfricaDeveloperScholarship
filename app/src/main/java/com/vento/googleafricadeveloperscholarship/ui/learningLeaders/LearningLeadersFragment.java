package com.vento.googleafricadeveloperscholarship.ui.learningLeaders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vento.googleafricadeveloperscholarship.R;
import com.vento.googleafricadeveloperscholarship.databinding.FragmentLearningLeadersBinding;
import com.vento.googleafricadeveloperscholarship.model.LearningLeadersResponse;
import com.vento.googleafricadeveloperscholarship.utils.Constants;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseActivity;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseFragment;
import com.vento.googleafricadeveloperscholarship.utils.base.MutableHelper;

import java.util.ArrayList;
import java.util.List;

public class LearningLeadersFragment extends BaseFragment {

    Context context;
    View rootView;
    FragmentLearningLeadersBinding binding;
    LearningLeadersViewModel viewModel;
    List<LearningLeadersResponse>learningLeadersResponses;
    LearningLeadersAdapter adapter;


    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil
                .inflate(inflater, R.layout.fragment_learning_leaders, container, false);
        init();

        return rootView;
    }

    private void init(){
        viewModel = new ViewModelProvider(this).get(LearningLeadersViewModel.class);
        binding.setLifecycleOwner(this);
        rootView = binding.getRoot();
    }

    public void initView() {
        adapter = new LearningLeadersAdapter(null,activity);
        binding.learningLeadersRecyclerView.
                setLayoutManager(new LinearLayoutManager(activity));
        binding.learningLeadersRecyclerView.setAdapter(adapter);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        viewModel.LearningLeadersAPI();
        onClickListener();
    }

    private void onClickListener (){
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<MutableHelper>() {
            @Override
            public void onChanged(MutableHelper mutableHelper) {
                if (mutableHelper.key== Constants.LearningLeaders_FAILED){
                    Toast.makeText(activity, mutableHelper.value.toString(), Toast.LENGTH_SHORT).show();

                }else if (mutableHelper.key==Constants.LearningLeaders_success){
                    learningLeadersResponses = (List<LearningLeadersResponse>) mutableHelper.value;
                    adapter = new LearningLeadersAdapter(learningLeadersResponses,context);
                    binding.learningLeadersRecyclerView.setAdapter(adapter);
                }else if (mutableHelper.key==Constants.NETWORK_CONNECTION_ERROR){
                    Toast.makeText(activity, "NETWORK_CONNECTION_ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}