package com.example.chapter3.homework;

import java.util.ArrayList;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private ArrayAdapter<Item> adapterItems;
    private LottieAnimationView animationView;
    private ListView items;
    private TextView tv1;

    private OnItemSelectedListener listener;

    public interface OnItemSelectedListener {
        public void onItemSelected(Item i);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create arraylist from item fixtures
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1, items);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = view.findViewById(R.id.animation_view);
        animationView.playAnimation();
        items = view.findViewById(R.id.lvItems);
        items.setVisibility(View.GONE);
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
                long mShortAnimationDuration = 2000;
                animationView.animate().alpha(0).setDuration(mShortAnimationDuration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                animationView.setVisibility(View.GONE);
                            }
                        });

                items.setAlpha(0);
                items.setVisibility(View.VISIBLE);
                items.animate().alpha(1).setDuration(7000).setListener(null);
                ArrayList<Item> item = Item.getItems();
                items.setAdapter(new ArrayAdapter<Item>(getActivity(), android.R.layout.simple_list_item_1, item));
                items.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View item, int position,
                                            long rowId) {
                        // Retrieve item based on position
                        Item i = adapterItems.getItem(position);
                        // Fire selected event for item
                        listener.onItemSelected(i);
                    }
                });
            }
        }, 5000);
    }
}
