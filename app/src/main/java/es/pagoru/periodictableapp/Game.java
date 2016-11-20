package es.pagoru.periodictableapp;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Game extends Activity {

    private final Context context = this;
    private RelativeLayout[] rl_elements;
    private Element currentElement;

    private TextView corrects;
    private int correctInt = 0;
    private TextView mistakes;
    private int mistakeInt = 0;

    private TextView question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);


        RelativeLayout[] rl_elements = {
                (RelativeLayout)findViewById(R.id.gm_element_1), (RelativeLayout)findViewById(R.id.gm_element_2),
                (RelativeLayout)findViewById(R.id.gm_element_3), (RelativeLayout)findViewById(R.id.gm_element_4)
        };
        this.rl_elements = rl_elements;

        corrects = (TextView) findViewById(R.id.gm_corects);
        mistakes = (TextView) findViewById(R.id.gm_mistakes);
        question = (TextView) findViewById(R.id.gm_question);

        newCurrentElement();

        for (final RelativeLayout rl_element : rl_elements) {
            rl_element.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String symbol = ((TextView)rl_element.getChildAt(0)).getText().toString();
                    if(symbol.equalsIgnoreCase(currentElement.getSymbol())){
                        correctInt++;
                        corrects.setText("Correctes: " + correctInt);
                        newCurrentElement();
                        return;
                    }
                    newCurrentElement();
                    mistakeInt++;
                    mistakes.setText("Errors: " + mistakeInt);
                }
            });
        }
    }


    private void newCurrentElement(){
        Element[] rl_options = new Element[4];
        List<Element> elements = new ArrayList<>(Element.getElements());

        for (int i = 0; i < rl_options.length; i++) {
            int r = Utils.getRandom(0, elements.size());
            rl_options[i] = elements.get(r);
            elements.remove(r);
        }

        currentElement = rl_options[Utils.getRandom(0, 4)];
        question.setText("¿Quin element es " + currentElement.getName() + "?");

        int i = 0;
        for (RelativeLayout rl_element : rl_elements) {

            TextView tv_element = (TextView) rl_element.getChildAt(0);
            TextView tv_number = (TextView) rl_element.getChildAt(1);
            /*
            0 - Element
            1 - Number
             */

            rl_element.setBackgroundColor(getResources().getColor(rl_options[i].getColor()) );
            tv_element.setText(rl_options[i].getSymbol());
            tv_number.setText(rl_options[i].getNumber());

            i++;
        }
    }
}
