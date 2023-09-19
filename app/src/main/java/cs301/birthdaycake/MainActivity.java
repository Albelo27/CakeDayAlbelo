package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity  {

    private CakeView baseView;
    private Button blowOutButton;
    private Switch candleSwitch;
    private SeekBar numCandleSeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //auto-generated config
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        baseView = findViewById(R.id.cakeview);
        blowOutButton = findViewById(R.id.blowOutButton);
        candleSwitch = findViewById(R.id.candleSwitch);
        numCandleSeek = findViewById(R.id.numCandleSeek);
        CakeController controller = new CakeController(baseView, blowOutButton, candleSwitch, numCandleSeek);
    }


}
