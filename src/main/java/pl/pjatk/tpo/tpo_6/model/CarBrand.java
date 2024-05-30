package pl.pjatk.tpo.tpo_6.model;

public class CarBrand {
    private int id;
    private String name;

    public CarBrand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarBrand() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

