package Collection;

public enum Difficulty {
    EASY,
    NORMAL,
    IMPOSSIBLE;

    public static Difficulty fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Вы не ввели тип сложности!");
        }
        try {
            return valueOf(value.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Недопустимый тип сложности");
        }
    }
}