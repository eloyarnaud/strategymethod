package strategymethod;

/* Utiliza a estrat�gia comum para delegar o pagamento para a classe "PayStrategy.java". */

public class Order {
    private int totalCost = 0;
    private boolean isClosed = false;

    
 // Aqui podemos receber e guardar dados do pagamento da estrat�gia.
    public void processOrder(PayStrategy strategy) {
        strategy.collectPaymentDetails();
        
    }

    public void setTotalCost(int cost) {
        this.totalCost += cost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed() {
        isClosed = true;
    }
}