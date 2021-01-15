package com.abc.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        TextView tv=(TextView)findViewById(R.id.color1);
//        var tv: TextView = findViewById(R.id.color1)
//        tv.text="BLUE"
        color1.apply{
            setTextColor(getColor(R.color.darkBlue))
            text="BLUE"
            isClickable=true
            setOnClickListener(this@MainActivity)
        }
        color2.apply{
            setTextColor(getColor(R.color.darkRed))
            text="RED"
            isClickable=true
            setOnClickListener(this@MainActivity)
        }
        symbol1.apply {
            setTextColor(getColor(R.color.darkBlue))
            text="X"
            setOnClickListener(this@MainActivity)
        }
        symbol2.apply {
            setTextColor(getColor(R.color.darkRed))
            text="O"
            setOnClickListener(this@MainActivity)
        }

        startButton.setOnClickListener{
                val intent = Intent(this,GameActivity::class.java).apply{
                    putExtra( "PLAYERNAME1", name1.text.toString())
                    putExtra("PLAYERNAME2",name2.text.toString())
                    putExtra("COLOR1",color1.text.toString())
                    putExtra("COLOR2",color2.text.toString())
                    putExtra("SYMBOL1",symbol1.text.toString())
                    putExtra("SYMBOL2",symbol2.text.toString())
                }
                startActivity(intent)
            }
        }

    override fun onClick(v: View) {
        when(v.id){
            R.id.color1 -> {
                changeColor()
            }
            R.id.color2 -> {
                changeColor()
            }
            R.id.symbol1 -> {
                changeSymbol()
            }
            R.id.symbol2 -> {
                changeSymbol()
            }
    }
}

    private fun changeSymbol() {
        if(symbol1.text.toString()=="X"){
            symbol1.apply{
                text="O"
            }
            symbol2.apply{
                text="X"
            }
        }
        else{
            symbol1.apply{
                text="X"
            }
            symbol2.apply{
                text="O"
            }
        }
    }

    private fun changeColor() {
        if(color1.text.toString()=="BLUE")
        {
            color1.apply{
                text="RED"
                setTextColor(getColor(R.color.darkRed))
            }
            symbol1.setTextColor(getColor(R.color.darkRed))
            color2.apply{
                text="BLUE"
                setTextColor(getColor(R.color.darkBlue))
            }
            symbol2.setTextColor(getColor(R.color.darkBlue))
        }
        else
        {
            color1.apply{
                text="BLUE"
                setTextColor(getColor(R.color.darkBlue))
            }
            symbol1.setTextColor(getColor(R.color.darkBlue))
            color2.apply{
                text="RED"
                setTextColor(getColor(R.color.darkRed))
            }
            symbol2.setTextColor(getColor(R.color.darkRed))
        }
    }

}