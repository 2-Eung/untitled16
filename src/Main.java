public class Main {
    @SuppressWarnings("rawtypes")   // 제시한 상황의 경고를 무시
    public static void main(String[] args) {
        java.util.List list = new java.util.ArrayList(); // 제네릭 없다고 경고

        list.add("Hello");
        list.add("World");

        for (Object obj: list) { // Object 로 설정하여 모든타입에 열어놓았다.
            System.out.println(obj);
        }
    }
}