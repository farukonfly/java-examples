package examples.designpattern.command;

//抽象命令类,构造方法需要传入真正执行命名的对象(Receiver)
public abstract class Command {
	protected Reciever reciever;

	public Command(Reciever reciever) {
		this.reciever = reciever;
	}

	public abstract void execute();

}
