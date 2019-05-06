package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeftXor;
    private final NandGate nandTopXor;
    private final NandGate nandBottomXor;
    private final NandGate nandRightXor;
    private final NandGate nandLeftAnd;
    private final NandGate nandRightAnd;

    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandLeftXor = new NandGate();

        nandTopXor = new NandGate();
        nandTopXor.connect(1, nandLeftXor);

        nandBottomXor = new NandGate();
        nandBottomXor.connect(0, nandLeftXor);

        nandRightXor = new NandGate();
        nandRightXor.connect(0, nandTopXor);
        nandRightXor.connect(1, nandBottomXor);

        nandLeftAnd = new NandGate();

        nandRightAnd = new NandGate();
        nandRightAnd.connect(0, nandLeftAnd);
        nandRightAnd.connect(1, nandLeftAnd);
    }

    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nandRightXor.read();
        }
        else{
            return nandRightAnd.read();
        }
    }
    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        nandLeftAnd.connect(inputPin, emitter);
        switch (inputPin) {
            case 0:
                nandTopXor.connect(0, emitter);
                nandLeftXor.connect(0, emitter);
                break;
            case 1:
                nandLeftXor.connect(1, emitter);
                nandBottomXor.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }

}


