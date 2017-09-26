package Khasang.Java.Level0.lesson6;

public enum AnimalType {
    HORSE("HORSE"),
    CAMEL("CAMEL"),
    DOG("DOG"),
    JERBOA("JERBOA");

    private final String animalDesignation;

    AnimalType(String animalDesignation) {
        this.animalDesignation = animalDesignation;
    }

    public String getAnimalDesignation() {
        return animalDesignation;
    }
}
