package tasks;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Timer extends Label{
    Timeline timer;
    private long start;
    private long time;
    private int stop;

    public Timer() {
        super("00:00");
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> update()));
    }

    public void start(int stop) {
        this.stop = stop/60;
        //System.out.println("stop at: "+this.stop);
        start = System.currentTimeMillis();
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void update() {
        time = System.currentTimeMillis() - start;
        int minutes = (int)time/60000;
        if(minutes == stop) stop();
        int seconds = (int)(time/1000)%60;
        String timeFormat = String.format("%d:%02d", minutes,seconds);
        //System.out.println(""+timeFormat);
        setText(""+timeFormat);
        setTextFill(Color.ORANGE);
    }

    public String getTime(){
        int minutes = (int)time/60000;
        int seconds = (int)(time/1000)%60;
        return String.format("%d:%02d", minutes,seconds);
    }

    public void stop() {
        timer.stop();
    }
}
