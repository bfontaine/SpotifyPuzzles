import java.util.Scanner;

public class ReversedBinary {
    public static long reverseBinary(long n) {
      long reversed = 0;

      while (n > 0) {
        long rem = n % 2;
        n /= 2;

        reversed = (reversed * 2 + rem);
      }

      return reversed;
    }

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(reverseBinary(Long.parseLong(sc.nextLine())));
    }
}
