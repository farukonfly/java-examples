package examples.jdk6.annotations.inherited;

@BTable(name = "Sub_@BTable")
public class Sub extends Super {
    private int subx;
    public int suby;

    private Sub() {
    }

    public Sub(int i) {
    }

    private int subX() {
        return 0;
    }

    public int subY() {
        return 0;
    }
}
