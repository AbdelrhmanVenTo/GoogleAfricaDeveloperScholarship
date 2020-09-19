package com.vento.googleafricadeveloperscholarship.ui.learningLeaders;

import android.app.Application;

import androidx.annotation.NonNull;

import com.vento.googleafricadeveloperscholarship.model.LearningLeadersResponse;
import com.vento.googleafricadeveloperscholarship.network.ApiManager;
import com.vento.googleafricadeveloperscholarship.utils.Constants;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseViewModel;
import com.vento.googleafricadeveloperscholarship.utils.base.MutableHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersViewModel extends BaseViewModel {


    public LearningLeadersViewModel(@NonNull Application application) {
        super(application);
    }

    public void LearningLeadersAPI(){
        ApiManager.getAPIs().LearningLeaders().enqueue(new Callback<List<LearningLeadersResponse>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersResponse>> call, Response<List<LearningLeadersResponse>> response) {
                if (response.isSuccessful()){
                    getMutableLiveData().setValue(new
                            MutableHelper(Constants.LearningLeaders_success,response.body()));
                }else {
                    getMutableLiveData().setValue(new
                            MutableHelper(Constants.LearningLeaders_FAILED,"Can't Get Data"));
                }
            }

            @Override
            public void onFailure(Call<List<LearningLeadersResponse>> call, Throwable t) {
                getMutableLiveData().setValue(new MutableHelper(Constants
                        .NETWORK_CONNECTION_ERROR,null));
            }
        });
    }
}
