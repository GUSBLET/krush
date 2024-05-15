package krush.ua.system.software.type.enums;

public enum SoftwareType {
    WEB("ВЕБ"),
    WEB_SHELL("ВЕБ-оболонка"),
    DESKTOP_SOFTWARE("ПЗ робочого столу"),
    DESKTOP_SOFTWARE_AND_WEB_SHELL("ПЗ робочого столу та ВЕБ-оболонка");

    private final String russianName;

    SoftwareType(String ukrainianName) {
        this.russianName = ukrainianName;
    }

    public String getUkrainianName() {
        return russianName;
    }
}
