/**
 *  A client to test
 */
package examples.designpattern.chainofresponsibility;

public class Test  {
    public static void main(String[] args) {
        Manager aManager = new Manager();
        ProjectManager aPM = new ProjectManager();
        Programmer aProgrammer = new Programmer();
        PA aPA = new PA();
        Others others = new Others();

        aManager.addChain(aPM);
        aPM.addChain(aProgrammer);
        aProgrammer.addChain(aPA);
        aPA.addChain(others);

        aManager.sendToChain(ResponsibilityStatus.GET_PROJECT);
        aManager.sendToChain(ResponsibilityStatus.SCHEDULE);
        aManager.sendToChain(ResponsibilityStatus.CODING);
        aManager.sendToChain(ResponsibilityStatus.TEST);
        aManager.sendToChain(ResponsibilityStatus.OTHERS);
    }
}