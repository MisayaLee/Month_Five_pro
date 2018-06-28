package com.example.misaya.month_five_pro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.misaya.month_five_pro.Adapter.InfoAdapter;
import com.example.misaya.month_five_pro.Fragment.OneFragment;
import com.example.misaya.month_five_pro.Fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> stringList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private ImageView main_iv;
    private TextView main_tv;
    private TabLayout main_tab;
    private ViewPager main_vp;
    OneFragment oneFragment = new OneFragment();
    public static CheckBox main_check;
    private Button main_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        stringList.add("商品");
        stringList.add("路线/旅游攻略");

        fragmentList.add(oneFragment);
        fragmentList.add(new TwoFragment());

        main_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return stringList.get(position);
            }
        });
        main_tab.setupWithViewPager(main_vp);
    }


    private void initView() {
        main_iv = (ImageView) findViewById(R.id.main_iv);
        main_tv = (TextView) findViewById(R.id.main_tv);
        main_tab = (TabLayout) findViewById(R.id.main_tab);
        main_vp = (ViewPager) findViewById(R.id.main_vp);
        main_iv.setOnClickListener(this);
        main_tv.setOnClickListener(this);
        main_check = (CheckBox) findViewById(R.id.main_check);
        main_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    InfoAdapter.iselected = true;
                    oneFragment.adapter.notifyDataSetChanged();
                    main_check.setText("取消全选");
                } else {
                    InfoAdapter.iselected=false;
                    main_check.setText("全选");
                    oneFragment.adapter.notifyDataSetChanged();
                }
            }
        });
        main_btn = (Button) findViewById(R.id.main_btn);
        main_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_iv:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("您确定退出吗");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.main_tv:

//                InfoAdapter.checkBox.setVisibility(View.VISIBLE);
                InfoAdapter.tag = true;
                oneFragment.adapter.notifyDataSetChanged();
                main_tv.setText("完成");

                break;
            case R.id.main_btn:
                oneFragment.adapter.notifyDataSetChanged();
                break;

        }
    }
}
