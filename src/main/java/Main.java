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
                result.add(String.valueOf(sb));
                initializeSymb(text, symbols, importance, i);
            } else {
                initializeSymb(text, symbols, importance, i);
            }
        }

//        for (String s : result){
//            System.out.println(s);
//        }

        for (int i : importance){
            System.out.println(i);
        }

    }

    private static void initializeSymb(String text, ArrayList<Character> symbols, ArrayList<Integer> importance, int i) {
        int imp = 0;
        switch (text.charAt(i)){
            case '=':
                imp = 0;
                break;
            case '+':
            case '-':
                imp = 1;
                break;
            case '/':
            case '*':
                imp = 2;
                break;
            case '^':
                imp = 3;
                break;
            case '(':
            case ')':
                imp = 4;
                break;
        }
        importance.add(imp);
        symbols.add(text.charAt(i));
    }
}
