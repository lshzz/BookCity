package com.asterism.fresk.ui.fragment;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.asterism.fresk.R;
import com.asterism.fresk.contract.IBookContract;
import com.asterism.fresk.ui.adapter.MyAdapter;
import com.asterism.fresk.ui.adapter.SelectedRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * 精选页面Fragment类，继承base基类且泛型为当前模块Presenter接口类型，并实现当前模块View接口
 *
 * @author lshzz
 * @email 528118879@qq.com
 * @date on 2019-10-2 11:18
 */

public class SelectedFragment extends BaseFragment<IBookContract.Presenter>
        implements IBookContract.View {


    Banner banner;
    @BindView(R.id.sl_recyclerview)
    RecyclerView recyclerView;

    private List<String> mDatas;
    private SelectedRecyclerAdapter recycleAdapter;
    private MyAdapter myAdapter;



    @Override
    protected int setLayoutId() {
        return R.layout.fragment_selected;
    }

    @Override
    protected IBookContract.Presenter setPresenter() {
        return null;
    }

    @Override
    protected void initialize() throws IOException {

        String[] sss=new String[5];
        initRecyclerView();
        String url1 = "http://pic.baike.soso.com/p/20090711/20090711101754-314944703.jpg";
        String url2 = "https://img-my.csdn.net/uploads/201407/26/1406383264_8243.jpg";
        String url3 = "https://img-my.csdn.net/uploads/201407/26/1406383248_3693.jpg";
        String url4 = "https://img-my.csdn.net/uploads/201407/26/1406383242_3127.jpg";
        String url5 = "https://img-my.csdn.net/uploads/201407/26/1406383166_3407.jpg";
        List<String> list = new ArrayList<>();
        list.add(url1);
        list.add(url2);
        list.add(url3);
        list.add(url4);
        list.add(url5);

for (int i=0;i<sss.length;i++)
{
    sss[i]=url1;
}
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_header,null);
        View header = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_header, null);
    //    banner=header.findViewById(R.id.banner1);
        /***
         * 调用MyBanner
//         */
//        banner.setImageLoader(new MyBanner());
//       // banner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,App .H/4));
//
//        /**
//         * 把集合中数据添加到banner中
//         */
//        banner.setImages(list);
//
//        banner.start();//执行



//        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        myAdapter = new MyAdapter(mContext, sss);
////设置headerview
//        myAdapter.setHeaderView(banner);
//        recyclerView.setAdapter(myAdapter);


        recycleAdapter= new SelectedRecyclerAdapter(mContext , mDatas );
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter( recycleAdapter);
     //   recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        myAdapter= new MyAdapter(mContext , sss );
//        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
//        //设置布局管理器
//        recyclerView.setLayoutManager(layoutManager);
//        //设置为垂直布局，这也是默认的
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        //设置Adapter
//        recyclerView.setAdapter( myAdapter);
//      //  myAdapter.setHeaderView(banner);
//        //   recyclerView.setNestedScrollingEnabled(false);//禁止滑动
//        //设置增加或删除条目的动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initRecyclerView() {
        mDatas = new ArrayList<String>();
        for ( int i=0; i < 10; i++) {
            mDatas.add( "item"+i);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRemoving() {

    }

    @Override
    public void hideRemoving() {

    }


    public class MyBanner extends ImageLoader {

        public void displayImage(Context context, Object path, ImageView imageView) {

            // path表示当前要显示的图片的url，，imageView表示当前要显示图片的载体
            Glide.with(context).load((String) path).centerCrop().into(imageView);

        }
    }

}
