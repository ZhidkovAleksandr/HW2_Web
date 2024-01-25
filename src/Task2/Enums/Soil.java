package Task2.Enums;

public enum Soil {
    PODZOLIC("podzolic"),
    GROUND("ground"),
    SOD_PODZOLIC("sod-podzolic");


    private String title;

    Soil(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
