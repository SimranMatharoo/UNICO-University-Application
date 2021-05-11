package com.example.unico;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Evaluation2 extends Fragment {
    ListView lv,lvRoll;
    ArrayList<String> nameList,rollNoList, rollArr;
    ArrayAdapter ad,ad1;
    DatabaseReference dr;

    String year, rolll;


    int i=1;
    private StudentViewModel mViewModel;

    public static StudentFragment newInstance() {
        return new StudentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.student_fragment, container, false);


        dr= FirebaseDatabase.getInstance().getReference();

        year = getArguments().getString("year");

        Toast.makeText(getActivity(), "" + year, Toast.LENGTH_SHORT).show();

        lvRoll = v.findViewById(R.id.StudentsNameList);
        lv = v.findViewById(R.id.StudentsNameList2);

        nameList = new ArrayList<>();
        rollNoList = new ArrayList<>();
        rollArr=new ArrayList<>();


        retrieve_data();


        lvRoll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FormFragement f=new FormFragement();
                Bundle arg=new Bundle();
                arg.putString("year",year);
                String a= rollArr.get(position);
                arg.putString("rollNo",a);
                f.setArguments(arg);
                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.nav_host_fragment,f);
                fr.commit();
            }
        });
        return v;
    }

    public void retrieve_data()
    {
        Query q=dr.child(year+"Students");

        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    Map m= (Map) ds.getValue();

                    String name=(String) m.get("name");
                    String roll_no=(String) m.get("roll_no");
                    rolll=roll_no;

                    nameList.add(name);
                    rollNoList.add("     " + i + "." + "              " + roll_no);

                    rollArr.add(rolll);

                    ad = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, nameList);
                    ad1 = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, rollNoList);
                    lv.setAdapter(ad);
                    lvRoll.setAdapter(ad1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }





    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        // TODO: Use the ViewModel
    }



}