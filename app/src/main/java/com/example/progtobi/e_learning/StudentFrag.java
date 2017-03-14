package com.example.progtobi.e_learning;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.progtobi.e_learning.Adapters.StudentAdapter;
import com.example.progtobi.e_learning.Tasks.FetchStudentsBackgroundTask;
import com.example.progtobi.e_learning.Tasks.VolleyBackgroundTask;
import com.example.progtobi.e_learning.model._User;

import java.util.ArrayList;

/**
 * Created by PROG. TOBI on 10-Oct-16.
 */
public class StudentFrag extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter studentAdapter;
    String fetchstud_url = "http://elearningapp.eu5.org/fetchstudents.php";
    ArrayList<_User> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.student, container, false);
        FetchStudentsBackgroundTask fetchStudentsBackgroundTask = new FetchStudentsBackgroundTask(getActivity());
        fetchStudentsBackgroundTask.execute();


        /*recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerstud);
        //studentAdapter = new StudentAdapter(getActivity(), getData());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setAdapter(studentAdapter);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        VolleyBackgroundTask volleyBackgroundTask = new VolleyBackgroundTask(getActivity());
        arrayList= volleyBackgroundTask.getData();
        studentAdapter = new StudentAdapter(arrayList);
        recyclerView.setAdapter(studentAdapter);*/

        return rootView;
    }


}
