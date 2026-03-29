package Collection;

public enum Color {
    GREEN,
    RED,
    BLACK,
    YELLOW,
    ORANGE;

    public static Color fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Вы не ввели тип цвета!");
        }
        try {
            return valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Недопустимый тип цвета");
        }
    }
}
