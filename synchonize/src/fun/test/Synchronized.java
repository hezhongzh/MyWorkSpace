package fun.test;

public class Synchronized {

	public static void main(String[] args) {
		Thr run = new Thr();
		Thread t1 = new Thread(run,"����һ");
		Thread t2 = new Thread(run,"���̶�");
		Thread t3 = new Thread(run,"������");
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
	private  int tickets = 400;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Ticket tic = new Ticket();
		do{
		synchronized(this){
			System.out.println(Thread.currentThread().getName()+":"+"  ��ò�����"+tickets);
			tickets = tickets-1;
		}
		}while(tickets>1);
	}
	
}