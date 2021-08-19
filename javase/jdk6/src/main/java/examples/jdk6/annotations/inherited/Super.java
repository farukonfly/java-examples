package examples.jdk6.annotations.inherited;

@ATable
public class Super {
    private int superx;
    public int supery;

    public Super() {
    }

    private int superX() {
        return 0;
    }

    public int superY() {
        return 0;
    }
}
