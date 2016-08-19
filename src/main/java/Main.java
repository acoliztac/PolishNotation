import java.util.ArrayList;

import static java.lang.Character.isDigit;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Никита on 18.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("\tИнвертированная польская запись.\n" +
                "Данный алгоритм предназначен для вычисления формул, содержащих арифметические операции:" +
                "\nСложение. Вычитание. Умножение. Деление.\n" +
                "Возведение в степень. Извлечение корня.  ");

        String s = "2 + 1";                         //3.0
        String s1 = "(2 / 3 + 1)";                  //1.(6)
        String s2 = "4 * 5+3";                      //23.0
        String s3 = "1 - 2*3 + 4";                  //-1.0
        String s4 = "1 + 2 * (3 - 4)";              //-1.0
        String s5 = "5 * 3 - 4 ^ 2 +2/(2 -11)";     //1.(2)
        String s6 = "2 * (3-1) - (2 + 1) + 12";     //13.0
//        solution(s6);
        System.out.println(sqrt(9));
    }


    private static void solution(String s) {
        ArrayList<String> polish = converterToPolish(s);
        for (int i = 0; i < polish.size(); i++) {
            try {
                double tmp = Double.parseDouble(polish.get(i));
            } catch (NumberFormatException e) {
                decision(polish, i);
                i -= 2;
            }
        }
        System.out.println(polish.get(0));
    }

    private static void decision(ArrayList<String> polish, int i) {
        double first = Double.parseDouble(polish.get(i - 2));
        double second = Double.parseDouble(polish.get(i - 1));
        if (polish.get(i).equals("+")){
            polish.set(i - 2, String.valueOf(first + second));
        } else if (polish.get(i).equals("-")){
            polish.set(i - 2, String.valueOf(first - second));
        } else if (polish.get(i).equals("*")){
            polish.set(i - 2, String.valueOf(first * second));
        } else if (polish.get(i).equals("/")){
            polish.set(i - 2, String.valueOf(first / second));
        } else if (polish.get(i).equals("^")){
            polish.set(i - 2, String.valueOf(pow(first, second)));
        }
        polish.remove(i);
        polish.remove(i - 1);
    }

    private static ArrayList<String> converterToPolish(String text) {
        text = text.replaceAll(" ", "");
//        System.out.println(text + " - содержимое входной строки");

        ArrayList<String> result = mainInitialization(text);

//        for (String s : result){
//            System.out.print(s + " ");
//        }
//        System.out.println(" - польская запись");
        return result;
    }

    private static ArrayList<String> mainInitialization(String text) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<Character> symbols = new ArrayList<Character>();
        ArrayList<Integer> importance = new ArrayList<Integer>();

        for (int i = 0; i < text.length(); i++) {
            StringBuilder sb = new StringBuilder();
            if (isDigit(text.charAt(i))){
                for (int j = i; j < text.length(); j++) {
                    i = j;
                    if (isDigit(text.charAt(j))){
                        sb.append(text.charAt(j));
                    } else {
                        break;
                    }
                }
                result.add(String.valueOf(sb));
                if (i == text.length() - 1 && isDigit(text.charAt(i))){
                    break;
                }
                initializeSymb(text, symbols, importance, i);
            } else {
                initializeSymb(text, symbols, importance, i);
            }

            initializeResult(result, symbols, importance);

        }
        for (int i = symbols.size() - 1; i >= 0; i--) {
            result.add(String.valueOf(symbols.get(i)));
        }

//        System.out.println("result");
//        for (String s : result){
//            System.out.print(s);
//        }
//
//        System.out.println("importance");
//        for (int i : importance){
//            System.out.print(i + " ");
//        }
//        System.out.println("");
//
//        System.out.println("symbols");
//        for (char ch : symbols){
//            System.out.print(ch + " ");
//        }

        return result;
    }

    private static void initializeResult(ArrayList<String> result, ArrayList<Character> symbols, ArrayList<Integer> importance) {
        while (true){
            if (importance.size() < 2){
                break;
            }

            int preLast = importance.get(importance.size() - 2);
            int last = importance.get(importance.size() - 1);

            if (preLast == 4 && !(last == 5)) {
                break;
            } else if (preLast == 4 && last == 5) {
                importance.remove(importance.size() - 1);
                importance.remove(importance.size() - 1);
                symbols.remove(symbols.size() - 1);
                symbols.remove(symbols.size() - 1);
            } else if (!(preLast < last) || last == 5) {
                char tmp = symbols.get(symbols.size() - 2);
                symbols.remove(symbols.size() - 2);
                importance.remove(importance.size() - 2);
                result.add(String.valueOf(tmp));
            } else {
                break;
            }
        }
    }

    private static void initializeSymb(String text, ArrayList<Character> symbols, ArrayList<Integer> importance, int i) {
        int imp = 0;
        if (isDigit(text.charAt(i))){
            return;
        }
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
                imp = 4;
                break;
            case ')':
                imp = 5;
                break;
        }
        importance.add(imp);
        symbols.add(text.charAt(i));
    }
}
