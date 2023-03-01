package kg.megacom.authorizationservice.exceptions;


public class AccountNotFoundExc extends RuntimeException {
    public AccountNotFoundExc(String message) {
        super(message);
    }
}
