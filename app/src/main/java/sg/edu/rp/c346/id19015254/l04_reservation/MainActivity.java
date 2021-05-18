package sg.edu.rp.c346.id19015254.l04_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button cfm;
    Button reset;
    CheckBox smoke;
    CheckBox nonSmoke;
    EditText name;
    EditText num;
    EditText pax;
    DatePicker dp;
    TimePicker tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cfm = findViewById(R.id.btnCfm);
        reset = findViewById(R.id.btnReset);
        smoke = findViewById(R.id.cbSmoke);
        name = findViewById(R.id.etName);
        num = findViewById(R.id.etNumber);
        pax = findViewById(R.id.etSize);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute) {
                if (tp.getCurrentHour() > 20 || tp.getCurrentHour() < 8)
                {
                    Toast.makeText(getApplicationContext(), "Please choose a time between 8AM and 8:59PM inclusive!", Toast.LENGTH_LONG).show();
                    tp.setCurrentHour(19);
                    tp.setCurrentMinute(30);
                }
            }
        });

        cfm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int month = dp.getMonth() + 1;
                String date = dp.getDayOfMonth() + "/" + month + "/" + dp.getYear();
                String time = "";
                String smokeArea = "";
                String name1 = name.getText().toString().trim();

                if (name1.length() == 0 || num.getText().length() == 0 || pax.getText().length() == 0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter in all fields!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (tp.getCurrentMinute() < 10)
                    {
                        time = tp.getCurrentHour() + ":0" + tp.getCurrentMinute();
                    }
                    else
                    {
                        time = tp.getCurrentHour() + ":" + tp.getCurrentMinute();
                    }

                    if (smoke.isChecked())
                    {
                        smokeArea = "Yes";
                    }
                    else
                    {
                        smokeArea = "No";
                    }

                    String toastText = "Name: " + name.getText() + "\nNo.: " + num.getText() + "\nPax: " + pax.getText() + "\nDate: " + date + "\nTime: " + time + "\nSmoking Area: " + smokeArea;
                    Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
                dp.updateDate(2020, 5, 1);
                smoke.setChecked(false);
                nonSmoke.setChecked(false);
                name.setText("");
                num.setText("");
                pax.setText("");
            }
        });


    }
}