package com.example.currency

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
   val countryList = mapOf("USD" to 2,"GPB" to 3,"OMR" to 4,"AED" to 5)
    lateinit var toInput:AutoCompleteTextView
    lateinit var fromInput:AutoCompleteTextView
    lateinit var resultButton: Button

    lateinit var result:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultButton = findViewById(R.id.button_reslut)
        result = findViewById(R.id.text_input_result)

       val amount = findViewById<TextInputEditText>(R.id.amount_input_text)
        toInput = findViewById(R.id.to_input_text)
        fromInput = findViewById(R.id.from_input_text)
        val apdater = ArrayAdapter(this,R.layout.drop_down_menu,countryList.map({x-> x.key}))
        toInput.setAdapter(apdater)
        fromInput.setAdapter(apdater)


        toInput.addTextChangedListener {
            if(amount.text.toString().isNotEmpty()){
            val am = amount.text.toString().toDouble()


                val to =   countryList[toInput.text.toString()]
                val from =   countryList[fromInput.text.toString()]

                result.setText((am.times(to!!.toDouble()/from!!.toDouble())).toString())

            }

        }


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share ->{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"Hello Ali")
                startActivity(intent)
            }
            R.id.profile ->{}
            R.id.setting ->{}
        }

        return super.onOptionsItemSelected(item)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.opation_menu, menu);

        return true
    }
}