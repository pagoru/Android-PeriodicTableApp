package es.pagoru.periodictableapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class IndividualElement extends AppCompatActivity {

    private Element element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        element = Element.getElement(getIntent().getStringExtra("Name"));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setTitle(element.getName());
        setContentView(R.layout.activity_individual_element);

        ((TextView) findViewById(R.id.tv_Number)).setText(element.getNumber());
        ((TextView) findViewById(R.id.tv_Name)).setText(element.getName());
        ((TextView) findViewById(R.id.tv_Symbol)).setText(element.getSymbol());
        ((TextView) findViewById(R.id.tv_Category)).setText(element.getCategory());
        ((TextView) findViewById(R.id.tv_Group)).setText(element.getGroup());
        ((TextView) findViewById(R.id.tv_Period)).setText(element.getPeriod());
        ((TextView) findViewById(R.id.tv_Block)).setText(element.getBlock());
        ((TextView) findViewById(R.id.tv_Weight)).setText(element.getWeight());


        Button wikiButton = (Button) findViewById(R.id.tv_Wiki);
        wikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wikiLink = "https://ca.wikipedia.org/wiki/" + element.getName();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(wikiLink));
                startActivity(browserIntent);
            }
        });
    }
}
