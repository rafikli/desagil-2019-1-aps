package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate[] nandList;

    public OrGate() {

        super(2);
        nandList = new NandGate[3];

        nandList[0] = new NandGate();
        nandList[1] = new NandGate();
        nandList[2] = new NandGate();
        nandList[2].connect(1,nandList[0]);
        nandList[2].connect(0,nandList[1]);

    }

    @Override
    public boolean read() {
        return nandList[2].read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        nandList[inputPin].connect(0, emitter);
        nandList[inputPin].connect(1, emitter);
    }
}

