package examples.designpattern.chainofresponsibility;


public class Manager extends Chain {
    private Chain nextChain = null;
    
    public Manager() {
    	this.responsibility = ResponsibilityStatus.GET_PROJECT;
    }
    

    public void addChain(Chain c) {
        nextChain = c;
    }

    public Chain getChain() {
        return nextChain;
    }

    public void sendToChain(ResponsibilityStatus resp) {
        if(resp.equals(responsibility)) {
            System.out.println("A manager  -->  Get a Project");
        } else {
            if(nextChain != null) {
                nextChain.sendToChain(resp);
            }
        }
    }
    
}