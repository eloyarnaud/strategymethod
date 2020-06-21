package strategymethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Estratégia que implementa pagamento por cartão de crédito. */

public class PayByCreditCard implements PayStrategy {
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    /* Recebe dados do cartão. */
    
    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Insira o numero do cartao de credito: ");
            String number = READER.readLine();
            System.out.print("Insira a validade do cartao 'mm/aa': ");
            String date = READER.readLine();
            System.out.print("Insira o código de verificacao (CVV) - Verso do cartao: ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);

            // Valida número do cartão...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* Após validar número do cartão, cobra do cartão. */
    
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Pagando R$ " + paymentAmount + " utilizando Cartão de Credito.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        } else {
            return false;
        }
    }

    private boolean cardIsPresent() {
        return card != null;
    }
}