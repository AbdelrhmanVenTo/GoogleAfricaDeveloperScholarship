package com.vento.googleafricadeveloperscholarship.ui.skillIQLeaders;

import android.app.Application;

import androidx.annotation.NonNull;

import com.vento.googleafricadeveloperscholarship.model.SkillIQLeadersResponse;
import com.vento.googleafricadeveloperscholarship.network.ApiManager;
import com.vento.googleafricadeveloperscholarship.utils.Constants;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseViewModel;
import com.vento.googleafricadeveloperscholarship.utils.base.MutableHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersViewModel extends BaseViewModel {


    public SkillIQLeadersViewModel(@NonNull Application application) {
        super(application);
    }

    public void SkillIQLeadersAPI(){
        ApiManager.getAPIs().SkillIQLeaders().enqueue(new Callback<List<SkillIQLeadersResponse>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeadersResponse>> call, Response<List<SkillIQLeadersResponse>> response) {
                if (response.isSuccessful()){
                    getMutableLiveData().setValue(new
                            MutableHelper(Constants.SkillIQLeadersAPI_success,response.body()));
                }else {
                    getMutableLiveData().setValue(new
                            MutableHelper(Constants.SkillIQLeadersAPI_FAILED,"Can't Get Data"));
                }
            }

            @Override
            public void onFailure(Call<List<SkillIQLeadersResponse>> call, Throwable t) {
                getMutableLiveData().setValue(new MutableHelper(Constants
                        .NETWORK_CONNECTION_ERROR,null));
            }
        });
    }
}
