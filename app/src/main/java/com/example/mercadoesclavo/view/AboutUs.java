
package com.example.mercadoesclavo.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mercadoesclavo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUs extends Fragment {



    public AboutUs() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_about_us, container, false);


        return view;

    }




}
