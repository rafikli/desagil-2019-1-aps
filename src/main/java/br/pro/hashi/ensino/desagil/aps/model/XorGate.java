package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate[] nandList;

    public XorGate() {

        super(2);
        nandList = new NandGate[4];

        nandList[0] = new NandGate();
        nandList[1] = new NandGate();
        nandList[2] = new NandGate();
        nandList[3] = new NandGate();

        nandList[1].connect(1, nandList[0]);
        nandList[2].connect(0,nandList[0] );
        nandList[3].connect(0, nandList[1]);
        nandList[3].connect(1,nandList[2] );
    }




    @Override
    public boolean read() {
        return nandList[3].read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        nandList[0].connect(inputPin,emitter);
        nandList[inputPin+1].connect(inputPin,emitter);
    }
}
