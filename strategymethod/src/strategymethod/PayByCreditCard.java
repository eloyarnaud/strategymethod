package strategymethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Estrat�gia que implementa pagamento por cart�o de cr�dito. */

public class PayByCreditCard implements PayStrategy {
    private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private CreditCard card;

    /* Recebe dados do cart�o. */
    
    @Override
    public void collectPaymentDetails() {
        try {
            System.out.print("Insira o numero do cartao de credito: ");
            String number = READER.readLine();
            System.out.print("Insira a validade do cartao 'mm/aa': ");
            String date = READER.readLine();
            System.out.print("Insira o c�digo de verificacao (CVV) - Verso do cartao: ");
            String cvv = READER.readLine();
            card = new CreditCard(number, date, cvv);

            // Valida n�mero do cart�o...

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /* Ap�s validar n�mero do cart�o, cobra do cart�o. */
    
    @Override
    public boolean pay(int paymentAmount) {
        if (cardIsPresent()) {
            System.out.println("Pagando R$ " + paymentAmount + " utilizando Cart�o de Credito.");
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