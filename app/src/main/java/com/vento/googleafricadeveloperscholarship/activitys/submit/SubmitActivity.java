package com.vento.googleafricadeveloperscholarship.activitys.submit;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vento.googleafricadeveloperscholarship.R;
import com.vento.googleafricadeveloperscholarship.activitys.MainActivity;
import com.vento.googleafricadeveloperscholarship.activitys.SplashScreenActivity;
import com.vento.googleafricadeveloperscholarship.databinding.ActivitySubmitBinding;
import com.vento.googleafricadeveloperscholarship.model.SkillIQLeadersResponse;
import com.vento.googleafricadeveloperscholarship.ui.skillIQLeaders.SkillIQLeadersAdapter;
import com.vento.googleafricadeveloperscholarship.ui.skillIQLeaders.SkillIQLeadersViewModel;
import com.vento.googleafricadeveloperscholarship.utils.Constants;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseActivity;
import com.vento.googleafricadeveloperscholarship.utils.base.MutableHelper;

import java.util.List;

public class SubmitActivity extends BaseActivity {

    ActivitySubmitBinding binding;
    SubmitViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
        viewModel = new ViewModelProvider(this).get(SubmitViewModel.class);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        onClickListener();

    }

    private void onClickListener (){
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.firstNameEditText.getText().toString().isEmpty()){
                    binding.firstNameEditText.setError("please insert");
                }else if (binding.lastNameEditText.getText().toString().isEmpty()){
                    binding.lastNameEditText.setError("please insert");
                }else if (binding.editTextEmail.getText().toString().isEmpty()){
                    binding.editTextEmail.setError("please insert");
                }else if (binding.editTextproLink.getText().toString().isEmpty()){
                    binding.editTextproLink.setError("please insert");
                }else {
                    Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();
                    showDialog();

                }
            }
        });

        viewModel.getMutableLiveData().observe(activity, new Observer<MutableHelper>() {
            @Override
            public void onChanged(MutableHelper mutableHelper) {
                if (mutableHelper.key== Constants.submit_FAILED){
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run(){
                            final Dialog dialog = new Dialog(activity);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setCancelable(false);
                            dialog.setContentView(R.layout.layout_submission_unsuccessful);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            final Window window = dialog.getWindow();
                            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            dialog.show();
                        }
                    }, 1000);
                }else if (mutableHelper.key==Constants.submit_success){
                    final Dialog dialog = new Dialog(activity);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.layout_submission_successful);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final Window window = dialog.getWindow();
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.show();
                    finish();
                }else if (mutableHelper.key==Constants.NETWORK_CONNECTION_ERROR){
                    Toast.makeText(activity, "NETWORK_CONNECTION_ERROR", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void showDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_submit_prompt);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);


        ImageButton cancelBTN = dialog.findViewById(R.id.cancel_button);
        Button acceptBTN = dialog.findViewById(R.id.confirm_submit_button);


        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        acceptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CAll API
                viewModel.submitProject(binding.firstNameEditText.getText().toString(),
                        binding.lastNameEditText.getText().toString(),
                        binding.editTextEmail.getText().toString(),
                        binding.editTextproLink.getText().toString());
            }
        });

        dialog.show();
    }
}