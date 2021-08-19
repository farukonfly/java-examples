package examples.designpattern.chainofresponsibility;

/**
 *  The abstract class of the chain
 *  You can use AddChain function to modify the chain dynamically
 *  addChain & getChain 是实现模式的关键方法
 *  sendToChain为业务方法
 */
public abstract class Chain  {
    public abstract void addChain(Chain c);
    public abstract void sendToChain(ResponsibilityStatus resp);
    public abstract Chain getChain();
    protected ResponsibilityStatus responsibility = ResponsibilityStatus.OTHERS;
}