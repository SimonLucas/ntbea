package ntbea.params;

public class DoubleParam extends Param {
    double[] a;

    public Param setArray(double[] a) {
        this.a = a;
        return this;
    }

    public Object getValue(int i) {
        return a[i];
    }
}
