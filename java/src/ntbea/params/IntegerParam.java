package ntbea.params;

public class IntegerParam extends Param {
    int[] a;

    public Param setArray(int[] a) {
        this.a = a;
        return this;
    }

    public Object getValue(int i) {
        return a[i];
    }
}
