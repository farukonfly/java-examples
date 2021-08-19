/**
 *  A member of the chain
 *  The resposibility of QA is test 
 */
package examples.designpattern.chainofresponsibility;

public class PA extends Chain {
    private Chain nextChain = null;
    
    public PA() {
    	this.responsibility = ResponsibilityStatus.TEST;
    }
    

    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(ResponsibilityStatus resp) {
        if(resp.equals(responsibility)) {
            System.out.println("A PA  -->  Test");
        } else {
            if(nextChain != null) {
                nextChain.sendToChain(resp);
            }
        }
    }
    
}