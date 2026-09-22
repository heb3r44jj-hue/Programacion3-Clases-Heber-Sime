public class Cajero extends Thread {

    private final CuentaBancaria cuenta;

    private final String[] clientes = {"Ana", "Luis", "Carlos", "", "Maria"};
    private final double[] montos = {1500.00, -200.00, 800.00, 500.00, 3000.00};

    public Cajero(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for (int i = 0; i < clientes.length; i++) {
            String cliente = clientes[i];
            double monto = montos[i];

            try {
                cuenta.depositar(cliente, monto);
            } catch (DepositoInvalidoExecption e) {
                System.out.printf("[Cajero]  ERROR        | %-6s | %s%n", cliente, e.getMessage());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}