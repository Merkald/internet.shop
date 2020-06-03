package internet.shop.exeptions;

public class AuthenticationExeption extends RuntimeException {
    public AuthenticationExeption(String message) {
        super(message);
    }

    public AuthenticationExeption(String message, Throwable throwable) {
        super(message, throwable);
    }
}
