package com.vento.googleafricadeveloperscholarship.activitys;

import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.vento.googleafricadeveloperscholarship.R;
import com.vento.googleafricadeveloperscholarship.activitys.submit.SubmitActivity;
import com.vento.googleafricadeveloperscholarship.databinding.ActivityMainBinding;
import com.vento.googleafricadeveloperscholarship.ui.learningLeaders.LearningLeadersFragment;
import com.vento.googleafricadeveloperscholarship.ui.skillIQLeaders.SkillIQLeadersFragment;
import com.vento.googleafricadeveloperscholarship.utils.base.BaseActivity;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    FragmentPagerItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        prepareTabsLayout();

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });

    }

    public void prepareTabsLayout() {
        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add(R.string.learning_leaders_title, LearningLeadersFragment.class)
                        .add(R.string.skill_iq_leaders_title, SkillIQLeadersFragment.class)
                        .create());


        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setViewPager(binding.viewPager);


        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                binding.viewPager.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }
}