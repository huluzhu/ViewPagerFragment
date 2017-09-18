package com.baway.hujiqiang.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.baway.hujiqiang.R;
import com.baway.hujiqiang.adapter.FragmentAdapter;
import com.baway.hujiqiang.fragment.Fragment1;
import com.baway.hujiqiang.fragment.Fragment2;
import com.baway.hujiqiang.fragment.Fragment3;
import com.baway.hujiqiang.utils.IsNetUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3;
    private List<Fragment> fragmentLists;
    private Fragment f1, f2, f3;
    private FragmentManager fragmentManager;
    private FragmentAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (IsNetUtil.isNetworkAvailable(this)) {
            setContentView(R.layout.activity_main);
            bindView();
            //初始化ViewPager
            initViewPager();
        } else {
            Toast.makeText(this, "没有网络", Toast.LENGTH_SHORT).show();
        }


    }

    private void bindView() {
        radioGroup = (RadioGroup) findViewById(R.id.rd_group);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        radioButton1 = (RadioButton) findViewById(R.id.r1);
        radioButton2 = (RadioButton) findViewById(R.id.r2);
        radioButton3 = (RadioButton) findViewById(R.id.r3);
        radioButton1.setChecked(true);
        radioGroup.setOnCheckedChangeListener(this);
    }

    private void initViewPager() {
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();

        fragmentLists = new ArrayList<Fragment>();
        fragmentLists.add(f1);
        fragmentLists.add(f2);
        fragmentLists.add(f3);
        //获取FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        //获取FragmentPageAdapter对象
        adapter = new FragmentAdapter(fragmentManager, fragmentLists);
        //设置Adapter，使ViewPager 与 Adapter 进行绑定
        viewPager.setAdapter(adapter);
        //设置ViewPager默认显示第一个View
        viewPager.setCurrentItem(0);
        //设置第一个RadioButton为默认选中状态
        //ViewPager页面切换监听
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.r1);
                        break;
                    case 1:
                        radioGroup.check(R.id.r2);
                        break;
                    case 2:
                        radioGroup.check(R.id.r3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.r1:
                //显示第一个Fragment并关闭动画效果
                viewPager.setCurrentItem(0, false);
                break;
            case R.id.r2:
                viewPager.setCurrentItem(1, false);
                break;
            case R.id.r3:
                viewPager.setCurrentItem(2, false);
                break;
        }
    }
}
