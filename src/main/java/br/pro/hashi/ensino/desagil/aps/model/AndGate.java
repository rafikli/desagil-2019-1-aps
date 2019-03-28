package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate[] nandList;

    public AndGate() {

        super(2);
        nandList = new NandGate[2];

        nandList[0] = new NandGate();
        nandList[1] = new NandGate();
        nandList[1].connect(0,nandList[0]);
        nandList[1].connect(1,nandList[0]);

    }

    @Override
    public boolean read() {
        return nandList[1].read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        nandList[0].connect(inputPin, emitter);

    }
}
