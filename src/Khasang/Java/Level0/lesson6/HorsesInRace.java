package Khasang.Java.Level0.lesson6;

import java.util.*;

public class HorsesInRace {
    private String teamName;
    private List<Animal> animals = new ArrayList<Animal>();

    public HorsesInRace(String teamName, Animal... raceParticipants) {
        //if (raceParticipants.length == 0) { throw new IllegalArgumentException("PARTICIPANTS OF THE RACE MUST BE GREATER THAN ZERO"); }
        this.teamName = teamName;
        for (Animal animal : raceParticipants) {
            if (!animals.contains(animal)) animals.add(animal);
        }
    }

    public HorsesInRace(String teamName, List<Animal> raceParticipants) {
        //if (raceParticipants.size() == 0) { throw new IllegalArgumentException("PARTICIPANTS OF THE RACE MUST BE GREATER THAN ZERO"); }
        this.teamName = teamName;
        for (Animal animal : raceParticipants) {
            if (!animals.contains(animal)) animals.add(animal);
        }
    }

    public void printInfoOnHorsesInRace() {
        System.out.printf("Team: \"%s\", Participant(s): %d%n", teamName, animals.size());
        for (int i = 0; i < animals.size(); i++) {
            System.out.printf("Participant %2d: %s%n", i + 1, animals.get(i).getInfo());
        }
    }

    public String getInfoOnHorsesInRace() {
        String s = String.format("Team: \"%s\", Participant(s): %d%n", teamName, animals.size());
        for (int i = 0; i < animals.size(); i++) {
            s.concat(String.format("Participant %2d: %s%n", i + 1, animals.get(i).getInfo()));
        }
        return s;
    }

    public int addRaceParticipants(Animal... raceParticipants) {
        int j = 0;
        for (Animal animal : raceParticipants) {
            if (!animals.contains(animal)) {
                animals.add(animal);
                j++;
            }
        }
        return j;
    }

    public int removeRaceParticipants(Animal... raceParticipants) {
        int j = 0;
        for (Animal animal : raceParticipants) {
            if (!animals.contains(animal)) {
                animals.remove(animal);
                j++;
            }
        }
        return j;
    }

    public int getNumberOfRaceParticipants() {
        return animals.size();
    }

    public List<Animal> getRaceParticipants() {
        if (animals.size() == 0) return Collections.EMPTY_LIST;
        return animals;
    }

    public Animal[] getRaceParticipantsAsArray() {
        if (animals.size() == 0) return (Animal[]) Collections.EMPTY_LIST.toArray();
        return (Animal[]) animals.toArray();
    }

    public Animal getRaceParticipant(int index) {
        if (index < 0 | index >= animals.size()) return null;
        return animals.get(index);
    }

    public void sortRaceParticipantsByName() {
        Collections.sort(animals, Comparator.comparing(horse -> horse.getName()));
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getSize() {
        return animals.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HorsesInRace)) return false;

        HorsesInRace that = (HorsesInRace) o;

        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        return animals != null ? animals.equals(that.animals) : that.animals == null;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + (animals != null ? animals.hashCode() : 0);
        return result;
    }
}