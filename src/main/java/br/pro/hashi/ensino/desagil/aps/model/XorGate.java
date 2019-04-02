package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
<<<<<<< HEAD
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandRight;


    public XorGate() {
        super("XOR", 2);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandBottom = new NandGate();
        nandBottom.connect(0, nandLeft);

        nandRight = new NandGate();
        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);
    }


    @Override
    public boolean read() {
        return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
=======
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
>>>>>>> c7c6e06c2bab516d1cd5524b7e33a8f302b194b1
    }
}
