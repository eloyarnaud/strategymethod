package strategymethod;
/* Interface comum para todas as estratégias. */

public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}