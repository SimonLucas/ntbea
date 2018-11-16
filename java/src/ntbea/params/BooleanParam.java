package ntbea.params;

public class BooleanParam extends Param {
    boolean[] a;

    public Param setArray(boolean[] a) {
        this.a = a;
        return this;
    }

    public Object getValue(int i) {
        return a[i];
    }
}
