package strategymethod;

import strategymethod.Order;
import strategymethod.PayByCreditCard;
import strategymethod.PayByPayPal;
import strategymethod.PayStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* Aplicativo de teste. */

public class Demo {
    private static Map<Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;

    static {
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while (!order.isClosed()) {
            int cost;

            String continueChoice;
            do {
                System.out.print("Selecione um produto:" + "\n" +
                        "1 - Placa Mae (R$ 2200)" + "\n" +
                        "2 - CPU (R$ 1850)" + "\n" +
                        "3 - HDD (R$ 1100)" + "\n" +
                        "4 - Memoria (R$ 890)" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Quantos deste produto?: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Quer continuar a escolher produtos? S/N: ");
                continueChoice = reader.readLine();
            } while (continueChoice.equalsIgnoreCase("S"));

            if (strategy == null) {
                System.out.println("Selecione o metodo de pagamento:" + "\n" +
                        "1 - Pay Pal" + "\n" +
                        "2 - Cartao de Credito");
                String paymentMethod = reader.readLine();

                // Cria diferentes estratégias baseado nas entradas do usuário,
                // configuração, etc.
                
                if (paymentMethod.equals("1")) {
                    strategy = new PayByPayPal();
                } else {
                    strategy = new PayByCreditCard();
                }

                // O objeto Order delega o pagamento para o objeto 
                //  já que somente ele sabe da existência do pagamento.
                
                order.processOrder(strategy);

                System.out.print("Pagar R$ " + order.getTotalCost() + " ou continuar a comprar? P/C: ");
                String proceed = reader.readLine();
                if (proceed.equalsIgnoreCase("P")) {
                	
                    // Finalmente, o "strategy" trata do pagamento.
                    
                	if (strategy.pay(order.getTotalCost())) {
                        System.out.println("Pagamento realizado.");
                    } else {
                        System.out.println("FALHA! Por favor, confira os dados.");
                    }
                    order.setClosed();
                }
            }
        }
    }
}