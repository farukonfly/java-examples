package examples.jdk8.concurrent.thread;

class ThreadGroupTest {
    public static void main(final String[] args) {
        main_system_threadGroup();
    }

    static void main_system_threadGroup() {
        System.out.println("main thread group = " + Thread.currentThread().getThreadGroup());
        System.out.println("main parent thead group = " + Thread.currentThread().getThreadGroup().getParent());
        Thread t = new Thread(()->{
            System.out.println("thread group of new thread in main = " + Thread.currentThread().getThreadGroup());
            System.out.println("parent thread group of new thread in main = " + Thread.currentThread().getThreadGroup().getParent());
       
        });
        t.start();
    }

    static void threadGroup_ctors() {
        final ThreadGroup subThreadGroup1 = new ThreadGroup("subThreadGroup1");
        final ThreadGroup subThreadGroup2 = new ThreadGroup(subThreadGroup1, "subThreadGroup2");
        System.out
                .println("subThreadGroup1 parent name = " + subThreadGroup1.getParent().getName());
        System.out
                .println("subThreadGroup2 parent name = " + subThreadGroup2.getParent().getName());
        /*
         * subThreadGroup1 parent name = main 
         * subThreadGroup2 parent name = subThreadGroup1
         */
    }
    static void threadGroup_interrupt(){
        //start two threads
        MyThread mt = new MyThread();
        mt.setName("A");
        System.out.println("线程A:"+mt.getThreadGroup().getName()); //main
        mt.start();
        mt = new MyThread();
        mt.setName("B");
        System.out.println("线程B:"+mt.getThreadGroup().getName()); //main
        mt.start();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // Interrupt all methods in the same thread group as the main thread
        //main ThreadGroup
        System.out.println("Current ThreadGroup:"+Thread.currentThread().getThreadGroup().getName());
        Thread.currentThread().getThreadGroup().interrupt();
    }

    
}

 //一个启动以后进入等待，直到被interrupt的线程
class MyThread extends Thread{
    @Override
    public void run(){
        synchronized("A"){
            System.out.println(getName()+" about to wait.");
            try {
                "A".wait();
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
            }
            System.out.println(getName() + " terminating.");
        }
    }
}
