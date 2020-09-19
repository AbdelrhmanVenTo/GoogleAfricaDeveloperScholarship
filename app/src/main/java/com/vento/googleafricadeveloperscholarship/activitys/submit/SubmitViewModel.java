package com.vento.googleafricadeveloperscholarship.activitys.submit;

import android.app.Application;

import androidx.annotation.NonNull;

import com.vento.googleafricadeveloperscholarship.network.SubmissionApi;
import com.vento.googleafricadeveloperscholarship.utils.Constants;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseViewModel;
import com.vento.googleafricadeveloperscholarship.utils.base.MutableHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitViewModel extends BaseViewModel {

    public SubmitViewModel(@NonNull Application application) {
        super(application);
    }

    public void submitProject(String firstName , String lastName , String emailAddress , String linkToProject){
        SubmissionApi.getAPIs().submitProject(firstName, lastName, emailAddress, linkToProject).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){
                    getMutableLiveData().setValue(new
                            MutableHelper(Constants.submit_success,response.body()));
                }else {
                    getMutableLiveData().setValue(new
                            MutableHelper(Constants.submit_FAILED,"Don't Submit"));
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                getMutableLiveData().setValue(new MutableHelper(Constants
                        .NETWORK_CONNECTION_ERROR,null));
            }
        });
    }
}
