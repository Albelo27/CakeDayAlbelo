package cs301.birthdaycake;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class CakeController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    private CakeView cakeView;
    private CakeModel cakeModel;
    private Button blowOut;
    private Switch candle;

    public CakeController(CakeView view, Button blowOutButton, Switch candleSwitch) {
        cakeView = view;
        cakeModel = cakeView.getModel();
        blowOut = blowOutButton;
        candle = candleSwitch;
        blowOut.setOnClickListener(this);
        candle.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.blowOutButton) {
            cakeModel.candleLit = !cakeModel.candleLit;
            cakeView.invalidate();
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            cakeModel.hasCanlde = compoundButton.isChecked();
            cakeView.invalidate();
    }
}
