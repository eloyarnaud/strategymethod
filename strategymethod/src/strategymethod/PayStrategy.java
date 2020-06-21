package strategymethod;
/* Interface comum para todas as estrat�gias. */

public interface PayStrategy {
    boolean pay(int paymentAmount);
    void collectPaymentDetails();
}