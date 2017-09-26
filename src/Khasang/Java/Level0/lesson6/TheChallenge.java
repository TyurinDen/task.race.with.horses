package Khasang.Java.Level0.lesson6;

import java.util.*;
import java.util.concurrent.CountDownLatch;

public class TheChallenge {
    private HorseRaceTrack horseRaceTrackObj;
    private List<Animal> participants;
    private List<ResultItem> results;
    private int numberOfBarriers;
    private int distance;
    private int horseRaceTrack[];
    private CountDownLatch latch;

    public TheChallenge(HorseRaceTrack horseRaceTrackObj, HorsesInRace horses) {
        //if (horses.getSize() == 0) throw new IllegalArgumentException("LIST OF PARTICIPANTS OF THE RACE CAN NOT BE EMPTY");
        participants = horses.getRaceParticipants();
        results = new ArrayList<>(horses.getSize());
        this.horseRaceTrackObj = horseRaceTrackObj;
        numberOfBarriers = horseRaceTrackObj.getNumberOfBarriers();
        distance = horseRaceTrackObj.getDistance();
        horseRaceTrack = horseRaceTrackObj.getHorseRaceTrack();
        latch = new CountDownLatch(horses.getSize());
    }

    public List<ResultItem> getResults() {
        if (results.size() == 0) return Collections.EMPTY_LIST;
        return results;
    }

    public void arrangeResults() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("ERROR! Something wrong with multithreading - latch.await()");
            e.printStackTrace();
        }
        Collections.sort(results, Comparator.comparing(r -> r.getTime()));
    }

    public void printWinner() {
        ResultItem resultItem = Collections.max(results, Comparator.comparing(r -> r.getTime()));
        float min = resultItem.getTime();
        for (ResultItem res: results) {
            if (res.getTime() < 0) continue;
            if (res.getTime() < min) {
                resultItem = res;
                min = res.getTime();
            }
        }
        System.out.printf("ПОБЕДИТЕЛЬ: >>> %s <<< с результатом: %s%n", resultItem.getName(), resultItem.getFormatTime());
    }

    public void printResults() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("ERROR! Something wrong with multithreading - latch.await()");
            e.printStackTrace();
        }

        for (ResultItem res: results) {
            if (res.getTime() < 0) System.out.printf("Участник: %10s*, ВЫБЫЛ ИЗ ГОНКИ%n", res.getName());
            else {
                System.out.printf("Участник: %11s, время: %s%n", res.getName(), res.getFormatTime());
            }
        }
    }

    public void launch() {
        for (Animal h: participants) {
            new Thread(new Race((Horse) h)).start();
        }
    }

    private class Race implements Runnable {
        private Horse horse;
        private String horseName;
        private int maxSpeed;
        float stamina, weight;
        private Random random = new Random();

        public Race(Horse horse) {
            this.horse = horse;
            horseName = horse.getName();
            maxSpeed = horse.getMaxSpeed();
            stamina = horse.getStamina();
            weight = horse.getWeight();
        }

        private synchronized float speedReductionValue() {//TODO нужна здесь синхронизация?
            //чтобы вычесленное значение декремента вычислялось именно с параметрами бегущей лошади
            //то есть по моим представлениям если в этот метод одновременно полезут разные лошади,
            //то возникнет каша. Или это не так?
            return ((random.nextInt(60) + (float) maxSpeed / 3) / weight) / stamina;
        }

        private synchronized void saveResult(float time) {
            results.add(new ResultItem(horseName, time));
        }

        private float raceWithBarriers() {
            float speed = maxSpeed;
            float time = 0;
            for (int i = 0; i < numberOfBarriers; i++) {
                time += horseRaceTrack[i] / speed;
                speed = speed - speedReductionValue();
                //System.out.printf("%s, speed = %f%n", Thread.currentThread().getName(), speed);
                if (random.nextInt(20) * random.nextInt(20) == 361) { //лошадь сошла с дистанции, "убившись" о барьер
                    time = -1;
                    break;
                }
            }
            return time;
        }

        private float raceWoBarriers() {
            final int TIME_SLOT = 15;
            int numberOfPoints = distance / (TIME_SLOT * maxSpeed);
            float time;
            float speed = maxSpeed;
            float dist = distance;
            for (int i = 0; i < numberOfPoints; i++) {
                //System.out.printf("length = %.1f, dist = %.1f, speed = %.1f%n", TIME_SLOT * speed, dist, speed);
                dist -= TIME_SLOT * speed;
                speed -= speedReductionValue();
            }
            time = TIME_SLOT * numberOfPoints + dist / speed;
            return time;
        }

        @Override
        public void run() {
            float time;
            if (numberOfBarriers == 0) time = raceWoBarriers();
            else time = raceWithBarriers();
            //System.out.printf("%s, Time = %f%n", Thread.currentThread().getName(), time);
//            try {
//                Thread.sleep(300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            latch.countDown();
            saveResult(time);
        }
    }
    private class ResultItem {
        private String name;
        private Float time;

        public ResultItem(String name, float time) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public float getTime() {
            return time;
        }

        public String getFormatTime() {
            int minutes = time.intValue() / 60;
            float seconds = time.floatValue() - minutes * 60;
            return String.format("%d мин %.2f сек", minutes, seconds);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ResultItem)) return false;

            ResultItem that = (ResultItem) o;

            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return time != null ? time.equals(that.time) : that.time == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (time != null ? time.hashCode() : 0);
            return result;
        }
    }
}