package strategymethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Estratégia que implementa o pagamento pelo PayPal. */

public class PayByPayPal implements PayStrategy {
    private static final Map<String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private String email;
    private String password;
    private boolean signedIn;

    static {
        DATA_BASE.put("guilherme2020", "guilherme@upis.br");
        DATA_BASE.put("eloy2020", "eloy@upis.br");
        
    }

    /* Pega dados do cliente. */
    
    @Override
    public void collectPaymentDetails() {
        try {
            while (!signedIn) {
                System.out.print("E-mail do usuario: ");
                email = READER.readLine();
                System.out.print("Senha do usuario: ");
                password = READER.readLine();
                if (verify()) {
                    System.out.println("Dados verificados corretamente.");
                } else {
                    System.out.println("E-mail ou senha incorretos.");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean verify() {
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }

    /* Salva dados do usuario para futuras compras. */
    
    @Override
    public boolean pay(int paymentAmount) {
        if (signedIn) {
            System.out.println("Pagando R$ " + paymentAmount + " com Pay Pal.");
            return true;
        } else {
            return false;
        }
    }

    private void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
}