package setone;

public class ReverseText {
    public static String reverse(String input) {
        if ( (input == null) || (input.equals("")) )
            return "";

        char[] array = input.toCharArray();
        int mid = array.length/2;

        for(int i = 0; i <= mid; i++) {
            char c = array[i];
            array[i] = array[array.length -1 -i];
            array[array.length -1 -i] = c;
        }


        return String.valueOf(array);
    }

}
