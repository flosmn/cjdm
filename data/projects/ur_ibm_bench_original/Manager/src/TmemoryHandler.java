

class TmemoryHandler extends Thread
{

    public void run()
    {
        while ( Manager.request_counter > 0)
        {
            System.out.print ("\rMemory Blocks to be realesed yet: "+Manager.request_counter+"     ");
            while ( !Manager.flag )
            {
                yield();
                //try {sleep (10);} catch (Exception e) {}

            }
            Manager.request_counter--;
            Manager.flag=false;
        }
        System.out.println ("\nTmemoryHandler thread finish");
    }
}