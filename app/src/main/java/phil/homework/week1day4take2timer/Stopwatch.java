package phil.homework.week1day4take2timer;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.TimeUnit;

public class Stopwatch implements Parcelable {
    private boolean running;
    private long startTime;
    private long elapsedTime;

    public Stopwatch() {
        running = false;
        startTime = 0L;
        elapsedTime = 0L;
    }

    protected Stopwatch(Parcel in) {
        running = in.readByte() != 0;
        startTime = in.readLong();
        elapsedTime = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (running ? 1 : 0));
        dest.writeLong(startTime);
        dest.writeLong(elapsedTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Stopwatch> CREATOR = new Creator<Stopwatch>() {
        @Override
        public Stopwatch createFromParcel(Parcel in) {
            return new Stopwatch(in);
        }

        @Override
        public Stopwatch[] newArray(int size) {
            return new Stopwatch[size];
        }
    };

    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }

    public void pause() {
        elapsedTime = System.currentTimeMillis() - startTime;
        running = false;
    }

    public void resume() {
        start();
    }

    public void stop() {
        pause();
    }

    public void reset() {
        elapsedTime = 0L;
    }

    public boolean isRunning() {
        return running;
    }

    public long getElapsedTime() {
        if(running) {
            return System.currentTimeMillis() - startTime + elapsedTime;
        } else {
            return elapsedTime;
        }
    }

    public String printElapsedTime() {
        long t = getElapsedTime();
        int seconds = (int) (t / 1000) % 60 ;
        int minutes = (int) ((t / (1000*60)) % 60);
        int hours   = (int) ((t / (1000*60*60)) % 24);
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static void main(String[] args) {
        Stopwatch testWatch = new Stopwatch();
        testWatch.start();
        pauseExecution(3);
        testWatch.logTime();
        testWatch.pause();
        pauseExecution(2);
        testWatch.logTime();
        testWatch.resume();
        pauseExecution(3);
        testWatch.logTime();
        testWatch.stop();
        pauseExecution(2);
        testWatch.logTime();
        testWatch.reset();
        testWatch.logTime();
    }

    private static void pauseExecution(int seconds){
        try{TimeUnit.SECONDS.sleep((long)seconds);}
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void logTime() {
        System.out.println(printElapsedTime());
    }

}
