package com.example.eldercare.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.eldercare.DailyActivitiesActivity;
import com.example.eldercare.GroceryActivity;
import com.example.eldercare.MedicationsActivity;
import com.example.eldercare.OnlineShoppingActivity;
import com.example.eldercare.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;

    Button button1,button2,button3,button4;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container,false);
       button1=root.findViewById(R.id.button1);
       button2=root.findViewById(R.id.button2);
       button3=root.findViewById(R.id.button3);
       button4=root.findViewById(R.id.button4);
       button1.setOnClickListener(this);
       button2.setOnClickListener(this);
       button3.setOnClickListener(this);
       button4.setOnClickListener(this);
        return root;
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button1) {
            Intent intent = new Intent(getContext(), DailyActivitiesActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.button2) {
            Intent intent = new Intent(getContext(), MedicationsActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.button3) {
            Intent intent = new Intent(getContext(), GroceryActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.button4) {
            Intent intent = new Intent(getContext(), OnlineShoppingActivity.class);
            startActivity(intent);
        }
    }
}