import java.util.ArrayList;

import static java.lang.Character.isDigit;

/**
 * Created by Никита on 18.08.2016.
 */
public class Main {
    public static void main(String[] args) {

        String text = "2 + 3";
        String s = "(2 / 3 + 1)";
        converterToPolish(s);
    }

    private static void converterToPolish(String text) {
        text = text.replaceAll(" ", "");
        System.out.println(text + " - содержимое входной строки");

        ArrayList<String> result = mainInitialization(text);
    }

    private static ArrayList<String> mainInitialization(String text) {
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

//            initializeResult(result, symbols, importance);
        }

//        for (String s : result){
//            System.out.print(" " + s);
//        }

//        for (int i : importance){
//            System.out.print(i + " ");
//        }

//        for (char ch : symbols){
//            System.out.print(ch + " ");
//        }

        return result;
    }

    private static void initializeResult(ArrayList<String> result, ArrayList<Character> symbols, ArrayList<Integer> importance) {
        if (importance.size() < 2){
            return;
        }

        int last = importance.get(importance.size() - 1);
        int preLast = importance.get(importance.size() - 2);
        while (!(last > preLast)){
            if (importance.size() < 2){
                break;
            }
            else if (last == 4 && preLast == 4){
                importance.remove(importance.size() - 1);
                importance.remove(importance.size() - 1);
                symbols.remove(symbols.size() - 1);
                symbols.remove(symbols.size() - 1);
            } else {
                char tmp = symbols.get(symbols.size() - 2);
                symbols.remove(symbols.size() - 2);
                result.add(String.valueOf(tmp));
            }
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
