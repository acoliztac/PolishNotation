import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.lang.Character.isDigit;
import static java.lang.Math.pow;

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

        String expression = reader.readLine();
        if (expression.contains("null")){
            System.exit(0);
        }
        reader.close();
//        String expression = "2 ^ 2.1";
        Polish polish = new Polish(expression);
    }



}
