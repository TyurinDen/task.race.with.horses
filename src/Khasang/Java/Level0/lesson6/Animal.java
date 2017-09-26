package Khasang.Java.Level0.lesson6;

import org.jetbrains.annotations.Contract;

public abstract class Animal {
    protected String name;
    protected final AnimalType animalType;//Собака, кошка, лошадь и тд. предполагаем, что тип Животного меняться не может
    protected final String breed; //порода, пусть тоже не меняется
    protected String color;
    protected float weight;//вес в килограммах
    protected int age; //возраст в месяцах
    protected MoveBehavior moveBehavior;//к паттерну "стратегия"
    protected VoiceBehavior voiceBehavior;//к паттерну "стратегия"

    protected Animal(final String name, AnimalType animalType, String breed, String color,
                     float weight, int age, MoveBehavior moveBehavior, VoiceBehavior voiceBehavior) {
        this.name = name;
        this.animalType = animalType;
        this.breed = breed;
        this.color = color;
        this.weight = weight;
        this.age = age;
        this.moveBehavior = moveBehavior;
        this.voiceBehavior = voiceBehavior;
    }

    protected String getInfo() {
        return String.format("[Animal: %s]; [Name: %s]; [Breed: %s], [Color: %s]; [Weight: %6.1f kg]; [Age:%,3d months]",
                animalType, name, breed, color, weight, age);
    }

    protected final void setName(String name) { this.name = name; }
    protected final void setColor(String color) { this.color = color; }
    protected final void setWeight(float weight) { this.weight = weight; }
    protected final void setAge(int age) { this.age = age; }
    public void setMoveBehavior(MoveBehavior moveBehavior) { this.moveBehavior = moveBehavior; }
    public void setVoiceBehavior(VoiceBehavior voiceBehavior) { this.voiceBehavior = voiceBehavior; }

    @Contract(pure = true)
    protected final String getAnimalType() { return animalType.getAnimalDesignation(); }
    protected final String getName() { return name; }
    protected final String getColor() { return color; }
    protected final float getWeight() { return weight; }
    protected final int getAge() { return age; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;

        Animal animal = (Animal) o;

        if (Float.compare(animal.weight, weight) != 0) return false;
        if (age != animal.age) return false;
        if (name != null ? !name.equals(animal.name) : animal.name != null) return false;
        if (animalType != animal.animalType) return false;
        if (breed != null ? !breed.equals(animal.breed) : animal.breed != null) return false;
        if (color != null ? !color.equals(animal.color) : animal.color != null) return false;
        if (moveBehavior != null ? !moveBehavior.equals(animal.moveBehavior) : animal.moveBehavior != null)
            return false;
        return voiceBehavior != null ? voiceBehavior.equals(animal.voiceBehavior) : animal.voiceBehavior == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (animalType != null ? animalType.hashCode() : 0);
        result = 31 * result + (breed != null ? breed.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + age;
        result = 31 * result + (moveBehavior != null ? moveBehavior.hashCode() : 0);
        result = 31 * result + (voiceBehavior != null ? voiceBehavior.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Animal: %s; Name: %s; Breed: %s, Color: %s; Weight: %6.1f kg; Age:%,3d months",
                animalType, name, breed, color, weight, age);
    }
}
