package com.example.assignment_1_vamsi_gamidi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private boolean clicked;
    private Button colorChanger;
    private ImageView imageChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorChanger = findViewById(R.id.btnChangeText);
        imageChanger = findViewById(R.id.imageChanger);

        clicked = false;
        colorChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!clicked)
                {
                    colorChanger.setText(getResources().getText(R.string.BtnClicked));
                    colorChanger.setBackgroundColor(getResources().getColor(R.color.colorBlack, getResources().newTheme()));
                    colorChanger.setTextColor(getResources().getColor(R.color.colorWhite, getResources().newTheme()));
                    imageChanger.setImageResource(R.drawable.dalhousie3);
                    clicked = true;
                }
                else
                {
                    colorChanger.setText(getResources().getText(R.string.BtnNotClicked));
                    colorChanger.setBackgroundColor(getResources().getColor(R.color.colorLavender, getResources().newTheme()));
                    colorChanger.setTextColor(getResources().getColor(R.color.colorPrimaryDark, getResources().newTheme()));
                    imageChanger.setImageResource(R.drawable.dalhousie1);
                    clicked = false;
                }
            }
        });

    }
}
