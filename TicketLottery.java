import java.util.Scanner;

class TicketLottery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(getProbabilities(sc.nextLine()));
    }

    public static String getProbabilities(String input) {

        int[] numbers = splitNumbers(input);

        if (!enoughTickets(numbers))
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
    private static boolean enoughTickets(int[] numbers) {
        // number of winners * number of tickets per winner >= |group|
        return (numbers[1]*numbers[2] >= numbers[3]);
    }
}
