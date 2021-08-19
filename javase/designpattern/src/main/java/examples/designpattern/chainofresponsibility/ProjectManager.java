/**
 *  A member of the chain
 *  The resposibility of PM is to design the project 
 */
package examples.designpattern.chainofresponsibility;

public class ProjectManager extends Chain {
    private Chain nextChain = null;

    
    public ProjectManager() {
    	this.responsibility = ResponsibilityStatus.SCHEDULE;
    }
    
    public void addChain(Chain c) {
        nextChain = c;
    }
    
    public Chain getChain() {
        return nextChain;
    }
    
    public void sendToChain(ResponsibilityStatus resp) {
        if(resp.equals(responsibility)) {
            System.out.println("A PM  -->  Schedule");
        } else {
            if(nextChain != null) {
                nextChain.sendToChain(resp);
            }
        }
    }
    
}