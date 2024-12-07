public class TicketMachineTest {
    public static void main(String[] args) {
        TicketMachine machine = new TicketMachine();

        machine.selectTicket();

        machine.insertMoney();

        machine.dispenseTicket();

        machine.cancelTransaction();

        machine.selectTicket();
        machine.cancelTransaction();

        machine.insertMoney();
    }
}
