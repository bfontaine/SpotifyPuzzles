import java.util.Scanner;

class TicketLottery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(getProbabilities(sc.nextLine()));
    }

    public static String getProbabilities(String input) {

        double[] numbers = splitNumbers(input);

        /* m : total # of people
         * n : # of winners
         * t : # tickets per winners
         * p : people in the team
         */

        int m = numbers[0],
            n = numbers[1],
            t = numbers[2],
            p = numbers[3];

        if (m == n) return "1";

        // not enough tickets
        if (t*n < p) return "0";

        // needed # of winners
        int w = p / t + 1;

        if (w > n) return "0";

        return "";
    }

    private static double[] splitNumbers(String input) {
        String[] parts = input.split(" ");

        return new double[] {
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3])
        };
    }
}
