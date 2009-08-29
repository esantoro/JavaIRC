/*
	IrcSock.java
	this is a simple irc socket abstraction... it handles a single sock, reads and writes on it.

	CopyLeft 2008-3008 Emanuele Santoro <santoro@autistici.org> (http://santoro.tk/)

*/


import java.net.* ;
import java.io.* ;

import java.util.Vector ;

public class IrcSock extends Thread{
    
    private Socket SOCK ;

    private String HOST = "irc.freenode.org";  //We set up irc.freenode.org as default irc server to connect to
    private int PORT = 6667; // The default port is always 6667

    private BufferedReader RDR ;
    private PrintWriter WTR ;
    private Vector<String> INCOMING ;
    private Vector<String> OUTGOING ;

    public IrcSock() {
	// what to do? no host/port defined.
    }
    
    
    public IrcSock( String host , int port) {
	
	this.INCOMING = new Vector<String>() ;
	this.OUTGOING = new Vector<String>() ;
	
	this.HOST = host ; 
	this.PORT = port ;
	try {
	    this.SOCK = new Socket( this.HOST , this.PORT) ;
	    this.RDR = new BufferedReader( new InputStreamReader(this.SOCK.getInputStream()) ) ;
	    this.WTR = new PrintWriter(this.SOCK.getOutputStream()) ; 
	}
	catch (Exception ex00) {
	    ex00.printStackTrace() ;
	    System.out.println("\n\n\t\t\tSomme errors occourred during the connection :)") ;
	    System.exit(2) ;
	}
	this.start() ;
    }
    
    // public void login(String user , String ident) {}
    
    
    public String getHost() {
	return this.HOST ;
    }
    
    public int getPort() {
	return this.PORT ;
    }
    
    public void setHost( String host ) {
	this.HOST = host ;
    }
    
    public void setPort(int port) {
	this.PORT = port ;
    }
    
    public void send(String what) {
	this.OUTGOING.add(what) ;
	//this.WTR.flush() ;
	//this.WTR.println(what) ;
    }

    public String read() {
	String content ;
	try {
	    content = this.INCOMING.firstElement() ;
	    this.INCOMING.removeElementAt(0) ;
	    
	}
	catch (Exception ex10) {
	    content = null ;
	}
	    return content ;
    }
    
    public void run() {
	String toSend ;
	while (true) { 
	    if (this.OUTGOING.size() > 0) {
		toSend = this.OUTGOING.firstElement() ;
		this.WTR.println(toSend) ;
		this.WTR.flush() ;
		this.OUTGOING.removeElementAt(0) ;
	    }
	    
	    try {
		String justReaded = this.RDR.readLine() ;
		this.INCOMING.add(justReaded) ;
	    }
	    catch (IOException ex01) {}
	}


    }
}
