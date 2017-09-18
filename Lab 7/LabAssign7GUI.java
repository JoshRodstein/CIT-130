package Code;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

/**
 * Lab Assignment 7 / FINCH GUI GROUP PROJECT
 * @author Joshua.Rodstein, Tula.Sah, Shawn.Clisby
 */
public class FinchGUI extends JFrame {

    private Finch groupFinch;
    private JPanel framePanel;
    private JPanel sensorPanel;
    private JPanel speechPanel;
    private JPanel colorPanel;
    private JCheckBox[] checkboxes = new JCheckBox[6];
    private JTextField[] editText = new JTextField[6];
    private String[] sensorLabels = {"Right Light Sensor", "Left Light Sensor",
        "Temparature Sensor", "X Acceleration Value", "Y Acceleration Value",
        "Z Acceleration Value"};

    private JLabel enterText = new JLabel("Enter Text:");
    private JButton btnSpeak = new JButton("Speak");
    private JTextField speechText = new JTextField();
    private JButton btnClear = new JButton("Clear");

    private JRadioButton[] jradiobuttons = new JRadioButton[6];
    private String[] colorLabels = {"Red", "Green", "Blue", "Cyan", "Yellow",
        "Pink"};
    private ButtonGroup bg = new ButtonGroup();

    FinchGUI() {
        groupFinch = new Finch();
        framePanel = new JPanel();
        sensorPanel = new JPanel();
        speechPanel = new JPanel();
        colorPanel = new JPanel();

        framePanel.setLayout(new BorderLayout());
        sensorPanel.setLayout(new GridLayout(6, 2, 5, 5));
        speechPanel.setLayout(new GridLayout(2, 2, 10, 10));
        colorPanel.setLayout(new GridLayout(2, 3, 5, 5));

        setSensorPanel();
        setSpeechPanel();
        setBeekColorPanel();
        for (int i = 0; i < 6; i++) {
            bg.add(jradiobuttons[i]);
        }

        add(framePanel);

        if (!this.isEnabled()) {
            groupFinch.quit();
        }

    }

    class SensorPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == checkboxes[0]) {
                if (checkboxes[0].isSelected()) {
                    editText[0].setText(String.valueOf(groupFinch.
                            getRightLightSensor()));
                } else {
                    editText[0].setText("");
                }
            } else if (e.getSource() == checkboxes[1]) {
                if (checkboxes[1].isSelected()) {
                    editText[1].setText(String.valueOf(groupFinch.
                            getLeftLightSensor()));
                } else {
                    editText[1].setText("");
                }
            } else if (e.getSource() == checkboxes[2]) {
                if (checkboxes[2].isSelected()) {
                    editText[2].setText(String.valueOf(groupFinch.
                            getTemperature()));
                } else {
                    editText[2].setText("");
                }
            } else if (e.getSource() == checkboxes[3]) {
                if (checkboxes[3].isSelected()) {
                    editText[3].setText(String.valueOf(groupFinch.
                            getXAcceleration()));
                } else {
                    editText[3].setText("");
                }
            } else if (e.getSource() == checkboxes[4]) {
                if (checkboxes[4].isSelected()) {
                    editText[4].setText(String.valueOf(groupFinch.
                            getYAcceleration()));
                } else {
                    editText[4].setText("");
                }
            } else if (e.getSource() == checkboxes[5]) {
                if (checkboxes[5].isSelected()) {
                    editText[5].setText(String.valueOf(groupFinch.
                            getZAcceleration()));
                } else {
                    editText[5].setText("");
                }
            }
        }
    }

    class SpeechPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnClear) {
                speechText.setText("");
            } else if (e.getSource() == btnSpeak) {
                if (speechText.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No text was enetered", 
                            "Text Missing", JOptionPane.WARNING_MESSAGE);

                } else {
                    groupFinch.saySomething(speechText.getText());
                }
            }

        }
    }

    class ColorPanelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jradiobuttons[0]) {
                if (jradiobuttons[0].isSelected()) {
                    groupFinch.setLED(Color.RED);
                } else {
                    groupFinch.setLED(Color.BLACK);
                }
            } else if (e.getSource() == jradiobuttons[1]) {
                if (jradiobuttons[1].isSelected()) {
                    groupFinch.setLED(Color.GREEN);
                } else {
                    groupFinch.setLED(Color.BLACK);
                }
            } else if (e.getSource() == jradiobuttons[2]) {
                if (jradiobuttons[2].isSelected()) {
                    groupFinch.setLED(Color.BLUE);
                } else {
                    groupFinch.setLED(Color.BLACK);
                }
            } else if (e.getSource() == jradiobuttons[3]) {
                if (jradiobuttons[3].isSelected()) {
                    groupFinch.setLED(Color.CYAN);
                } else {
                    groupFinch.setLED(Color.BLACK);
                }
            } else if (e.getSource() == jradiobuttons[4]) {
                if (jradiobuttons[4].isSelected()) {
                    groupFinch.setLED(Color.YELLOW);
                } else {
                    groupFinch.setLED(Color.BLACK);
                }
            } else if (e.getSource() == jradiobuttons[5]) {
                if (jradiobuttons[5].isSelected()) {
                    groupFinch.setLED(Color.PINK);
                } else {
                    groupFinch.setLED(Color.BLACK);
                }
            }
        }
    }

    private void setSensorPanel() {
        SensorPanelListener listener = new SensorPanelListener();

        for (int i = 0; i < sensorLabels.length; i++) {
            checkboxes[i] = new JCheckBox(sensorLabels[i]);
            checkboxes[i].addActionListener(listener);
            editText[i] = new JTextField();
            editText[i].setEditable(false);

            sensorPanel.add(checkboxes[i]);
            sensorPanel.add(editText[i]);
        }

        sensorPanel.setBorder(BorderFactory.createTitledBorder("Sensor Values"));

        framePanel.add(sensorPanel, BorderLayout.NORTH);

    }

    private void setSpeechPanel() {
        SpeechPanelListener listener = new SpeechPanelListener();

        btnClear.addActionListener(listener);
        btnSpeak.addActionListener(listener);

        enterText.setForeground(Color.red);
        speechPanel.add(enterText);
        speechPanel.add(speechText);
        speechPanel.add(btnSpeak);
        speechPanel.add(btnClear);

        speechPanel.setBorder(BorderFactory.createTitledBorder("Speech"));

        framePanel.add(speechPanel, BorderLayout.CENTER);

    }

    private void setBeekColorPanel() {
        ColorPanelListener listener = new ColorPanelListener();

        for (int i = 0; i < colorLabels.length; i++) {

            jradiobuttons[i] = new JRadioButton(colorLabels[i]);
            jradiobuttons[i].addActionListener(listener);
            colorPanel.add(jradiobuttons[i]);
        }
        jradiobuttons[0].setSelected(true);
        groupFinch.setLED(Color.RED);
        colorPanel.setBorder(BorderFactory.createTitledBorder("Beek Color"));

        framePanel.add(colorPanel, BorderLayout.SOUTH);

    }
}
