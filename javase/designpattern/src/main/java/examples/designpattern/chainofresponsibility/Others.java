package examples.designpattern.chainofresponsibility;
/**
 *  The end of the chain
 *  The resposibility of Others is handle exeception 
 */

public class Others extends Chain {
    private Chain nextChain = null;
    
    public Others() {
    }
    
    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(ResponsibilityStatus resp) {
            System.out.println("Others  -->  " + resp);
    }
    
}