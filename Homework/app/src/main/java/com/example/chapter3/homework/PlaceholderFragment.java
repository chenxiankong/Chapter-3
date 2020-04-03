package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private ListView lv_ex3;//列表listview控制变量
    private LottieAnimationView animationView_ex3;//lottie动画控制变量
    private ObjectAnimator animator_1;//控制lottie动画效果
    private ObjectAnimator animator_2;//控制列表动画效果
    AnimatorSet animatorSet;
    private String[] data = { "Item 1", "Item 2", "Item 3", "Item 4"};//列表数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view=inflater.inflate(R.layout.fragment_placeholder, container, false);
        //lottie动画控件绑定
        animationView_ex3 = view.findViewById(R.id.animation_view_ex3);
        animationView_ex3.playAnimation();
        //列表控件绑定初始化
        lv_ex3=(ListView)view.findViewById(R.id.lv_ex3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, data);
        lv_ex3.setAdapter(adapter);
        lv_ex3.setAlpha(0.0f);//设置透明度为0表示开始时隐藏

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override

            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                //设置lottie动画效果
                animator_1=ObjectAnimator.ofFloat(animationView_ex3,"alpha",1.0f,0.0f);
                animator_1.setDuration(1000);
                //设置列表动画效果
                animator_2=ObjectAnimator.ofFloat(lv_ex3,"alpha",0.0f,1.0f);
                animator_2.setDuration(1000);
                animatorSet=new AnimatorSet();

                animatorSet.playTogether(animator_1,animator_2);
                animatorSet.start();
                animationView_ex3.pauseAnimation();
            }
        }, 5000);
    }
}
