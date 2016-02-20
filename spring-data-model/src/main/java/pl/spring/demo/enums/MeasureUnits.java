package pl.spring.demo.enums;

public enum MeasureUnits {
    KG("kg"),
    SZT("szt."),
    M2("m2"),
    SIXTY_MIN("60 min."),
    KPL("kpl."),;

    private String name;

    MeasureUnits(String name) {
        this.name = name;
    }

    MeasureUnits() {
        name = "";
    }

    String getName() {
        return name;
    }
}
