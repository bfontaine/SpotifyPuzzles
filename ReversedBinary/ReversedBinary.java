import java.util.Scanner;

public class ReversedBinary {

    private static Scanner sc = new Scanner(System.in);

    private static String reverse(String s) {

        int len = s.length();
        StringBuffer s2 = new StringBuffer(len);

        for (int i=len-1; i>=0; i--) {
            s2.append(s.charAt(i));
        }

        return s2.toString();

    }

    public static String reverseBinary(String input) {
        
        String binary = Integer.toBinaryString(Integer.parseInt(input, 10));

        return new Integer(Integer.parseInt(reverse(binary), 2)).toString();
    }

    public static void main (String[] args) {
        System.out.println(reverseBinary(sc.nextLine()));
    }


}
