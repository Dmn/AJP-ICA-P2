package ajp.ica.p2;

import java.util.concurrent.ArrayBlockingQueue;


public class MetaAgent extends ArrayBlockingQueue<Message> implements Runnable
{
    String name = "";
    Portal portal = null;
    private Thread thread;
    private volatile boolean run;
    
    public MetaAgent()
    {
        super(100);
    }
    
    private void start()
    {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Thread messageFling=new Thread(this);
    }
    
    public synchronized void msgHandler(Message msg)
    {
        
        //Handle the messages.
        //UserAgent r= receiver;
        //BlockingQueue q= r.getQueue();
        //q.add(msg);
        //r.setQueue(q);
    }
    

}



