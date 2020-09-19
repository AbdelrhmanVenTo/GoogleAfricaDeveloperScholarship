package com.vento.googleafricadeveloperscholarship.network;

import com.vento.googleafricadeveloperscholarship.model.LearningLeadersResponse;
import com.vento.googleafricadeveloperscholarship.model.SkillIQLeadersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;



public interface APICalls {

    @GET("api/hours")
    Call<List<LearningLeadersResponse>> LearningLeaders();

    @GET("api/skilliq")
    Call<List<SkillIQLeadersResponse>> SkillIQLeaders();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Integer> submitProject(@Field("entry.1877115667")  String firstName ,
                                @Field("entry.2006916086")  String lastName,
                                @Field("entry.1824927963")  String emailAddress,
                                @Field("entry.284483984")   String linkToProject);







}
