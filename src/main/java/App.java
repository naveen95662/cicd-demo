public class App {
    public static void main(String[] args) {
        System.out.println("CI/CD pipeline is working");
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (Exception e) {}
        }
    }
}
