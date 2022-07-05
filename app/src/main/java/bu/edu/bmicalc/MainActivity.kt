package bu.edu.bmicalc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Log.*
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


open class MainActivity : AppCompatActivity() {
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get the reference of the submit button
        val submitBtn: Button = findViewById<Button>(R.id.buttonId_submit)
        // register the button with an OnClickListener
        submitBtn.setOnClickListener { view -> onClickSubmit(view) }
    }

    @SuppressLint("ServiceCast")
    fun onClickSubmit(v: View) {
        v.clearFocus()
        // get the name from the EditText
        val nameEditText: EditText = findViewById(R.id.editTextId_name) as EditText
        val name: String = nameEditText.getText().toString()
        val heightEditText: EditText = findViewById(R.id.editTextId_height) as EditText
        val heightStr: String = heightEditText.getText().toString()
        val height = heightStr.toInt().toDouble()
        val weightEditText: EditText = findViewById(R.id.editTextId_weight) as EditText
        val weightStr: String = weightEditText.getText().toString()
        val weight = weightStr.toInt().toDouble()
        // get the gender from the RadioGroup
        val genderRadioGroup: RadioGroup = findViewById(R.id.radioGroupId_gender) as RadioGroup
        var gender = ""
        when (genderRadioGroup.getCheckedRadioButtonId()) {
            R.id.radioButtonId_female -> gender = "female"
            R.id.radioButtonId_male -> gender = "male"
            else -> {}
        }
        //let's now calculate the BMI
        var bmi = weight / height / height * 703.0
        bmi = Math.round(bmi * 100).toDouble()
        bmi /= 100
        val bmiStr = bmi.toString()
        // compose a message from the above information
        val msg = " Here is your information: \n $name\n Your BMI is $bmiStr"


        //set the message in the TextView
        val infoTextView: TextView = findViewById(R.id.textViewId_info) as TextView
        infoTextView.text = msg
        // display a toast message
        Toast.makeText(this, "Submit successfully!", Toast.LENGTH_LONG).show()
        nameEditText.setText("")
        heightEditText.setText("")
        weightEditText.setText("")
        genderRadioGroup.clearCheck()
        nameEditText.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
        Log.d(TAG,"Submit")
    }

    companion object {
        private const val TAG = "MyApp"
    }
}
