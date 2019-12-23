import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class MathApp extends JFrame {
    final static int maxGap = 20;
    JButton submitBtn = new JButton("Submit");
    GridLayout experimentLayout = new GridLayout(0,2);
    JTextField userAnswer = new JTextField("", 5);
    Label memLabel=new Label("",Label.LEFT);
    Label scoreLabel=new Label("");
    int answer;
    int questions = 0;
    int corAnswers = 0;

    public MathApp(String name) {
        super(name);
        setResizable(false);
    }

    public void addComponentsToPane(final Container pane) {
        final JPanel topComps = new JPanel();
        userAnswer.setEditable(false);
        setQuestionLabelAndAnswer();
        topComps.add(memLabel);
        topComps.add(userAnswer);
        final JPanel btnComps = new JPanel();
        JPanel bottomControls = new JPanel();
        JButton clearBtn = new JButton("Clear");
        bottomControls.setLayout(new GridLayout(4,8));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        int xny = (int)buttonSize.getWidth()+maxGap;//width n height the same
        btnComps.setPreferredSize(new Dimension(xny,xny));

        //Add num buttons to Grid Layout
        int[] nums = { 1,2,3,4,5,6,7,8,9,0 };
        for (int num : nums)
        {
            btnComps.add(new ActionBtn(Integer.toString(num), this));
        }
        btnComps.add(new JSeparator());
        btnComps.add(clearBtn);

        //Add bottomControls to set up horizontal and vertical gaps
        setScoreLabelTxt();
        bottomControls.add(scoreLabel);
        bottomControls.add(submitBtn);

        //on clear
        clearBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                userAnswer.setText("");
            }
        });
        //on click of submit
        submitBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(Integer.parseInt(userAnswer.getText()) == answer){
                    corAnswers++;
                }
                questions++;
                setScoreLabelTxt();
                setQuestionLabelAndAnswer();
                userAnswer.setText("");
            }
        });
        pane.add(topComps, BorderLayout.NORTH);
        pane.add(btnComps, BorderLayout.CENTER);
        pane.add(bottomControls, BorderLayout.SOUTH);
    }
    private static int rand(int min, int max){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    private void setScoreLabelTxt(){
        scoreLabel.setText("Score: "+corAnswers+"/"+questions);
    }

    private void setQuestionLabelAndAnswer(){
        int num1 = rand(1,5);
        int num2 = rand(1,5);
        memLabel.setText(num1+"+"+num2+" =");
        answer = num1 + num2;
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        MathApp frame = new MathApp("MathApp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void numberClick(String num){
        userAnswer.setText(userAnswer.getText() + num);
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}


class ActionBtn extends JButton implements ActionListener
{
    MathApp cl;

    /////////////////////////////////
    ActionBtn(String txt, MathApp clc)
    {
        this.cl=clc;
        this.setText(txt);
        this.cl.add(this);
        addActionListener(this);
    }
    ////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ev)
    {
        this.cl.numberClick(this.getText());
    }//actionPerformed
}