import java.util.ArrayList;

import static java.lang.Character.isDigit;

/**
 * Created by Никита on 18.08.2016.
 */
public class Main {
    public static void main(String[] args) {

        String text = "2 + 3";
        String s = "(2 + 3)";
        converterToPolish(s);
    }

    private static void converterToPolish(String text) {
        text = text.replaceAll(" ", "");
        System.out.println(text + " - содержимое входной строки");


        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Character> symbols = new ArrayList<Character>();
        ArrayList<Integer> importance = new ArrayList<Integer>();

        for (int i = 0; i < text.length(); i++) {
            StringBuilder sb = new StringBuilder();
            if (isDigit(text.charAt(i))){
                for (int j = i; j < text.length(); j++) {
                    if (isDigit(text.charAt(j))){
                        sb.append(text.charAt(j));
                    } else {
                        i = j;
                        break;
                    }
                }
                symbols.add(text.charAt(i));
                result.add(String.valueOf(sb));
            } else {
                symbols.add(text.charAt(i));
            }
        }

        for (String s : result){
            System.out.print(s);
        }

    }
}
