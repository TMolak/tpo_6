package pl.pjatk.tpo.tpo_6.model;

public class CarVersion {
    private int id;
    private int modelId;
    private String engine;
    private int horsepower;
    private String transmission;
    private String brandName;
    private String modelName;

    public CarVersion() {}

    public CarVersion(int id, int modelId, String engine, int horsepower, String transmission, String brandName, String modelName) {
        this.id = id;
        this.modelId = modelId;
        this.engine = engine;
        this.horsepower = horsepower;
        this.transmission = transmission;
        this.brandName = brandName;
        this.modelName = modelName;
    }

    @Override
    public String toString() {
        return "CarVersion{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", engine='" + engine + '\'' +
                ", horsepower=" + horsepower +
                ", transmission='" + transmission + '\'' +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                '}';
    }

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

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
