import java.lang.annotation.ElementType;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static boolean check_number(String number_1, String number_2, String sign) {
        String[] rim_numbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] rim_numbers_2 = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] numbers_2 = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        Boolean f1 = false;
        Boolean f2 = false;
        int index_1 = 0;
        int index_2 = 0;
        int count = 0;
        for (String number : rim_numbers) {
            if (number_1.equals(number)) {
                f1 = true;
                index_1 = count;
            }
            if (number_2.equals(number)) {
                f2 = true;
                index_2 = count;
            }
            count += 1;
        }
        if (f1 && f2 && !sign.equals("-")) {
            int answer = 0;
            switch (sign) {
                case "+":
                    answer = numbers[index_1] + numbers[index_2];
                    break;
                case "-":
                    answer = numbers[index_1] - numbers[index_2];
                    break;
                case "*":
                    answer = numbers[index_1] * numbers[index_2];
                    break;
                case "/":
                    answer = numbers[index_1] / numbers[index_2];
                    break;
            }
            if (answer < 1){
                try {
                    throw new Exception();
                } catch (Exception exception) {
                    System.out.println("Результат вычисления римских чисел должен быть положительным числом");
                    return true;
                }
            }
            int i = 0;
            String answ = "";
            for (int number: numbers_2){
                while (answer - number >= 0){
                    answer -= number;
                    answ += rim_numbers_2[i];
                }
                i+=1;
            }
            System.out.println(answ);
            return true;
        }
        if (f1 || f2){
            try {
                throw new Exception();
            } catch (Exception exception) {
                System.out.println("Либо оба числа римские, либо оба арабские должны быть");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Введите элементы математического выражения через пробел (a + b):");
        Scanner scanner = new Scanner(System.in);
        String[] expression = scanner.nextLine().split(" ");
        int number_1 = 0;
        int number_2 = 0;
        //Проверяем выражение
        if (expression.length != 3) {
            try {
                throw new Exception("Кол-во элементов больше чем ожидалось");
            } catch (Exception exception) {
                System.out.println("Кол-во элементов больше чем ожидалось");
                return;
            }
        }
        //Проверяем знак
        String signs = "+-*/";
        String sign = expression[1];
        if (signs.indexOf(sign) == -1){
            try {
                throw new Exception();
            } catch (Exception exception) {
                System.out.println("Можно использовать знаки +-*/");
                return;
            }
        }
        //Проверяем наличие римских цифр
        if (check_number(expression[0], expression[2], expression[1])) {
            return;
        }
        //Пробуем преоброзовать в целое число
        try {
            number_1 = Integer.parseInt(expression[0]);
        } catch (NumberFormatException exception) {
            System.out.println(exception);
            return;
        }
        try {
            number_2 = Integer.parseInt(expression[2]);
        } catch (NumberFormatException exception) {
            System.out.println(exception);
            return;
        }
        //Проверяем числа
        if (number_1 < 1 || number_1 > 10 || number_2 < 1 || number_2 > 10) {
            try {
                throw new Exception();
            } catch (Exception exception) {
                System.out.println("Введенные числа должны быть от 1 до 10 включительно");
                return;
            }

        }
        //Смотрим знак и выполняем соответствующуюб операцию
        switch (sign) {
            case "+":
                System.out.println(number_1 + number_2);
                break;
            case "-":
                System.out.println(number_1 - number_2);
                break;
            case "*":
                System.out.println(number_1 * number_2);
                break;
            case "/":
                System.out.println(number_1 / number_2);
                break;
        }
    }
}