@FunctionalInterface
interface Greeting {
    void sayHello(String name);
//    void anotherMethod(); // 하나의 추상메소드만 존재하여야하므로 오류
}
public class Main {
    public static void main(String[] args) {
        Greeting greeting = (name) -> System.out.println("Hello, " + name + "!");
        greeting.sayHello("Alice");

    }
}