package com.example.admin.androidebook.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.androidebook.R;
import com.example.admin.androidebook.Util.Method;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Booking extends AppCompatActivity implements OnClickListener {
    Button button;
    EditText mail,ngaytap,matkhau;
    DatabaseReference mData;
    public Toolbar toolbar;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Method.forceRTLIfSupported(getWindow(), Booking.this);

        toolbar = (Toolbar) findViewById(R.id.toolbar_booking);
        toolbar.setTitle(getResources().getString(R.string.booking));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mData=FirebaseDatabase.getInstance().getReference();
        button=(Button)findViewById(R.id.button);

        mail=(EditText)findViewById(R.id.editText);
        ngaytap=(EditText)findViewById(R.id.editText2);
        ngaytap.setOnClickListener(this);
        matkhau=(EditText)findViewById(R.id.editText3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String a=mail.getText().toString();
                final String b=ngaytap.getText().toString();
                final String c=matkhau.getText().toString();
                LichTap lichtap=new LichTap(a,b,c);
                mData.child("LichTap").push().setValue(lichtap);
                Toast.makeText(getApplicationContext(),"Lịch tập đã được đặt",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void onClick(View view) {
        if (view == ngaytap) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            ngaytap.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}


