package pl.pjatk.tpo.tpo_6.model;

public class CarVersion {
    private int id;
    private int modelId;
    private String engine;
    private int horsepower;
    private String transmission;

    public CarVersion() {}

    public CarVersion(int id, int modelId, String engine, int horsepower, String transmission) {
        this.id = id;
        this.modelId = modelId;
        this.engine = engine;
        this.horsepower = horsepower;
        this.transmission = transmission;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }
}
