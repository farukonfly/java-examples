package examples.designpattern.command;

public class Invoker {

	private Command command;

	private void setCommand(Command command)

	{

		this.command = command;

	}

	public void execute()

	{

		this.command.execute();

	}

	public static void main(String[] args)

	{

		Reciever receiver = new Reciever();

		Invoker invoker = new Invoker();

		invoker.setCommand(new CommandImpl(receiver));

		invoker.execute();

	}

}
