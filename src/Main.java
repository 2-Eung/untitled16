class Calculator {
    @Deprecated
    public int add (int a, int b) {
        return a + b;
    }
    public int addNumber (int a , int b) {
        return a + b;
    }
}
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.add(1,2)); // 실행은 되는데 노란줄 뜸
        System.out.println(calculator.addNumber(1, 2));
    }
}