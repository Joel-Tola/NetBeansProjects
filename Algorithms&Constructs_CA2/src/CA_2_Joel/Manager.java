package CA_2_Joel;

public abstract class Manager {
    protected String name;

    public Manager(String name) {
        this.name = name;
    }

    public abstract String getManagerType();

    // Getter
    public String getName() {
        return name;
    }
}

