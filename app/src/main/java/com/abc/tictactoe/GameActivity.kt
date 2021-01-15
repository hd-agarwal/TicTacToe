package com.abc.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_game.*

lateinit var gameBoard :Array<Array<Button>>
     var names:Array<String> = arrayOf("Player 1","Player 2")
     var colors:Array<Int> =arrayOf (0,1)
     var symbols:Array<String> =arrayOf("X","O")
     var count:Int=0
var player=0
var boardStatus=Array(3){IntArray(3)}
class GameActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        names[0]=intent.getStringExtra("PLAYERNAME1")
        names[1]=intent.getStringExtra("PLAYERNAME2")
        val c1=intent.getStringExtra("COLOR1")
        if(c1=="BLUE"){
            colors[0]=0
            colors[1]=1
        }
        else{
            colors[0]=1
            colors[1]=0
        }
        symbols[0]=intent.getStringExtra("SYMBOL1")
        symbols[1]=intent.getStringExtra("SYMBOL2")

        gameBoard= arrayOf(arrayOf(r0c0,r0c1,r0c2), arrayOf(r1c0,r1c1,r1c2),arrayOf(r2c0,r2c1,r2c2))
        resetBoard();
        newgamebtn.setOnClickListener{
            resetBoard()
        }

        for(i in gameBoard){
            for(btn in i)
            {
                btn.setOnClickListener(this)
            }
        }


    }
    private fun resetBoard(){
        count=0
        player=0
        winnermessage.text=""
        turnMessage.isEnabled=true
        playername.text= names[player]
        symbol.text= symbols[player]
        turn.text="TURN"
        if(colors[player]==0)
            symbol.setTextColor(getColor(R.color.darkBlue))
        else
            symbol.setTextColor(getColor(R.color.darkRed))
        for(i in 0..2){
            for(j in 0..2)
            {
                boardStatus[i][j]=-1
                gameBoard[i][j].apply {
                    text=""
                    isEnabled=true
                    isClickable=true
                }
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.r0c0 -> {
                updateValue(row=0,col=0,p=player)
            }
            R.id.r0c1 -> {
                updateValue(row=0,col=1,p=player)
            }
            R.id.r0c2 -> {
                updateValue(row=0,col=2,p=player)
            }
            R.id.r1c0 -> {
                updateValue(row=1,col=0,p=player)
            }
            R.id.r1c1 -> {
                updateValue(row=1,col=1,p=player)
            }
            R.id.r1c2 -> {
                updateValue(row=1,col=2,p=player)
            }
            R.id.r2c0 -> {
                updateValue(row=2,col=0,p=player)
            }
            R.id.r2c1 -> {
                updateValue(row=2,col=1,p=player)
            }
            R.id.r2c2 -> {
                updateValue(row=2,col=2,p=player)
            }
        }
        isWon()
    }

    private fun isWon() {
        //Rows
        for(i in 0..2){
            if(boardStatus[i][0]==0&&boardStatus[i][1]==0&&boardStatus[i][2]==0){
                turn.text=""
                playername.text="MATCH OVER!!"
                symbol.text=""
                disableButtons()
                winnermessage.text=names[0]+" wins!"
            }
            if(boardStatus[i][0]==1&&boardStatus[i][1]==1&&boardStatus[i][2]==1){
                turn.text=""
                playername.text="MATCH OVER!!"
                symbol.text=""
                disableButtons()
                winnermessage.text=names[1]+" wins!"
            }
        }
        //Columns
        for(i in 0..2){
            if(boardStatus[0][i]==0&&boardStatus[1][i]==0&&boardStatus[2][i]==0){
                turn.text=""
                playername.text="MATCH OVER!!"
                symbol.text=""
                disableButtons()
                winnermessage.text=names[0]+" wins!"
            }
            if(boardStatus[0][i]==1&&boardStatus[1][i]==1&&boardStatus[2][i]==1){
                turn.text=""
                playername.text="MATCH OVER!!"
                symbol.text=""
                disableButtons()
                winnermessage.text=names[1]+" wins!"
            }
        }
        //Diagonal1
        if(boardStatus[0][0]==0&&boardStatus[1][1]==0&&boardStatus[2][2]==0){
                turn.text=""
                playername.text="MATCH OVER!!"
                symbol.text=""
                disableButtons()
                winnermessage.text=names[0]+" wins!"
        }
        if(boardStatus[0][0]==1&&boardStatus[1][1]==1&&boardStatus[2][2]==1){
            turn.text=""
            playername.text="MATCH OVER!!"
            symbol.text=""
            disableButtons()
            winnermessage.text=names[1]+" wins!"
        }
        //Diagonal2
        if(boardStatus[0][2]==0&&boardStatus[1][1]==0&&boardStatus[2][0]==0){
            turn.text=""
            playername.text="MATCH OVER!!"
            symbol.text=""
            disableButtons()
            winnermessage.text=names[0]+" wins!"
        }
        if(boardStatus[0][2]==1&&boardStatus[1][1]==1&&boardStatus[2][0]==1){
            turn.text=""
            playername.text="MATCH OVER!!"
            symbol.text=""
            disableButtons()
            winnermessage.text=names[1]+" wins!"
        }
    }

    private fun disableButtons() {
        for(i in gameBoard){
            for(btn in i)
            {
                btn.isEnabled=false
            }
        }
    }

    private fun updateValue(row: Int, col: Int, p: Int) {
        count++
        gameBoard[row][col].apply {
            textSize=32f
            if(colors[player]==0)
                setTextColor(getColor(R.color.darkBlue))
            else
                setTextColor(getColor(R.color.darkRed))
            text = symbols[player]
            isEnabled=false;

        }
        boardStatus[row][col]=player
        if(player==0)
            player=1
        else
            player=0
        playername.text= names[player]
        symbol.text= symbols[player]
        if(count==9){
            playername.text="MATCH DRAWN!!!"
            symbol.text=""
            turn.text=""
            turnMessage.isEnabled=false
        }

        if(colors[player]==0)
            symbol.setTextColor(getColor(R.color.darkBlue))
        else
            symbol.setTextColor(getColor(R.color.darkRed))
    }
}