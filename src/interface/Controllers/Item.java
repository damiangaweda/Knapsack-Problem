package Controllers;

public class Item {
    private String name;
    private Double value;
    private Double weight;

    public Item(String name, Double value, Double weight) {
        setName(name);
        setValue(value);
        setWeight(weight);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}