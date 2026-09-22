public class Auditor extends Thread {

    private final CuentaBancaria cuenta;
    private volatile Boolean activo = true;

    public Auditor(CuentaBancaria cuenta){
        this.cuenta = cuenta;
    }

    public void detener(){
        activo = false;
    }

    @Override 

    public void run() {
        while (activo) {
            try {
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
            if (activo) {
                System.out.printf("Auditor saldo actual: ",cuenta.getSaldo());
            }
            
        }
    }
    
}
