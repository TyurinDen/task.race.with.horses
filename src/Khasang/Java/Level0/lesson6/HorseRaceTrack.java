package Khasang.Java.Level0.lesson6;

import java.util.Arrays;

public class HorseRaceTrack {
    private int[] horseRaceTrack;
    private int distance;
    private int numberOfBarriers;

    public HorseRaceTrack(int distance, int numberOfBarriers) {
        this.distance = distance;
        this.numberOfBarriers = numberOfBarriers;
        horseRaceTrack = generateHorseRaceTrack(distance, numberOfBarriers);
    }

    private int[] generateHorseRaceTrack(int distance, int numberOfBarriers) {
        if (distance <= 0 || numberOfBarriers < 0) throw new IllegalArgumentException("INCORRECT ARGUMENTS");
        if (numberOfBarriers == 0) return new int[] { distance };

        int raceTrack[] = new int[numberOfBarriers];
        Arrays.fill(raceTrack, distance / numberOfBarriers);
        raceTrack[raceTrack.length -1] += distance % numberOfBarriers;
        return raceTrack;
    }

    public int[] getHorseRaceTrack() {
        return horseRaceTrack;
    }

    public int getDistance() {
        return distance;
    }

    public int getNumberOfBarriers() {
        return numberOfBarriers;
    }
}

