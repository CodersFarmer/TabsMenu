package com.heima.yqz.tabmenuview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.heima.yqz.adapter.MenuAdapter24;
import com.heima.yqz.adapter.TabAdapter;
import com.heima.yqz.fragment.grofferTabFragment;
import com.heima.yqz.fragment.homeTabFragment;
import com.heima.yqz.fragment.newsTabFragment;
import com.heima.yqz.fragment.settingTabFragment;
import com.heima.yqz.fragment.smartTabFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 无法使用TabPagerIndicator的默认主题，因为无法把AppCompatActivity改成Activity。
 */
public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_news)
    RadioButton rbNews;
    @BindView(R.id.smart)
    RadioButton smart;
    @BindView(R.id.goffer)
    RadioButton goffer;
    @BindView(R.id.setting)
    RadioButton setting;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;
    private ViewPager mVp;
    private List<Fragment> fragments;
    private TabPageIndicator tabPageIndicator;
    private SlidingMenu slidingMenu;
    private ImageView mImageVage;
    private List<String> menus;
    private MenuAdapter24 myadapter;

    public void setNewsCenterMenuBeanList(List<String> menus) {
        this.menus = menus;
        myadapter.setNewsCenterMenuBeanList(menus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        mImageVage = (ImageView) findViewById(R.id.iv_pciasso);
        /**
         * 用picasso加载网络图片http://192.168.1.112:8080/tomcat.png
         * */
        Picasso.with(this).load("http://192.168.1.106:8080/tomcat.png").into(mImageVage);
        init();//初始化控件
        initVp();//初始化viewPager
        initSlidingMenu();//初始化框架侧拉菜单
        initRecylerView();
    }

    private void initRecylerView() {
        menus = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            menus.add("菜单" + i);
        }
        RecyclerView rvMenu = (RecyclerView) slidingMenu.findViewById(R.id.rv_news);
        rvMenu.setLayoutManager(new LinearLayoutManager(this));
        myadapter = new MenuAdapter24(this, menus);
        rvMenu.setAdapter(myadapter);
    }

    /**
     * 初始化框架侧拉菜单
     */
    private void initSlidingMenu() {
        //创建侧滑菜单
        slidingMenu = new SlidingMenu(this);
        //设置菜单从左边滑出
        slidingMenu.setMode(SlidingMenu.LEFT);
        /**
         * 设置菜单可以全屏滑出
         * slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
         * */
        // 距离左界面边距有10px左右的间隔，不可以全屏滑出
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//TODO:设置滑动监听
        //设置它滑出来的宽度
        slidingMenu.setBehindWidth(250);
        //把它加到自己的activity里面
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置侧滑菜单的布局
        slidingMenu.setMenu(R.layout.sm_view);
    }

    /**
     * 往fragment的集合里面添加数据，给viewPager设置适配器
     */
    private void initVp() {
        fragments = new ArrayList<>();
        fragments.add(new homeTabFragment());
        fragments.add(new newsTabFragment());
        fragments.add(new smartTabFragment());
        fragments.add(new grofferTabFragment());
        fragments.add(new settingTabFragment());
        //通过构造函数，传递fragment集合
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        //设置适配器
        mVp.setAdapter(adapter);
        //让TabPagerIndicator和mVp产生关系
        tabPageIndicator.setViewPager(mVp);
        /**
         * 给mVp设置监听，实现滑动mVp，切换Tab
         * */
        mVp.addOnPageChangeListener(this);
    }

    /**
     * 初始化控件
     */
    private void init() {
        tabPageIndicator = (TabPageIndicator) findViewById(R.id.tabPageIndicator);
        mVp = (ViewPager) findViewById(R.id.vp);
        /**
         * 给radioGroup设置监听器，实现点击radioButton切换viewPager的界面
         * */
        rgTab.setOnCheckedChangeListener(this);
    }

    /**
     * ViewGroup的监听
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int item = 0;
        switch (i) {
            case R.id.rb_home:
                item = 0;
                break;
            case R.id.rb_news:
                item = 1;
                break;
            case R.id.smart:
                item = 2;
                break;
            case R.id.goffer:
                item = 3;
                break;
            case R.id.setting:
                item = 4;
                break;
        }
        //mVp切换到对应的页面
        mVp.setCurrentItem(item);
        //定义一个接口，实现回调，不让viewPager在1-4的界面往右滑，出现slidingMenu
        //没用
        //mVp切换到对应的页面,不需要mVp页面切换有滑动的界面
        //mVp.setCurrentItem(item,false);
    }

    /**
     * viewPage的监听
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //滑动
    }

    @Override
    public void onPageSelected(int position) {
        //被选择
        switch (position) {
            case 0:
                //首页RadioButton被选中
                rbHome.setChecked(true);
                break;
            case 1:
                //新闻中心RadioButton被选中
                rbNews.setChecked(true);
                //没用
                slidingMenu.setClipChildren(false);
                break;
            case 2:
                //智慧中心RadioButton被选中
                smart.setChecked(true);
                break;
            case 3:
                //政务RadioButton被选中
                goffer.setChecked(true);
                break;
            case 4:
                //设置中心RadioButton被选中
                setting.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //滑动状态改变
    }

}
