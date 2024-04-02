
//Java program to create a count down timer
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

//class
public class TimerCounter {
    // declare the interval i and timer t
    static int i;
    static Timer t;

    // main method
    public static void main(String[] args) {
        // create object for scanner
        Scanner sc = new Scanner(System.in);
        // input the seconds you want to count down
        System.out.print("Enter the seconds you want to count down : ");
        // save the seconds that is input in to the variable sec
        String sec = sc.nextLine();
        // set delay and period as 1000
        int del = 1000;
        int per = 1000;
        t = new Timer();
        i = Integer.parseInt(sec);
        System.out.println(sec);
        // performs the specifiedd task at certain intervals
        t.scheduleAtFixedRate(new TimerTask() {
            // task to be performed
            public void run() {
                System.out.println(seti());
            }
        }, del, per);
    }

    // set interval
    private static final int seti() {
        // if interval is 1, cancel
        if (i == 1)
            t.cancel();
        return --i;
    }
}