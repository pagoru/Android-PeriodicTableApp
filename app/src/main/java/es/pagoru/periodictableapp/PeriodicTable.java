package es.pagoru.periodictableapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PeriodicTable extends Activity {

    private final Context context = this;
    private RelativeLayout[] rl_elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_periodic_table);

        RelativeLayout[] rl_elements = {
                (RelativeLayout)findViewById(R.id.element_1), (RelativeLayout)findViewById(R.id.element_2),
                (RelativeLayout)findViewById(R.id.element_3), (RelativeLayout)findViewById(R.id.element_4),
                (RelativeLayout)findViewById(R.id.element_5), (RelativeLayout)findViewById(R.id.element_6),
                (RelativeLayout)findViewById(R.id.element_7), (RelativeLayout)findViewById(R.id.element_8),
        };
        this.rl_elements = rl_elements;

        randomizeElements();

        Button btn_random_elements = (Button) findViewById(R.id.btn_random_elements);
        btn_random_elements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomizeElements();
            }
        });

        for(final RelativeLayout relativeLayout : rl_elements){
            relativeLayout.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, IndividualElement.class);
                    intent.putExtra("Name", ((TextView)relativeLayout.getChildAt(0)).getText());
                    startActivity(intent);
                }
            });
        }

    }

    private int getRandom(int min, int max){
        return (min + (int)(Math.random() * ((max - min))));
    }

    private void randomizeElements(){
        List<Element> elements = new ArrayList<>(Element.getElements());

        for (RelativeLayout rl_element : rl_elements) {

            TextView tv_name = (TextView) rl_element.getChildAt(0);
            TextView tv_element = (TextView) rl_element.getChildAt(1);
            TextView tv_number = (TextView) rl_element.getChildAt(2);
            /*
            0 - Name
            1 - Element
            2 - Number
             */
            int num = getRandom(0, elements.size());
            Element element = elements.get(num);

            rl_element.setBackgroundColor(getResources().getColor(element.getColor()) );
            tv_name.setText(element.getName());
            tv_element.setText(element.getSymbol());
            tv_number.setText(element.getNumber());

            elements.remove(num);
        }

    }
}
