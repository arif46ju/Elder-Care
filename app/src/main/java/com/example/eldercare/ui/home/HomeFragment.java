package com.example.eldercare.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.eldercare.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ImageView imageView;
    private TextView textView;
ScrollView scrollView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_home, container,false);

      imageView=root.findViewById(R.id.imageView);
      textView=root.findViewById(R.id.homeText);


        return root;
    }





}