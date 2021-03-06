package edu.kvcc.cis298.cis298assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class TemperatureConverter extends AppCompatActivity {

    //Bundle Keys
    private static final String KEY_FORMULA = "gdfse4wradsCF74";
    private static final String KEY_CONVERSION = "FASDF5DSA512";

    //Layout Elements
    private Button mCalculateButton;
    private RadioGroup mOutputRadioGroup;
    private RadioGroup mInputRadioGroup;
    private RadioButton mInputCelsiusRadioButton;
    private RadioButton mInputFahrenheitRadioButton;
    private RadioButton mInputKelvinRadioButton;
    private RadioButton mInputRankineRadioButton;
    private RadioButton mOutputCelsiusRadioButton;
    private RadioButton mOutputFahrenheitRadioButton;
    private RadioButton mOutputKelvinRadioButton;
    private RadioButton mOutputRankineRadioButton;
    private TextView mOutputAnswerTextView;
    private TextView mOutputFormulaTextView;
    private EditText mtempInputEditText;

    //Variables
    private DecimalFormat TwoDigits = new DecimalFormat("#.##");
    private Double InputtedTemp;
    private Double ConvertedTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        //instantiate layout elements
        mCalculateButton = (Button) findViewById(R.id.convertButton);
        mOutputRadioGroup = (RadioGroup) findViewById(R.id.inputRadioButtons);
        mInputRadioGroup = (RadioGroup) findViewById(R.id.outputRadioButtons);
        mInputCelsiusRadioButton = (RadioButton) findViewById(R.id.inputCelsius);
        mInputFahrenheitRadioButton = (RadioButton) findViewById(R.id.inputFahrenheit);
        mInputKelvinRadioButton = (RadioButton) findViewById(R.id.inputKelvin);
        mInputRankineRadioButton = (RadioButton) findViewById(R.id.inputRankine);
        mOutputCelsiusRadioButton = (RadioButton) findViewById(R.id.outputCelsius);
        mOutputFahrenheitRadioButton = (RadioButton) findViewById(R.id.outputFahrenheit);
        mOutputKelvinRadioButton = (RadioButton) findViewById(R.id.outputKelvin);
        mOutputRankineRadioButton = (RadioButton) findViewById(R.id.outputRankine);
        mOutputAnswerTextView = (TextView) findViewById(R.id.textViewOutput);
        mOutputFormulaTextView = (TextView) findViewById(R.id.textViewFormulaOutput);
        mtempInputEditText = (EditText) findViewById(R.id.tempInputEditText);

        validateButton();

        //Create Listeners
        //To Radio buttons listener
        mOutputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                validateButton();
            }
        });
        //From Radio buttons listener
        mInputRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                validateButton();
            }
        });
        //Calculate button listener
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOutputFormulaTextView.setText("");
                mOutputAnswerTextView.setText("");
                setResults();
            }
        });
    }

    //Enables and disables the button depending if both sets of radio buttons have a selection
    private void validateButton()
    {
        if(mOutputRadioGroup.getCheckedRadioButtonId() != -1 && mInputRadioGroup.getCheckedRadioButtonId() != -1)
            mCalculateButton.setEnabled(true);
        else
            mCalculateButton.setEnabled(false);
    }

    //Sets the converted temp and conversion formula to the display TextViews
    private void setResults()
    {
        //error prevention
        if(mtempInputEditText.getText().toString().isEmpty())
        {
            Toast.makeText(TemperatureConverter.this,R.string.empty_edit_toast,Toast.LENGTH_SHORT).show();
            return;
        }

        InputtedTemp = Double.parseDouble(mtempInputEditText.getText().toString());

        //nested ifs, Outside if selects what "From" radio button is selected
        //Inside if selects what "To" radio button is checked.
        if (mInputCelsiusRadioButton.isChecked())
        {
            if (mOutputCelsiusRadioButton.isChecked())
            {
                //If both the "To" and "From" radio buttons are the same
                //Display an error message
                Toast.makeText(TemperatureConverter.this,R.string.same_unit_toast,Toast.LENGTH_SHORT).show();
            }
            else if (mOutputFahrenheitRadioButton.isChecked())
            {
                //calls correct conversion Method fro Conversions Class
                ConvertedTemp = Conversion.celsiusToFahrenheit(InputtedTemp);
                //Formats answer and sets it to the right text view
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°C = " +  TwoDigits.format(ConvertedTemp)+ "°F");
                //Displays the formula for conversion
                mOutputFormulaTextView.setText(R.string.celsius_to_fahrenheit);
            }
            else if (mOutputKelvinRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.celsiusToKelvin(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°C = " +  TwoDigits.format(ConvertedTemp)+ "K");
                mOutputFormulaTextView.setText(R.string.celsius_to_kelvin);
            }
            else if (mOutputRankineRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.celsiusToFahrenheit(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°C = " +  TwoDigits.format(ConvertedTemp)+ "°R");
                mOutputFormulaTextView.setText(R.string.celsius_to_rankine);
            }
        }

        //See top
        else if (mInputFahrenheitRadioButton.isChecked())
        {
            if (mOutputCelsiusRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.fahrenheitToCelsius(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°F = " +  TwoDigits.format(ConvertedTemp)+ "°C");
                mOutputFormulaTextView.setText(R.string.fahrenheit_to_celsius);
            }
            else if (mOutputFahrenheitRadioButton.isChecked())
            {
                Toast.makeText(TemperatureConverter.this,R.string.same_unit_toast,Toast.LENGTH_SHORT).show();
            }
            else if (mOutputKelvinRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.fahrenheitToKelvin(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°F = " +  TwoDigits.format(ConvertedTemp)+ "K");
                mOutputFormulaTextView.setText(R.string.fahrenheit_to_kelvin);
            }
            else if (mOutputRankineRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.fahrenheitToRankine(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°F = " +  TwoDigits.format(ConvertedTemp)+ "°R");
                mOutputFormulaTextView.setText(R.string.fahrenheit_to_rankine);
            }
        }

        //See top
        else if (mInputKelvinRadioButton.isChecked())
        {
            if (mOutputCelsiusRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.kelvinToCelsius(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "K = " +  TwoDigits.format(ConvertedTemp)+ "°C");
                mOutputFormulaTextView.setText(R.string.kelvin_to_celsius);
            }
            else if (mOutputFahrenheitRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.kelvinToFahrenheit(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "K = " +  TwoDigits.format(ConvertedTemp)+ "°F");
                mOutputFormulaTextView.setText(R.string.kelvin_to_rankine);
            }
            else if (mOutputKelvinRadioButton.isChecked())
            {
                Toast.makeText(TemperatureConverter.this,R.string.same_unit_toast,Toast.LENGTH_SHORT).show();
            }
            else if (mOutputRankineRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.kelvinToRankine(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "K = " +  TwoDigits.format(ConvertedTemp)+ "°R");
                mOutputFormulaTextView.setText(R.string.kelvin_to_rankine);
            }
        }

        //See Top
        else if (mInputRankineRadioButton.isChecked())
        {
            if (mOutputCelsiusRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.rankineToCelsius(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°R = " +  TwoDigits.format(ConvertedTemp)+ "°C");
                mOutputFormulaTextView.setText(R.string.rankine_to_celsius);
            }
            else if (mOutputFahrenheitRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.rankineToFahrenheit(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°R = " +  TwoDigits.format(ConvertedTemp)+ "°F");
                mOutputFormulaTextView.setText(R.string.rankine_to_fahrenheit);
            }
            else if (mOutputKelvinRadioButton.isChecked())
            {
                ConvertedTemp = Conversion.rankineToKelvin(InputtedTemp);
                mOutputAnswerTextView.setText(TwoDigits.format(InputtedTemp) + "°R = " +  TwoDigits.format(ConvertedTemp)+ "K");
                mOutputFormulaTextView.setText(R.string.rankine_to_kelvin);
            }
            else if (mOutputRankineRadioButton.isChecked())
            {
                Toast.makeText(TemperatureConverter.this,R.string.same_unit_toast,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temperature_converter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Puts the conversion and formula into the bundle
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_CONVERSION,mOutputAnswerTextView.getText().toString());
        outState.putString(KEY_FORMULA,mOutputFormulaTextView.getText().toString());
    }

    //Gets the conversion and formula into the bundle
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mOutputAnswerTextView.setText(savedInstanceState.getString(KEY_CONVERSION));
        mOutputFormulaTextView.setText(savedInstanceState.getString(KEY_FORMULA));
    }
}
