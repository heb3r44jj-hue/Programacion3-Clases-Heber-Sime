public class CuentaBancaria {
    private final String titular;
    private double saldo;

    public CuentaBancaria(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public synchronized void depositar(String cliente , double monto) throws DepositoInvalidoExecption {
        if (cliente == null || cliente.isEmpty()) {
            throw new DepositoInvalidoExecption("Nombre del cliente esta vacio");
        }
        if (monto <= 0) {
            throw new DepositoInvalidoExecption("Monto inválido: " + monto);
        }
        saldo += monto;
        System.out.printf("[Cajero]  Deposito OK  | %-6s | $%.2f | Saldo: $%.2f%n", cliente, monto, saldo);
    }

    public synchronized double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
    
}
