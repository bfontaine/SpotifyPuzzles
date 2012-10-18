import java.util.Scanner;

class TicketLottery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(getProbabilities(sc.nextLine()));
    }

    public static String getProbabilities(String input) {

        int[] numbers = splitNumbers(input);

        int lottery_people     = numbers[0],
            winners            = numbers[1],
            tickets_per_winner = numbers[2],
            group_size         = numbers[3];

        if (!enoughTickets(winners, tickets_per_winner, group_size))
            return "0";

        return "";
    }

    private static int[] splitNumbers(String input) {
        String[] parts = input.split(" ");

        return new int[] {
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2]),
            Integer.parseInt(parts[3])
        };
    }

    // test if it’s possible to win enough tickets
    // for the whole group
    private static boolean enoughTickets(int winners,
                                int tickets_per_winner, int group_size) {

        return (winners*tickets_per_winner >= group_size);
    }
}
