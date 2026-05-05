package drawer;

public enum Colors {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BRIGHT_RED("\u001B[91m"),
    BRIGHT_GREEN("\u001B[92m"),
    RESET("\u001B[0m");

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}