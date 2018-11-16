package ntbea.params;

public abstract class Param {
    String name;

    public Param setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }


    public abstract Object getValue(int i);
}
