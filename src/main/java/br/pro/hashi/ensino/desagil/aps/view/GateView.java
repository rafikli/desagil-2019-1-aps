package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class GateView extends FixedPanel implements ActionListener, MouseListener, ItemListener {

    private final Switch[] switches;
    private final Gate gate;
    private final Light light;
    private final JCheckBox[] inputBoxes;
    private final Image image;

    public GateView(Gate gate) {
        super(200, 150);
        this.gate = gate;
        light = new Light();
        light.connect(0,gate);
        light.setR(255);

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();
            gate.connect(i, switches[i]);
        }

        if(inputSize>1) {
            add(inputBoxes[0], 20, 52, 17, 20);
            add(inputBoxes[1], 20, 82, 17, 20);
            inputBoxes[0].addActionListener(this);
            inputBoxes[1].addActionListener(this);
        }
        else{
            add(inputBoxes[0], 20, 67, 17, 20);
            inputBoxes[0].addActionListener(this);
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }
        addMouseListener(this);
        update();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Color color;
        int x = event.getX();
        int y = event.getY();

        if (Math.pow((x-(160+12.5)),2) + Math.pow((y-(65+12.5)),2) <= Math.pow(25/2,2)) {

            color = JColorChooser.showDialog(this, null,  new Color(light.getR(), light.getG(), light.getB()));
            if (color != null) {
                light.setB(color.getBlue());
                light.setG(color.getGreen());
                light.setR(color.getRed());
            }
            repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent event) {
    }

    @Override
    public void mouseReleased(MouseEvent event) {
    }

    @Override
    public void mouseEntered(MouseEvent event) {
    }

    @Override
    public void mouseExited(MouseEvent event) {
    }
    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        repaint();

    }
    @Override
    public void paintComponent(Graphics g) {
        Color color = new Color(light.getR(),light.getG(),light.getB());
        super.paintComponent(g);
        g.drawImage(image, 30, 40, 150, 75, this);
        g.setColor(color);
        g.fillOval(160, 65, 25, 25);
        getToolkit().sync();
    }
    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }
}
