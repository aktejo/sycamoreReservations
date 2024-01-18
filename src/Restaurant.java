import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Restaurant implements ActionListener {

    public ArrayList<Reservation> reservations = new ArrayList<>();
    private JPanel titlePanel;
    private JPanel bodyPanel;
    private JFrame mainFrame;
    private JPanel currentResPanel;
    private JPanel newResPanel;
    private JPanel sortButtonPanel;
    private JButton addPeopleButton;
    private JButton addTimeForButton;
    private JButton timeForSortButton;
    private JButton timeMadeSortButton;
    private JButton numPeopleSortButton;
    private JButton makeReservationButton;
    private JTextField numPeopleField;
    private JTextField timeForTextField;
    private JTextField nameTextField;
    private JTextArea currentResLabel;
    private JLabel titleLabel;
    private JLabel bodyLabel;
    private JLabel currentResLabel2;
    private JLabel newResLabel;
    private JLabel sortButtonLabel;


    public Restaurant() {
        //call methods
        addReservation();
        displayReservations();
        sortBySize();
        System.out.println();
        System.out.println("sortbysize");
        displayReservations();
        sortByTimeMade();
        System.out.println();
        System.out.println("sortbytimemade");
        displayReservations();
        sortByTimeFor();
        System.out.println();
        System.out.println("sortbytimefor");
        displayReservations();

        timeStuff();

        mainFrame = new JFrame();
        titlePanel = new JPanel();
        bodyPanel = new JPanel();
        newResPanel = new JPanel();
        sortButtonPanel = new JPanel();
        titleLabel = new JLabel("Sycamore Reservations");
        bodyLabel = new JLabel("body");
        newResLabel = new JLabel("newres");
        sortButtonLabel = new JLabel("sortbutton");
        addPeopleButton = new JButton("Add Party Size");
        addTimeForButton = new JButton("Add Time For");
        timeForSortButton = new JButton("Sort by time for");
        timeMadeSortButton = new JButton("Sort by time made");
        numPeopleSortButton = new JButton("Sort by party size");
        makeReservationButton = new JButton("make reservation");
        addPeopleButton.setSize(20, 10);
        addTimeForButton.setSize(20, 10);
        timeForSortButton.setSize(20, 10);
        timeMadeSortButton.setSize(20, 10);
        numPeopleSortButton.setSize(20, 10);
        makeReservationButton.setSize(20, 10);
        numPeopleField = new JTextField("number of people");
        timeForTextField = new JTextField("time for");
        nameTextField = new JTextField("name");
        currentResLabel = new JTextArea("");
        currentResPanel = new JPanel();

        addPeopleButton.addActionListener(this);
        addTimeForButton.addActionListener(this);
        timeForSortButton.addActionListener(this);
        timeMadeSortButton.addActionListener(this);
        numPeopleSortButton.addActionListener(this);

        mainFrame.setLayout(new BorderLayout(10, 10));
        newResPanel.setLayout(new GridLayout(3, 2));
        bodyPanel.setLayout(new GridLayout(1, 2));
        currentResPanel.setLayout(new BorderLayout(10, 10));
//        sortButtonPanel.setLayout(new BorderLayout(10, 10));

        sortButtonPanel.setLayout(new FlowLayout());

        mainFrame.add(titlePanel, BorderLayout.NORTH);
        mainFrame.add(bodyPanel, BorderLayout.CENTER);
        bodyPanel.add(currentResPanel, BorderLayout.WEST);
        currentResPanel.add(sortButtonPanel, BorderLayout.NORTH);
        currentResPanel.add(currentResLabel, BorderLayout.CENTER);
        updateReservations();
        bodyPanel.add(newResPanel, BorderLayout.EAST);
        newResPanel.add(numPeopleField);
        newResPanel.add(addPeopleButton);
        newResPanel.add(timeForTextField);
        newResPanel.add(addTimeForButton);
        newResPanel.add(nameTextField);
        newResPanel.add(makeReservationButton);
        titlePanel.add(titleLabel);
//        sortButtonPanel.add(timeForSortButton, BorderLayout.EAST);
//        sortButtonPanel.add(timeMadeSortButton, BorderLayout.CENTER);
//        sortButtonPanel.add(timeForSortButton, BorderLayout.WEST);
        sortButtonPanel.add(timeForSortButton);
        sortButtonPanel.add(timeMadeSortButton);
        sortButtonPanel.add(numPeopleSortButton);





        mainFrame.pack();
        mainFrame.setSize(1600, 800);
        mainFrame.setVisible(true);


    }

    public void timeStuff() {
        System.out.println("\n\n\n\n\n\n\n\n\n");
        long now = new Date().getTime();
        System.out.println("-1 " + now);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd EEEE kk:mm");
        String timeString = dateFormat.format(now);
        System.out.println("0 " + timeString);

        long future = new Date().getTime() + 604800000;
        String futureString = dateFormat.format(future);
        System.out.println("1 " + futureString);
        System.out.println("2 " + (future > now));

        String min = "16:00";
        String max = "23:00";
        try {
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date minHour = timeFormat.parse(min);
            Date maxHour = timeFormat.parse(max);

            future = new Date().getTime() + (int)(Math.random() * 10000000);
            System.out.println("3 " + future);
            String futureStringTime = timeFormat.format(future);
            System.out.println("4 " + futureStringTime);
            Date checkTimeInRange = timeFormat.parse(futureStringTime);
            System.out.println("time to check: " + checkTimeInRange);
            System.out.println(minHour.before(checkTimeInRange) && maxHour.after(checkTimeInRange));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    public void displayReservations() {
        for (Reservation r : reservations) {
            r.printInfo();
        }
    }

    public void updateReservations() {
        currentResLabel.setText("");
        for (Reservation r : reservations) {
            currentResLabel.setText(currentResLabel.getText() + "\n" + r.returnInfo());
        }
    }

    public void addReservation() {
        for (int i = 0; i < 10; i++) {
            reservations.add(i, new Reservation(i));
        }
    }

    public void sortBySize() {
        Reservation n;
        for (int i = 1; i < reservations.size(); i++) {
            int j = i;
            while(j > 0 && reservations.get(j).getPartySize() < reservations.get(j - 1).getPartySize()) {
                n = reservations.get(j - 1);
                reservations.set(j - 1, reservations.get(j));
                reservations.set(j, n);
                j--;
            }
        }
    }

    public void sortByTimeMade() {
        Reservation minInPartition = null;
        for (int sortingPartitionSize = reservations.size(); sortingPartitionSize > 0; sortingPartitionSize--) {
            Reservation currentMin = reservations.get(reservations.size()-sortingPartitionSize);
            int replaceIndex = reservations.size() - sortingPartitionSize;
            minInPartition = currentMin;
            for (int i = (reservations.size()-sortingPartitionSize); i < reservations.size(); i++) {
                if (reservations.get(i).getTimeMade() < minInPartition.getTimeMade()) {
                    minInPartition = reservations.get(i);
                    replaceIndex = i;
                }
            }
            reservations.set(reservations.size()-sortingPartitionSize, minInPartition);
            reservations.set(replaceIndex, currentMin);
            //reservations.set(reservations.size()-k,currentMin);
            //  reservations.get(reservations.size()-k)=currentMin;
        }
    }

    public void sortByTimeFor() {
        Reservation n;
        for (int i = 1; i < reservations.size(); i++) {
            int j = i;
            while(j > 0 && reservations.get(j).getTimeFor() < reservations.get(j - 1).getTimeFor()) {
                n = reservations.get(j - 1);
                reservations.set(j - 1, reservations.get(j));
                reservations.set(j, n);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        Restaurant sycamore = new Restaurant();
    };

    @Override
    public void actionPerformed(ActionEvent e) {
            Object buttonClicked = e.getSource();
//            if (buttonClicked == button) {
//                System.out.println("button clicked");
//                String nPeople = label.getText();
//                System.out.println("new reservaton for " + nPeople + " people");
//            }
            if (buttonClicked == numPeopleSortButton) {
                System.out.println("sorting by number of people");
                sortBySize();
                updateReservations();
            } else if (buttonClicked == timeForSortButton) {
                System.out.println("sorting by number of people");
                sortByTimeFor();
                updateReservations();
            } else if (buttonClicked == timeMadeSortButton) {
                System.out.println("sorting by number of people");
                sortByTimeMade();
                updateReservations();
            } else if (buttonClicked == makeReservationButton) {

            }

    }

}