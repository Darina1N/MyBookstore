package sk.kosickaakademia.kolesarova.bookstore.enumerator;

public enum Genre { //žáner
    Educational(0), Poetry(1), Novels(2), Detective_stories(3), Fantasy(4), Fairy_tales(5);

    private int value;

    Genre(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
