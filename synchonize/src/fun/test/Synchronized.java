package fun.test;

public class Synchronized {

	public static void main(String[] args) {
		Thr run = new Thr();
		Thread t1 = new Thread(run,"线程一");
		Thread t2 = new Thread(run,"线程二");
		Thread t3 = new Thread(run,"线程三");
		t1.start();
		t2.start();
		t3.start();
		
	}
}
class Ticket{
	public  int TICKETS = 400;
	
	public synchronized void  saleTicket(){
		TICKETS=TICKETS--;
	}
}

class Thr implements Runnable{
	Ticket ti = new Ticket();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Ticket tic = new Ticket();
		do{
		synchronized(this){
			System.out.println(Thread.currentThread().getName()+":"+"  获得参数："+ti.TICKETS);
			ti.TICKETS = ti.TICKETS-1;
		}
		}while(ti.TICKETS>1);
	}
	
}