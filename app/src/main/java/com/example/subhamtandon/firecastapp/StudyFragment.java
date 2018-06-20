package com.example.subhamtandon.firecastapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudyFragment extends Fragment {

    CardView anatomyCard, physiologyCard, biochemistryCard, pathalogyCard;

    public StudyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_study, container, false);

        anatomyCard =(CardView)view.findViewById(R.id.anatomyCard);
        physiologyCard=(CardView)view.findViewById(R.id.physiologyCard);
        biochemistryCard=(CardView)view.findViewById(R.id.biochemistryCard);
        pathalogyCard=(CardView)view.findViewById(R.id.pathologyCard);

        anatomyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });
        physiologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });
        biochemistryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });
        pathalogyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Link new fragment
                Toast.makeText(getActivity(), "Will be available soon", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener
                    FragmentTransaction ft= getFragmentManager().beginTransaction();
                    ft.replace(R.id.flMain, new HomeFragment());
                    ft.commit();
                    return true;
                }
                return false;
            }
        });
    }

}
