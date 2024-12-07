
interface State {
    void selectTicket();
    void insertMoney();
    void dispenseTicket();
    void cancelTransaction();
}

class IdleState implements State {
    private TicketMachine machine;

    public IdleState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Билет выбран. Переход в состояние ожидания внесения денег.");
        machine.setState(machine.getWaitingForMoneyState());
    }

    @Override
    public void insertMoney() {
        System.out.println("Сначала выберите билет.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Сначала выберите билет и внесите деньги.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Нечего отменять.");
    }
}

class WaitingForMoneyState implements State {
    private TicketMachine machine;

    public WaitingForMoneyState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Билет уже выбран. Ожидаем внесения денег.");
    }

    @Override
    public void insertMoney() {
        System.out.println("Деньги внесены. Переход в состояние выдачи билета.");
        machine.setState(machine.getMoneyReceivedState());
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Сначала внесите деньги.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция отменена. Возврат в начальное состояние.");
        machine.setState(machine.getIdleState());
    }
}

class MoneyReceivedState implements State {
    private TicketMachine machine;

    public MoneyReceivedState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Билет уже выбран.");
    }

    @Override
    public void insertMoney() {
        System.out.println("Деньги уже внесены.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Билет выдан. Возврат в начальное состояние.");
        machine.setState(machine.getIdleState());
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция отменена. Деньги возвращены. Возврат в начальное состояние.");
        machine.setState(machine.getIdleState());
    }
}

class TransactionCanceledState implements State {
    private TicketMachine machine;

    public TransactionCanceledState(TicketMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectTicket() {
        System.out.println("Транзакция отменена. Выберите билет заново.");
        machine.setState(machine.getIdleState());
    }

    @Override
    public void insertMoney() {
        System.out.println("Транзакция отменена. Начните заново.");
    }

    @Override
    public void dispenseTicket() {
        System.out.println("Транзакция отменена. Выдача билета невозможна.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("Транзакция уже отменена.");
    }
}

class TicketMachine {
    private State idleState;
    private State waitingForMoneyState;
    private State moneyReceivedState;
    private State transactionCanceledState;

    private State currentState;

    public TicketMachine() {
        idleState = new IdleState(this);
        waitingForMoneyState = new WaitingForMoneyState(this);
        moneyReceivedState = new MoneyReceivedState(this);
        transactionCanceledState = new TransactionCanceledState(this);

        currentState = idleState; 
    }

    public void setState(State state) {
        this.currentState = state;
    }

    public State getIdleState() {
        return idleState;
    }

    public State getWaitingForMoneyState() {
        return waitingForMoneyState;
    }

    public State getMoneyReceivedState() {
        return moneyReceivedState;
    }

    public State getTransactionCanceledState() {
        return transactionCanceledState;
    }

    public void selectTicket() {
        currentState.selectTicket();
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void dispenseTicket() {
        currentState.dispenseTicket();
    }

    public void cancelTransaction() {
        currentState.cancelTransaction();
    }
}
