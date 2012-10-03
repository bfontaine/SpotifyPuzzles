import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

/* Simple class to represent basic dates (day/month/year),
 * easier to manipulate than 3-integers arrays.
 */
class BasicDate {
    public int d;
    public int m;
    public int y;

    public BasicDate(int _d, int _m, int _y) {
        d = _d;
        m = _m;
        y = _y;
    }

    public boolean isEarlierThan(BasicDate other) {
        if (this.y > other.y)
            return false;

        if (this.m > other.m)
            return false;

        return (this.d < other.d);
    }
}

public class BestBefore {

    private static Scanner sc = null;

    public static void main (String[] args) {
        System.out.println(bestBefore(readInput()));
    }

    public static String readInput() {
        if (sc == null) {
            sc = new Scanner(System.in);
        }

        return sc.nextLine();
    }

    public static int[] splitDate(String date) {
        String[] parts = date.split("/");

        return new int[] {
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2])
        };
    }

    // return true if `y` can be a year
    // (higher than, or equal to 2000)
    public static boolean canBeAYear(int y) {
        return (   (y >= 2000 && y < 3000)
                || (y >= 0    && y < 1000));
    }

    // return true if `y` is a leap year
    public static boolean isALeapYear(int y) {
        // isALeapYear(Y) == isALeapYear(Y+2000) // if Y < 1000,
        // so we don't have to call toFourDigitsYear(y) before
        // doing the test
        return ((y%4 == 0 && y%100 != 0 ) || y%400 == 0);
    }

    // return the year `y` with four digits
    // (we assume that the year is correct)
    public static int toFourDigitsYear(int y) {
        return (y < 100) ? 2000+y : y;
    }

    // return the number of days of the month `m`, given
    // the year `y`
    public static int getDaysNumber(int m, int y) {
        switch(m) {
            case 2:
                return isALeapYear(y) ? 29 : 28;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            default:
                return 30;
        }
    }

    // return true if `m` can be a month
    public static boolean canBeAMonth(int m) {
        return (m > 0 && m <= 12);
    }

    // return true if `d` can be a day, given the month (`m`)
    // and the year (`y`)
    public static boolean canBeADay(int d, int m, int y) {
        return (   canBeAMonth(m)
                && canBeAYear(y)
                && d > 0
                && d <= getDaysNumber(m, y));
    }

    // format a date for output
    public static String format(BasicDate date) {

        // shortcuts
        int d = date.d,
            m = date.m,
            y = date.y;

        String d_string = (d < 10)  ?  "0"+d : ""+d,
               m_string = (m < 10)  ?  "0"+m : ""+m,

               y_string = (y < 100)
                            ? (y < 10)
                                ? "200"+y
                                : "20"+y
                            : ""+y;


        return y_string+"-"+m_string+"-"+d_string;
    }

    public static BasicDate getEarliestDate(LinkedList<BasicDate> dates) {

        BasicDate earliest = dates.pop();

        for (BasicDate d : dates) {
            if (d.isEarlierThan(earliest))
                earliest = d;
        }

        return earliest;
    }

    // see http://www.spotify.com/fr/jobs/tech/best-before/
    public static String bestBefore(String input) {

        int[] numbers = splitDate(input);

        LinkedList<BasicDate> possibilities = new LinkedList<BasicDate>();

        for (int i=0, d, m, y; i<3; i++) {

            d = numbers[i];       // day (or year, if it cannot be a day)
            m = numbers[(i+1)%3]; // month
            y = numbers[(i+2)%3]; // year (or day, if it cannot be a year)


            // we need to store each possibility to check later
            // which date is the earliest one

            if (canBeADay(d, m, y)) {
                possibilities.push(new BasicDate(d,m,y));
            }

            if (canBeADay(y, m, d)) {
                possibilities.push(new BasicDate(y,m,d));
            }
        }

        if (possibilities.isEmpty())
            return input+" is illegal";

        return format(getEarliestDate(possibilities));
    }
}
