package examples.designpattern.command;

public class CommandImpl extends Command {

	public CommandImpl(Reciever reciever) {
		super(reciever);

	}

	@Override
	public void execute() {
		this.reciever.action();

	}

}
