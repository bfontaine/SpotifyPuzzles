import java.util.Scanner;

class TicketLottery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(getProbabilities(sc.nextLine()));
    }

    public static String getProbabilities(String input) {

        double[] numbers = splitNumbers(input);

        double lottery_people     = numbers[0],
               winners            = numbers[1],
               tickets_per_winner = numbers[2],
               group_size         = numbers[3];

        if (lottery_people == winners)
            return "1";

        if (!enoughTickets(winners, tickets_per_winner, group_size))
            return "0";

        int winners_needed = (int)Math.ceil(group_size/tickets_per_winner);

        if (winners_needed > winners)
            return "0";

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

    // test if it’s possible to win enough tickets
    // for the whole group
    private static boolean enoughTickets(double winners,
                                double tickets_per_winner, double group_size) {

        return (winners*tickets_per_winner >= group_size);
    }
}
