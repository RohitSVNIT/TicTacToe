package com.rohit222gmail.t.k.nowin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TIcActivity extends AppCompatActivity {

    Button b[] = new Button[9];
    TextView status;
    int a[] = {0,0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic);

        b[0] = (Button) findViewById(R.id.one);
        b[1] = (Button) findViewById(R.id.two);
        b[2] = (Button) findViewById(R.id.three);
        b[3] = (Button) findViewById(R.id.four);
        b[4] = (Button) findViewById(R.id.five);
        b[5] = (Button) findViewById(R.id.six);
        b[6] = (Button) findViewById(R.id.seven);
        b[7] = (Button) findViewById(R.id.eight);
        b[8] = (Button) findViewById(R.id.nine);
        status = (TextView) findViewById(R.id.status);
    }

    public void player(View view) {
        String strNum =  (String) view.getTag();
        int num = Integer.parseInt(strNum);
        if(b[num-1].getText() == "")
        {
            b[num-1].setText("X");
            a[num-1] = -1;
            a = compAI(a);
            if(checkWin(a) == 1 || checkWin(a) == -1 || checkDraw(a) == 1)
            {
                if(checkWin(a) == 1)
                {
                    status.setText("YOU LOSE !");
                }
                else if(checkWin(a) == -1)
                {
                    status.setText("YOU WIN !");
                }
                else
                {
                    status.setText("GAME DRAW !");
                }
            }
        }
    }

    public void reset(View view) {
        for(int i=0 ; i<9 ; i++)
        {
            b[i].setText("");
            a[i] = 0;
        }
        status.setText("Try to Beat Me !");
    }

    // Computer Turn - Returns Whole Array with value inserted
    public int [] compAI(int x[])
    {
        int move = -1;
        int max = -2;
        for(int i=0 ; i<9 ; i++)
        {
            if(x[i] == 0)
            {
                x[i] = 1;
                int score = -algoAI(x,-1);
                x[i] = 0;
                if(score > max)
                {
                    max = score;
                    move = i;
                }
            }
        }
        if(move != -1)
        {
            x[move] = 1;
            b[move].setText("O");
        }
        return x;
    }

    // Recursive AI Algorithm
    public int algoAI(int x[],int turn)
    {
        int move = -1;
        int max = -2;
        if(checkWin(x) != 0)
        {
            return checkWin(x)*turn;
        }
        for(int i=0 ; i<9 ; i++)
        {
            if(x[i] == 0)
            {
                x[i] = turn;
                int score = -algoAI(x,-1*turn);
                if(score > max)
                {
                    max = score;
                    move = i;
                }
                x[i] = 0;
            }
        }
        if(move == -1)
            return 0;
        return max;
    }

    // Draw Condition - Returns 1 when draw
    public int checkDraw(int x[])
    {
        for(int i=0 ; i<9 ; i++)
        {
            if(x[i]==0)
                return 0;
        }
        return 1;
    }

    // Winning Condition - Rerturns Respective Player Code
    public int checkWin(int x[])
    {
        for(int i=0 ; i<9 ; i+=3)
        {
            if(x[i]==x[i+1] && x[i]==x[i+2])
            {
                return x[i];
            }
        }
        for(int i=0 ; i<3 ; i++)
        {
            if(x[i]==x[i+3] && x[i]==x[i+6])
            {
                return x[i];
            }
        }
        if(x[0]==x[4] && x[0]==x[8])
        {
            return x[0];
        }
        if(x[2]==x[4] && x[2]==x[6])
        {
            return x[2];
        }
        return 0;
    }
}
