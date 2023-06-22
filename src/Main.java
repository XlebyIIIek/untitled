import java.util.Scanner;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        String[] expression = input.split(" ");
        check_expression(expression);
    }
    public static boolean check_expression(String[] expression){
        //Проверим кол-во элементов в выражении
        if(expression.length != 3){
            return throw_exception("Выражение должно состоять из 3 элементов разделенных пробелами, пример a + b");
        }
        //Проверим знак выражения
        String sign = expression[1];
        String sings = "+-*/";
        if (sings.indexOf(sign) == -1){
            return throw_exception("Неверный знак, можно только +-*/");
        }


        //Проверяем числа (вдруг римские)
        String[] rim_number = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String number_1 = expression[0];
        String number_2 = expression[2];
        int convert_number_1 = 0;
        int convert_number_2 = 0;
        for (int i = 0; i <rim_number.length; i++){
            if(number_1.equals(rim_number[i]) ){
                convert_number_1 = numbers[i];
            }
            if(number_2.equals(rim_number[i])){
                convert_number_2 = numbers[i];
            }
        }
        //Если римские от 0 до 10 и разность больше 0, то считаем ответ
        if(convert_number_1 != 0 && convert_number_2 != 0){
            if (check_numbers(convert_number_1, convert_number_2)){
                if (convert_number_1 - convert_number_2 < 1 && sign.equals("-")){
                    return throw_exception("В римской системе нет отрицательных чисел и 0");
                }
                calculate_rim_expression(convert_number_1, convert_number_2, sign);
                return true;
            }
            else {
                throw_exception("Числа должны быть от 1 до 10");
            }
        }
        //Проверяем нет ли случайно одного римского числа
        if(convert_number_1 != 0 || convert_number_2 != 0){
            return throw_exception("Оба числы должны быть римскими или арабскими");
        }
        //Проверяем являются ли числа целыми
        try {
            convert_number_1 = Integer.parseInt(number_1);
            convert_number_2 = Integer.parseInt(number_2);
        }catch (Exception exception){
            System.out.println("Числа должны быть целыми");
            return true;
        }
        //Проверяем числа чтобы были от 1 до 10
        if (check_numbers(convert_number_1, convert_number_2)){
            System.out.println(calculate_answer(convert_number_1,convert_number_1, sign));
        }
        else {
            throw_exception("Числа должны быть от 1 до 10");
        }



        return false;
    }
    public static boolean throw_exception(String massage){
        try {
            throw new Exception();
        }catch (Exception exception){
            System.out.println(massage);
            return true;
        }
    }
    public static void calculate_rim_expression(int number_1, int number_2, String sign){
        String[] rim_numbers = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] numbers = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        int int_answer = calculate_answer(number_1, number_2, sign);
        String rim_answer = "";
        for (int i = 0; i < rim_numbers.length; i++){
            while (int_answer - numbers[i] > -1){
                int_answer -= numbers[i];
                rim_answer += rim_numbers[i];
            }
        }
        System.out.println(rim_answer);


    }
    public static int calculate_answer(int a, int b, String sign){
        int answer = 0;
        switch (sign){
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                return a/b;
        }
        return answer;
    }
    public static boolean check_numbers(int a, int b){
        if (a > 0 && a < 11 && b > 0 && b < 11){
            return true;
        }
        return false;
    }


}
