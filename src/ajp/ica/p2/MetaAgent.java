package ajp.ica.p2;

interface MetaAgent
{
    String name = "";
    Portal portal = null;

    public void msgHandler(Message msg);

}

//    @Override
//    public void run() {
//        Thread messageFling=new Thread(this);
//    }
    
//    public synchronized void msgHandler(UserAgent receiver ,Message msg)
//    {
//        //Handle the messages.
//        UserAgent r= receiver;
//        BlockingQueue q= r.getQueue();
//        q.add(msg);
//        r.setQueue(q);
//    }

