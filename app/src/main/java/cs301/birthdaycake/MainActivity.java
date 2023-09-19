package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CakeView baseView;
    private Button goodBye;
    private Button blowOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //auto-generated config
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        baseView = findViewById(R.id.cakeview);
        CakeController controller = new CakeController(baseView);

        goodBye = findViewById(R.id.goodbyeButton);
        blowOut = findViewById(R.id.blowOutButton);
        goodBye.setOnClickListener(this);
        blowOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.blowOutButton) {
            baseView.getModel().candleLit = false;
            baseView.invalidate();
        }

    }
}
