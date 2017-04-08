package com.example.sudipto.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


import java.util.Map;


public class MainActivity extends AppCompatActivity {

    public Button mbutton;
    public Firebase mRootRef;
    public EditText mkey;
    public EditText mvalue;
    public TextView mshowView;
    public Firebase mRef;
    public Button showMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        mbutton = (Button)findViewById(R.id.add_data);
        mkey = (EditText)findViewById(R.id.key);
        mvalue = (EditText)findViewById(R.id.value);
        mRootRef = new Firebase("https://fireapp-e6531.firebaseio.com/Users");
        mshowView = (TextView)findViewById(R.id.showdata);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String key = mkey.getText().toString();
                String value = mvalue.getText().toString();

                Firebase ChildRef = mRootRef.child(key);
                ChildRef.setValue(value);
            }
        });



        mRef = new Firebase("https://fireapp-e6531.firebaseio.com/");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Map<String,String> map = dataSnapshot.getValue(Map.class);

                String name = map.get("Name");
                String age = map.get("Age");

               Log.v("E_VALUE","Name"+name);
                Log.v("E_VALUE","Age"+age);

                mshowView.setText(age +"\n"+name);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });









    }





    }

