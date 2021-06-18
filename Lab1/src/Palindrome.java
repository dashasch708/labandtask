public class Palindrome {
    public static void main(String[] args) {
        String [] s = new String []{
                "java", "Palindrome", "madam", "racecar", "apple", "kayak", "song", "noon"
        };
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i] + " - " + isPalindrome(s[i]));
        }

    }

    public static String reverseString (String s){
       String str = new String();
       for (int i = s.length() - 1; i >= 0; i--){
           str += s.charAt(i);
       }
       return str;
    }

    public static boolean isPalindrome(String s){
        return s.equalsIgnoreCase(reverseString(s));
    }
}