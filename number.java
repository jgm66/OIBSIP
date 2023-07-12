import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.event.*;

public class number extends JFrame
{
    JTextField guess, totalGuess;
    ButtonListener buttonListener;
    JLabel input_label,try_label,totalguesslabel;

    //setting random number in rand variable
    int rand=(int) (Math.random()*100);
    int count=0;
    public number()
    {
        //Get the container
        Container con = getContentPane();
        con.setLayout(null);
        con.setBackground(Color.PINK);

        //Creating label for input number
        input_label=new JLabel("Enter a number between 1 to 100");
        input_label.setFont(new Font("tunga",Font.PLAIN,14));
        input_label.setSize(270,20);
        input_label.setLocation(140,50);

        //Creating Label for Display message    
        try_label = new JLabel("Try and guess it !");
        try_label.setFont(new Font("tunga",Font.PLAIN,14));
        try_label.setSize(270,20);
        try_label.setLocation(180,140);

         //Creating TextField for input guess
        guess = new JTextField(10);
        guess.setSize(50,30);
        guess.setLocation(210,90);

        //Creating TextField for Display Number of Guesses  
        totalGuess = new JTextField(10);
        totalGuess.setSize(40,25);
        totalGuess.setLocation(150,260);

        //Creating Label for Display Number of Guesses    
        totalguesslabel = new JLabel("Number of Guesses");
        totalguesslabel .setFont(new Font("tunga",Font.PLAIN,14));
        totalguesslabel.setSize(270,20);
        totalguesslabel.setLocation(200,260);

        //creating Guess button
        JButton guessbutton =new JButton("Guess");
        guessbutton.setSize(100,30);
        guessbutton.setLocation(180,180);
        guessbutton.setBackground(Color.WHITE);
        buttonListener = new ButtonListener();
        guessbutton.addActionListener(buttonListener);

        //Place the components in the pane
        con.add(totalguesslabel);
        con.add(try_label);
        con.add(input_label);
        con.add(guess);
        con.add(totalGuess);
        con.add(guessbutton);
    
        //Seting the window
        totalGuess.setEditable(false);
        setTitle("GUESS THE NUMBER GAME");
        setSize(500,350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int actual = Integer.parseInt(guess.getText());
            count=count+1;
            if(actual<rand)
            {
                try_label.setText(actual+" is low, try again!!");
            }
            else if(actual>rand)
            {
                try_label.setText(actual+" is high, try again!!");
            }
            else
            {
                try_label.setText("Your guess is correct, You win!!");
                guess.setEditable(false);
            }

            guess.requestFocus();
            guess.selectAll();
            totalGuess.setText(count+"");
        }
    }

    public static void main(String[] args)
    {
        number num = new number();
    }
}