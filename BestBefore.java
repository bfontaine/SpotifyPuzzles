import java.util.Scanner;

/* Simple class to represent basic dates (day/month/year),
 * easier to manipulate than 3-integers arrays.
 */
class BasicDate {
    public int d;
    public int m;
    public int y;

    public BasicDate(int _d, int _m, int _y) {
        this.update(_d, _m, _y);
    }

    // earlier than or equals to
    public boolean isEarlierThan(int _d, int _m, int _y) {
        _y = toFourDigitsYear(_y);

        if (this.y < _y)
            return true;

        if (this.y > _y)
            return false;

        if (this.m < _m)
            return true;

        if (this.m > _m)
            return false;

        return (this.d <= _d);
    }

    public void update(int _d, int _m, int _y) {
        d = _d;
        m = _m;
        y = toFourDigitsYear(_y);
    }

    public String toString() {
        String d_string = (d < 10) ? "0"+d : ""+d,
               m_string = (m < 10) ? "0"+m : ""+m,

               y_string = (y < 100)
                            ? (y < 10)
                                ? "200"+y
                                : "20"+y
                            : ""+y;


        return y_string+"-"+m_string+"-"+d_string;
    }

    // return the year `y` with four digits
    // (we assume that the year is correct)
    public static int toFourDigitsYear(int y) {
        return (y < 100) ? 2000+y : y;
    }
}

public class BestBefore {

    private static Scanner sc = new Scanner(System.in);

    public static void main (String[] args) {
        System.out.println(bestBefore(sc.nextLine()));
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
                || (y >= 0    && y < 100));
    }

    // return true if `y` is a leap year
    public static boolean isALeapYear(int y) {
        // isALeapYear(Y) == isALeapYear(Y+2000) for Y < 1000,
        // so we don't have to call BasicDate.toFourDigitsYear(y) before
        // doing the test
        return ((y%4 == 0 && y%100 != 0 ) || y%400 == 0);
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
        // quicker tests first
        return (   d > 0
                && canBeAMonth(m)
                && canBeAYear(y)
                && d <= getDaysNumber(m, y));
    }

    // see http://www.spotify.com/fr/jobs/tech/best-before/
    public static String bestBefore(String input) {

        int[] numbers = splitDate(input);

        BasicDate earliest = null;

        for (int i=0, d, m, y; i<3; i++) {

            d = numbers[i];       // day
            m = numbers[(i+1)%3]; // month (or year, if it cannot be a month)
            y = numbers[(i+2)%3]; // year (or month, if it cannot be a year)

            // we need to store each possibility to check later
            // which date is the earliest one

            if (canBeADay(d, m, y)) {
                if (earliest == null) {
                    earliest = new BasicDate(d, m, y);
                }
                else if (!earliest.isEarlierThan(d, m, y)) {
                    earliest.update(d, m, y);
                }
            }

            if (canBeADay(d, y, m)) {
                if (earliest == null) {
                    earliest = new BasicDate(d, y, m);
                }
                else if (!earliest.isEarlierThan(d, y, m)) {
                    earliest.update(d, y, m);
                }
            }
        }

        return (earliest == null) ? input+" is illegal" : earliest.toString();
    }
}
