

class Trelease extends Thread
{
    int t_num;
    public Trelease(int num )
    {
        t_num = num;
    }
    public void run ()
    {
        int mem_num;
        while(true)
        {
            while (Manager.flag == true)
            {
                yield();
                //try {sleep (10);} catch (Exception e) {}

            }

            synchronized (Manager.rel_counter_lock)
            {
                mem_num = Manager.request_counter;
            }

            if (mem_num > 0)
            {
                ReleaseMemoryBlock();
                while (mem_num == Manager.request_counter && Manager.flag == true)
                {
                    yield();
                    //try {sleep (10);} catch (Exception e) {}

                }
            }
            else
            {
                System.out.println("Thread num: "+t_num+" finished");
                return;
            }
        }
    }


    private void ReleaseMemoryBlock()
    {
        Manager.setNote(t_num,true);
        Manager.flag = true;

        if ( Manager.isOtherNoteSet() )
        {
            synchronized(Manager.rel_counter_lock)
            {
                // release memory block
                Manager.released_counter ++;
            }
        }
        Manager.setNote(t_num,false);
    }
}