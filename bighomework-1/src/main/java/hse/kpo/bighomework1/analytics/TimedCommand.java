package hse.kpo.bighomework1.analytics;

public class TimedCommand implements Command {
    private final Command command;

    public TimedCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() {
        long start = System.currentTimeMillis();
        command.execute();
        long end = System.currentTimeMillis();
        System.out.println("Время выполнения: " + (end - start) + " мс");
    }
}
