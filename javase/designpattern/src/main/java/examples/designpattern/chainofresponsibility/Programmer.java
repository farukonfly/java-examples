package examples.designpattern.chainofresponsibility;
/**
 *  A member of the chain
 *  The resposibility of Programmer is coding 
 */

public class Programmer extends Chain {
    private Chain nextChain = null;

    
    public Programmer() {
    	this.responsibility = ResponsibilityStatus.CODING;
    }
    
    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(ResponsibilityStatus resp) {
        if(resp.equals(responsibility)) {
            System.out.println("A Programmer  -->  Coding");
        } else {
            if(nextChain != null) {
                nextChain.sendToChain(resp);
            }
        }
    }
    
}
