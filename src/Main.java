import java.lang.reflect.Field;
import java.lang.reflect.Method;

class ReflectionDemo {
    public String noSecret = "안비밀입니다.";
    private String secret = "비밀입니다.";

    public ReflectionDemo() {
        System.out.println("ReflectionDemo 생성자 실행됨");
    }
    public String greet(String name) {
        return "hello, " + name;
    }
    private String reveal(String code) {
        return "Access granted to : " + code;
    }
}
public class Main {
    public static void main(String[] args) {
        // private 인 secret 은 밖에서 접근이 불가능하다.
        // 그러나 Reflection 으로 이 규칙을 깨버려 접근 가능하게 만들것이다.
        Class<?> clazz = ReflectionDemo.class;

        System.out.println("클래스 이름  : " + clazz.getName()); // 클래스 이름 확인

        System.out.println("\n[필드목록]");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {                         // 필드 이름 확인
            System.out.println("필드: " + field.getName()); // 원래라면 private 는 접근불가
        }                                                  // 하지만 출력된다
        System.out.println("\n[메소드 목록]");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("메소드 : " + method.getName()); // private 메소드도 접근가능
            for (Class<?> paramType : method.getParameterTypes()) {
                System.out.println("파라미터 타입 : " + paramType.getSimpleName());
            }
        } //여기까지는 조회, 조사 이다.
        try {                                  // 이렇게 할 시 public, private 둘 다 사용가능
            Object instance = clazz.getDeclaredConstructor().newInstance();
                                                                              // 메소드 이름, 타입
            Method greetMethod = clazz.getMethod("greet",String.class); // 이미 Method 는 선언함
            Object greetResult = greetMethod.invoke(instance, "ㅇㅇㅇ"); // invoke : 부르다, 유발하다
            System.out.println("\n[퍼블릭 메소드 실행 결과]");
            System.out.println("greet() : " + greetResult); // greet() 메소드가 실행되는지 확인
            // 여기까지 퍼블릭 메소드 실행해보기
                                        // .getMethod 는 private 접근 불가
            Method revealMethod = clazz.getDeclaredMethod("reveal", String.class);
            revealMethod.setAccessible(true); // 접근을 건드리겠다 라는뜻
            Object revealResult = revealMethod.invoke(instance, "abcd");
            System.out.println("\n [프라이빗 메소드 실행 결과]");
            System.out.println("reveal(): " + revealResult);
            // 여기까지 프라이빗 메소드 실행해보기
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}