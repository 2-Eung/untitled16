class Animal {
    public void sound(){
        System.out.println("Animal makes a sound");
    }
}
class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}
class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("Cat meow");
    }
}
public class Main {         // 모든 쓰레드가 끝날때 까지 join 으로 대기 한 후 각쓰레드 값을 더해 전체를 구한다.
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.sound();
        cat.sound();
    }
}