/*
 *
 * Class Pinger: waits and ping the irc server every $TIMEOUT seconds.
 *
 */




public class Pinger extends Thread {
    
    private long TIMEOUT = 1000 ;  // timeout, default 5 seconds (5000 milliseconds)
    private IrcSock SOCK ;
    private String HOST ;

    public Pinger( IrcSock sock  , long timeout ) {
	this.HOST = sock.getHost() ;
	this.SOCK = sock ;
	this.TIMEOUT = timeout ;
	
	try {
	    this.start() ;
	}
	catch (Exception ex11) {
	    // do nothing, bitches!
	}
    }



    public Pinger(IrcSock sock) {
	this.SOCK = sock ;
	this.HOST = sock.getHost() ;
	
	try{
	    this.start() ;
	}
	catch (Exception ex100) {
	    // do nothing, bitches!
	}
    }
    
    public void run() {
	while (true) {
	    this.SOCK.send("PING " + this.HOST) ;
	    try {
		Thread.sleep(this.TIMEOUT) ;
	    }
	    catch (InterruptedException ex101) {
		// do nothing
	    }
	}
    }
}