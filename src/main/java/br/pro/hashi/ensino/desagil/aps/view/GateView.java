package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {

    private final Gate gate;

    private final JCheckBox inputAField;
    private final JCheckBox inputBField;
    private final JCheckBox resultField;
    private final Switch buttonA = new Switch();
    private final Switch buttonB = new Switch();

    public GateView(Gate gate) {

        this.gate = gate;

        inputAField = new JCheckBox();
        inputBField = new JCheckBox();
        resultField = new JCheckBox();

        JLabel inputAlabel = new JLabel("Input A");
        JLabel inputBlabel = new JLabel("Input B");
        JLabel resultLabel = new JLabel("Result");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if(this.gate.getInputSize()==1){
            add(inputAlabel);
            add(inputAField);

            add(resultLabel);
            add(resultField);
            inputAField.addActionListener(this);
            this.gate.connect(0,buttonA);
        }

        else{
            add(inputAlabel);
            add(inputAField);
            add(inputBlabel);
            add(inputBField);

            add(resultLabel);
            add(resultField);
            inputAField.addActionListener(this);
            inputBField.addActionListener(this);
            this.gate.connect(0,buttonA);
            this.gate.connect(1,buttonB);
        }
        resultField.setEnabled(false);
        update();
    }

    private void update() {
        if(inputAField.isSelected()){
            buttonA.turnOn();
        }
        else{
            buttonA.turnOff();
        }
        if(inputBField.isSelected()){
            buttonB.turnOn();
        }
        else{
            buttonB.turnOff();
        }
        resultField.setSelected(gate.read());
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
