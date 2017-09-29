package com.hu.test;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hu.test.adapter.MyMainFragmentAdapter;
import com.hu.test.service.LocalService;
import com.hu.test.service.RemoteService;
import com.hu.test.ui.book.BookFragment;
import com.hu.test.ui.drawer.CloudActivity;
import com.hu.test.ui.drawer.login.LoginActivity;
import com.hu.test.ui.drawer.message.MessageActivity;
import com.hu.test.ui.drawer.safetool.SafeActivity;
import com.hu.test.ui.drawer.store.StoreActivity;
import com.hu.test.ui.movie.MovieFragment;
import com.hu.test.ui.news.NewsFragment;
import com.hu.test.utils.PerfectClickListener;
import com.hu.test.utils.StatusBarUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,ViewPager.OnPageChangeListener{


    private DrawerLayout drawerLayout;
    private NavigationView slideMenuList;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NavigationView navView;
    private View includeView;
    private View viewStatus;
    private LinearLayout llAccount;
    private LinearLayout llExit;
    private LinearLayout llMessage;
    private LinearLayout llQuestion;
    private LinearLayout llSalf;
    private LinearLayout llSkin;
    private ImageView ivHead;
    private ImageView ivTitleBook;
    private ImageView ivTitleNews;
    private ImageView ivTitleMovie;
    private ViewPager viewPager;
    private LinearLayout llStore;
    private LinearLayout llFlashlight;
    private CameraManager cameraManager;
    private Camera m_Camera = null;// 声明Camera对象
    private boolean light = false;
    private LinearLayout llCloud;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找id
        initId();
        //初始化toolbar
        initToolBar();

        //初始化状态栏
        initStatusView();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        //初始化侧边栏
        initDrawerLayout();

        //设置悬浮菜单
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * 装订fragment
         */
        initFragmentList();

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        //启动服务
        this.startService(new Intent(this, LocalService.class));
        this.startService(new Intent(this, RemoteService.class));


        cameraManager = (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        //打开手电筒
        if(Build.VERSION.SDK_INT >21){
            //初始化相机管理对象
            try {
                String[] cameraIdList = cameraManager.getCameraIdList();
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }else{
            Toast.makeText(MainActivity.this, "手机版本过低", Toast.LENGTH_SHORT).show();
        }
    }

    private void initFragmentList() {
        ArrayList<Fragment>  fragmentArrayList =new ArrayList<>();

        fragmentArrayList.add(new NewsFragment());
        fragmentArrayList.add(new MovieFragment());
        fragmentArrayList.add(new BookFragment());




        MyMainFragmentAdapter myMainFragmentAdapter = new MyMainFragmentAdapter(getSupportFragmentManager(),fragmentArrayList);
        viewPager.setAdapter(myMainFragmentAdapter);

        //设置最大缓存页数
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(1);
        ivTitleMovie.setSelected(true);
        viewPager.addOnPageChangeListener(this);
    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 请求数据，头像，新消息的数量
     */
    private void initData() {

    }

    /**
     * 初始化侧边栏
     */
    private void initDrawerLayout() {
        navView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = navView.getHeaderView(0);
        llAccount = (LinearLayout) headerView.findViewById(R.id.ll_account);
        llStore = (LinearLayout) headerView.findViewById(R.id.ll_store);
        llExit = (LinearLayout) headerView.findViewById(R.id.ll_exit);
        llMessage = (LinearLayout) headerView.findViewById(R.id.ll_message);
        llQuestion = (LinearLayout) headerView.findViewById(R.id.ll_question);
        llSalf = (LinearLayout) headerView.findViewById(R.id.ll_salf);
        llSkin = (LinearLayout) headerView.findViewById(R.id.ll_skin);
        ivHead = (ImageView) headerView.findViewById(R.id.iv_head);
        llFlashlight = (LinearLayout) headerView.findViewById(R.id.ll_flashlight);
        llCloud = (LinearLayout) headerView.findViewById(R.id.ll_cloud);

        ivHead.setOnClickListener(listener);
        llAccount.setOnClickListener(listener);
        llStore.setOnClickListener(listener);
        llExit.setOnClickListener(listener);
        llMessage.setOnClickListener(listener);
        llQuestion.setOnClickListener(listener);
        llSalf.setOnClickListener(listener);
        llSkin.setOnClickListener(listener);
        llFlashlight.setOnClickListener(listener);
        llCloud.setOnClickListener(listener);
    }

    /**
     * 自定义的接口点击事件
     */
    private PerfectClickListener listener = new PerfectClickListener() {



        @Override
        protected void onNoDoubleClick(final View view) {
            drawerLayout.closeDrawer(GravityCompat.START);
            //加上延迟，使抽屉关闭动画完成后在进行点击事件判断
            drawerLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent();
                    switch (view.getId()) {
                        case R.id.ll_message://我的消息
                            intent.setClass(MainActivity.this, MessageActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.ll_account://账号管理
                            //initTiao(;
                            intent.setClass(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.ll_question://问题反馈
                            break;
                        case R.id.ll_salf://安全设置
                            intent.setClass(MainActivity.this, SafeActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.ll_skin://皮肤更换
                            break;
                        case R.id.ll_store://商城
                            intent.setClass(MainActivity.this, StoreActivity.class);
                            startActivity(intent);
                            break;
                        case R.id.ll_exit://应用退出
                            lightSwitch(true);
                            break;

                        case R.id.ll_flashlight://打开手电
                            //打开手电筒
                           lightSwitch(false);
                            break;

                        case R.id.ll_cloud://打开手电
                            intent.setClass(MainActivity.this, CloudActivity.class);
                            startActivity(intent);
                            break;
                      /*  case R.id.iv_head://个人头像
                            break;*/
                        default:
                            break;
                    }
                }
            }, 250);
        }
    };

    /**
     * 手电筒控制方法
     *
     * @param lightStatus
     * @return
     */
    private void lightSwitch(final boolean lightStatus) {
        if (lightStatus) { // 关闭手电筒
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {

                    cameraManager.setTorchMode("0", false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                if (m_Camera != null) {
                    m_Camera.stopPreview();
                    m_Camera.release();
                    m_Camera = null;
                }
            }
        } else { // 打开手电筒
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                try {
                    cameraManager.setTorchMode("0", true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                final PackageManager pm = getPackageManager();
                final FeatureInfo[] features = pm.getSystemAvailableFeatures();
                for (final FeatureInfo f : features) {
                    if (PackageManager.FEATURE_CAMERA_FLASH.equals(f.name)) { // 判断设备是否支持闪光灯
                        if (null == m_Camera) {
                            m_Camera = Camera.open();
                        }
                        final Camera.Parameters parameters = m_Camera.getParameters();
                        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        m_Camera.setParameters(parameters);
                        m_Camera.startPreview();

                    }
                }

            }
        }
    }


    private void initTiao() {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MessageActivity.class);
        startActivity(intent);
    }


    /**
     * 通过mainBind找id
     */
    private void initId() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        viewStatus = findViewById(R.id.view_status);
        viewPager = (ViewPager) findViewById(R.id.vp_content);

        ivTitleBook = (ImageView) findViewById(R.id.iv_title_book);
        ivTitleNews = (ImageView) findViewById(R.id.iv_title_gank);
        ivTitleMovie = (ImageView) findViewById(R.id.iv_title_movie);

        ivTitleBook.setOnClickListener(this);
        ivTitleNews.setOnClickListener(this);
        ivTitleMovie.setOnClickListener(this);
    }

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = viewStatus.getLayoutParams();
        //获取系统状态栏的高度设置给重写覆盖状态栏的高度
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        viewStatus.setLayoutParams(layoutParams);

        //设置状态栏的颜色
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(MainActivity.this, drawerLayout,
                getResources().getColor(R.color.colorPrimaryDark));
    }

    /**
     * 返回按钮去关闭菜单
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                // 不退出程序，进入后台
                moveTaskToBack(true);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View view) {
        Log.e("TAG",viewPager.getCurrentItem()+"");
        switch (view.getId()){
            case R.id.iv_title_gank://新闻栏
                if(viewPager.getCurrentItem() != 0){//避免重复点击
                    ivTitleNews.setSelected(true);
                    ivTitleMovie.setSelected(false);
                    ivTitleBook.setSelected(false);
                    viewPager.setCurrentItem(0);
                }
                break;
            case R.id.iv_title_movie://电影栏
                if(viewPager.getCurrentItem() != 1){
                    ivTitleNews.setSelected(false);
                    ivTitleMovie.setSelected(true);
                    ivTitleBook.setSelected(false);
                    viewPager.setCurrentItem(1);
                }
                break;
            case R.id.iv_title_book://书籍栏
                if(viewPager.getCurrentItem() != 2){
                    ivTitleNews.setSelected(false);
                    ivTitleMovie.setSelected(false);
                    ivTitleBook.setSelected(true);
                    viewPager.setCurrentItem(2);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 监听viewpager的切换
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                ivTitleNews.setSelected(true);
                ivTitleMovie.setSelected(false);
                ivTitleBook.setSelected(false);
                break;
            case 1:
                ivTitleNews.setSelected(false);
                ivTitleMovie.setSelected(true);
                ivTitleBook.setSelected(false);
                break;
            case 2:
                ivTitleNews.setSelected(false);
                ivTitleMovie.setSelected(false);
                ivTitleBook.setSelected(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
