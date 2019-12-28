package toolbox;


public class Time {

    public static final long SECOND = 1000000000L;

    private static double delta;


    public Time() {
    }

    public static float getTimeSecond() {
        return (float) System.nanoTime()/SECOND;
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        Time.delta = delta;
    }

}