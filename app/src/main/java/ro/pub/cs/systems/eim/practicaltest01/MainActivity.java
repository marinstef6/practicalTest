package ro.pub.cs.systems.eim.practicaltest01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ro.pub.cs.systems.eim.practicaltest01.Constants;
import ro.pub.cs.systems.eim.practicaltest01.R;
public class MainActivity extends AppCompatActivity {

    //trebuie sa stiu de ce tip este butonul/campul ca sa le initializez aici si declar
    private EditText leftEditText;
    private EditText rightEditText;
    private Button pressMeButton, pressMeTooButton;
    private Button NavigateToSecondaryActivity;
    // pentru partea de ascultare
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//pentru intentie
                if(view.getId() == R.id.navigate_to_secondary_activity_button) {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01SecondaryActivity.class);
                    //aici a trebuit sa fac suma lor
                    int numberOfClicks = Integer.parseInt(leftEditText.getText().toString()) +
                            Integer.parseInt(rightEditText.getText().toString());
                    intent.putExtra(Constants.NUMBER_OF_CLICKS, numberOfClicks);
                    //cere un int aceasta metoda de aia trebuie sa am secondary cu int
                    startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        }
                //inainte de intentie sa pot apasa butonul si sa se incrementeze pentru b1
            int leftNumberOfClicks = Integer.parseInt(leftEditText.getText().toString());
            int rightNumberOfClicks = Integer.parseInt(rightEditText.getText().toString());
            if(view.getId() == R.id.press_me_button)
            {
                leftNumberOfClicks++;
                leftEditText.setText((String.valueOf(leftNumberOfClicks)));
            }
            else if(view.getId() == R.id.press_me_too_button)
            {
                rightNumberOfClicks++;
                rightEditText.setText((String.valueOf(rightNumberOfClicks)));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //initializare cu id din xml
        leftEditText = findViewById(R.id.left_edit_text);
        rightEditText = findViewById(R.id.right_edit_text);
        pressMeButton = findViewById(R.id.press_me_button);
        pressMeTooButton = findViewById(R.id.press_me_too_button);
    //asta era la inceput pana sa le setez pentru a2
       // leftEditText.setText("0");
        //rightEditText.setText("0");
//sa mearga apasarea butoanelor pentru b1
        pressMeButton.setOnClickListener(new ButtonClickListener());
        pressMeTooButton.setOnClickListener(new ButtonClickListener());

        //pentru exercitiul unde imi cere salvarea instantei B2b
        //pentru b2a sa merg in xml
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
                leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
            } else {
                leftEditText.setText(String.valueOf(0));
            }
            if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
                rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
            } else {
                rightEditText.setText(String.valueOf(0));
            }
        } else {
            leftEditText.setText(String.valueOf(0));
            rightEditText.setText(String.valueOf(0));
        }

        //pentru secondary activity B2b
        NavigateToSecondaryActivity = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        NavigateToSecondaryActivity.setOnClickListener(buttonClickListener);
    }
    //pentru salvare+codul de mai sus B2b
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.LEFT_COUNT, leftEditText.getText().toString());
        savedInstanceState.putString(Constants.RIGHT_COUNT, rightEditText.getText().toString());
    }
    //pentru restaurare
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.LEFT_COUNT)) {
            leftEditText.setText(savedInstanceState.getString(Constants.LEFT_COUNT));
        } else {
            leftEditText.setText(String.valueOf(0));
        }
        if (savedInstanceState.containsKey(Constants.RIGHT_COUNT)) {
            rightEditText.setText(savedInstanceState.getString(Constants.RIGHT_COUNT));
        } else {
            rightEditText.setText(String.valueOf(0));
        }
    }
    //pentru toast c2
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);
        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

}
