import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Character.isDigit;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Никита on 18.08.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("\tИнвертированная польская запись.\n" +
                "Данный алгоритм предназначен для вычисления формул, содержащих арифметические операции:" +
                "\nСложение. Вычитание. Умножение. Деление.\n" +
                "Возведение в степень. Извлечение корня.  " +
                "\n\n\tВведите уравнение для вычисления:"
        );

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

//        String expression = reader.readLine();
        reader.close();
        String expression = "2 + 3";
        solution(expression);
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
        System.out.println("\tРезультат = " + polish.get(0));
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
        System.out.println(text + ": форматированное исходное выражение");

        ArrayList<String> result = mainInitialization(text);

        for (String s : result){
            System.out.print(s + " ");
        }
        System.out.println(": форматированная польская запись");
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
