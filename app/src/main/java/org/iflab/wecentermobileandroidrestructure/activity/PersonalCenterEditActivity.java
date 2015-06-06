package org.iflab.wecentermobileandroidrestructure.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import org.iflab.wecentermobileandroidrestructure.R;

import java.util.Calendar;

/**
 * Created by hcjcch on 15/6/6.
 */
public class PersonalCenterEditActivity extends BaseActivity {
    private TextView dateSelect;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center_edit);
        calendar = Calendar.getInstance();
        dateSelect = (TextView) findViewById(R.id.txt_birthday_select);
        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}