public class Main {

    public static void main(String[] args) throws InterruptedException {
        CuentaBancaria cuenta = new CuentaBancaria("Cliente Banco", 0.0);

        Cajero cajero = new Cajero(cuenta);
        Auditor auditor = new Auditor(cuenta);

        auditor.start();
        cajero.start();

        cajero.join();

        auditor.detener();
        auditor.join();

        System.out.println("================================");
        System.out.printf("Saldo final: $%.2f%n", cuenta.getSaldo());
        System.out.println("================================");
    }
}