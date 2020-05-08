import spark.Spark;

import java.time.Duration;
import java.time.Instant;

import static spark.Spark.get;

public class App {

    public static final int DEFAULT_PORT = 80;

    public static void main(String[] args) {
        Spark.port(getPort());
        get("/fastResponse", (req, res) -> delay(0));
        get("/oneMillisecondsDelay", (req, res) -> delay(1));
        get("/tenMillisecondsDelay", (req, res) -> delay(10));
        get("/oneSecondDelay", (req, res) -> delay(1000));

    }

    private static String delay(int millisec) {
        Instant start = Instant.now();
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        long delay = Duration.between(start, end).getNano();
        return String.valueOf(delay);
    }


    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null)
            return Integer.parseInt(port);
        return DEFAULT_PORT;
    }

}
