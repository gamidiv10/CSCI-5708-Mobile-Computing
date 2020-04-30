package com.example.lab_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewCourseActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.courselistsql.REPLY";

    private EditText mEditCourseView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        mEditCourseView = findViewById(R.id.edit_course);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditCourseView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String course = mEditCourseView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, course);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
