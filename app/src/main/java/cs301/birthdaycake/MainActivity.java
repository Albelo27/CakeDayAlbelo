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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //auto-generated config
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        baseView = findViewById(R.id.cakeview);
        CakeController controller = new CakeController(baseView);

        goodBye = findViewById(R.id.goodbyeButton);
        goodBye.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i("info", "goodbye");
    }
}
