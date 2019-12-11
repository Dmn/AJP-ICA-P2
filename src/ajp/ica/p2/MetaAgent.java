package ajp.ica.p2;

import java.util.concurrent.ArrayBlockingQueue;


public class MetaAgent extends ArrayBlockingQueue implements Runnable
{
    String name = "";
    Portal portal = null;
    
    public MetaAgent()
    {
        super(100);
    }

    @Override
    public void run() {
        Thread messageFling=new Thread(this);
    }
    
    public synchronized void msgHandler(UserAgent receiver ,Message msg)
    {
        //Handle the messages.
        //UserAgent r= receiver;
        //BlockingQueue q= r.getQueue();
        //q.add(msg);
        //r.setQueue(q);
    }
    

}



