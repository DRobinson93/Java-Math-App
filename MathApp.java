import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MathApp extends JFrame {
    final static int maxGap = 20;
    JButton applyButton = new JButton("Submit");
    GridLayout experimentLayout = new GridLayout(0,2);
    JTextField nonEditTxt = new JTextField();

    public MathApp(String name) {
        super(name);
        setResizable(false);
    }

    public void addComponentsToPane(final Container pane) {
        final JPanel topComps = new JPanel();
        nonEditTxt.setText("12");
        //nonEditTxt.setEditable(false);
        Label memLabel=new Label("1+5 =",Label.LEFT);
        topComps.add(memLabel);
        topComps.add(nonEditTxt);
        final JPanel btnComps = new JPanel();
        JPanel bottomControls = new JPanel();
        bottomControls.setLayout(new GridLayout(4,8));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        int xny = (int)buttonSize.getWidth()+maxGap;//width n height the same
        btnComps.setPreferredSize(new Dimension(xny,xny));

        //Add buttons to experiment with Grid Layout
        int[] nums = { 1,2,3,4,5,6,7,8,9,0 };
        for (int num : nums)
        {
            btnComps.add(new ActionBtn(Integer.toString(num), this));
        }
        btnComps.add(new JButton("Back"));
        btnComps.add(new JButton("Clear"));
        btnComps.add(new JSeparator());

        //Add bottomControls to set up horizontal and vertical gaps
        bottomControls.add(new Label("Score:1/3"));
        bottomControls.add(applyButton);

        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                memLabel.setText("Swag");
                nonEditTxt.setText("12");
                createAndShowGUI();
                //Get the horizontal gap value
                //String horGap = (String)horGapComboBox.getSelectedItem();
                //Set up the horizontal gap value
                //experimentLayout.setHgap(Integer.parseInt(horGap));
                //Set up the layout of the buttons
                //experimentLayout.layoutContainer(btnComps);
            }
        });
        pane.add(topComps, BorderLayout.NORTH);
        pane.add(btnComps, BorderLayout.CENTER);
        pane.add(bottomControls, BorderLayout.SOUTH);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        Learn frame = new Learn("Learn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
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
        //setBounds(x,y,width,height);
        this.cl=clc;
        this.cl.add(this);

        addActionListener(this);
        System.out.println("here");
    }
    ////////////////////////////////////////////////
    public void actionPerformed(ActionEvent ev)
    {
        System.out.println("here");
        cl.nonEditTxt.setText("test");
        //char memop=((MathApp)ev.getSource()).getLabel().charAt(1);
        //cl.nonEditTxt.setText(memop);
    }//actionPerformed
}