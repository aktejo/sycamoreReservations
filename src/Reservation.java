import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private String name;
    private int partySize;
    private double timeMade;
    private double timeFor;
    private boolean waitlisted;
    private int restaurantCapacity;
    private int rNumber;


    public Reservation(int resNumber) {
        partySize = (int)(Math.random()*10+1);
        timeMade = Math.random()*20;
        timeFor = Math.random()*6.5*1+16.5;
        rNumber = resNumber;
    }



    public void timeStuff() {
        System.out.println("\n\n\n\n\n\n\n\n\n");
        long now = new Date().getTime();
        System.out.println(now);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd EEEE kk:mm:ss");
        String timeString = dateFormat.format(now);
        System.out.println(timeString);

        long future = new Date().getTime() + 604800000;
        String futureString = dateFormat.format(future);
        System.out.println(futureString);
        System.out.println(future > now);

        String min = "16:00:00";
        String max = "23:00:00";
        try {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss:);");
            Date minHour = timeFormat.parse(min);
            Date maxHour = timeFormat.parse(max);

            future = new Date().getTime() + (int)(Math.random() * 10000000);
            String futureStringTime = timeFormat.format(future);
            Date checkTimeInRange = timeFormat.parse(futureStringTime);
            System.out.println("time to check: " + checkTimeInRange);
            System.out.println(minHour.before(checkTimeInRange) && maxHour.after(checkTimeInRange));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    public int getPartySize() {
        return partySize;
    }

    public double getTimeMade() {
        return timeMade;
    }

    public double getTimeFor() {
        return timeFor;
    }
    public void printInfo() {
        System.out.println("Reservation for " + partySize + " at " + ((int)(timeFor)%12) + ":" + ((int)((timeFor%1)*60)) + " made at " + (int)(timeMade) + ":" + ((int)((timeMade%1)*60)));
    }
    public String returnInfo() {
        return ("Reservation for " + partySize + " at " + ((int)(timeFor)%12) + ":" + ((int)((timeFor%1)*60)) + " made at " + (int)(timeMade) + ":" + ((int)((timeMade%1)*60)));
    }


}
