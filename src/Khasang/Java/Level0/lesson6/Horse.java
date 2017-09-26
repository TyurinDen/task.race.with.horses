package Khasang.Java.Level0.lesson6;

public class Horse extends Animal { //не final, так как у этой "лошади" может быть потомок "беговая лошадь"
    private int maxSpeed;
    private float stamina = 1;//выносливость

    public Horse(String name, String breed, String color, float weight, int age, int maxSpeed, float stamina) {
        super(name, AnimalType.HORSE, breed, color, weight, age, new Running(), new HorseNigh());
        setMaxSpeed(maxSpeed);
        setStamina(stamina);
    }

    @Override
    protected String getInfo() {
        return String.format("[Horse] [%s], [%s], [%s], [%,4.1f kg], [%3d month(s)], [%s at max speed %3d m/s]",
                name, breed, color, weight, age, moveBehavior.move(), maxSpeed);
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public float getStamina() {
        return stamina;
    }

    public void setMaxSpeed(int maxSpeed) {
        //if (maxSpeed <= 0) throw new IllegalArgumentException("INCORRECT ARGUMENTS");
        if (maxSpeed <= 0) maxSpeed = 15; //TODO насколько это лучше предыдущей строки?
        else this.maxSpeed = maxSpeed;
    }

    public void setStamina(float stamina) {
        //if (stamina <= 0) throw new IllegalArgumentException("INCORRECT ARGUMENTS");
        if (stamina <= 0) stamina = 1; //TODO насколько это лучше предыдущей строки?
        else this.stamina = stamina;
    }

    @Override
    public String toString() {
        return String.format("Horse \"%s\", '%s', %s, %,5.1f kg, %3d month(s), %3df m/s",
                name, breed, color, weight, age, maxSpeed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horse)) return false;
        if (!super.equals(o)) return false;

        Horse horse = (Horse) o;

        if (getMaxSpeed() != horse.getMaxSpeed()) return false;
        return Float.compare(horse.getStamina(), getStamina()) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getMaxSpeed();
        result = 31 * result + (getStamina() != +0.0f ? Float.floatToIntBits(getStamina()) : 0);
        return result;
    }
}