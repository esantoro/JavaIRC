/*
 * Chat client: a simple irc client designed in Java.
 *
 * Author Emanuele Santoro <santoro@autistici.org>
 */

import java.net.* ;
import java.awt.event.* ;
import javax.swing.* ;
import java.io.* ;


/*
 * La classe IrcClient e' la classe principale, e deve solo creare gli oggetti e metterli assieme.
 * Punto & stop.
 */
class IrcClient {
    public static void main(String args[]) {

	IrcSock mysock = new IrcSock("irc.freenode.org" , 6667) ;
	mysock.send("USER hh hh hh hh") ;
	mysock.send("NICK Freggia") ;
	
	Pinger ping = new Pinger(mysock, 500) ;
	
	String RECV_BUFFER ;
	while (true){
	    if ( (RECV_BUFFER = mysock.read() ) != null ) {
		System.out.println( RECV_BUFFER ) ;
	    }
	}
	
    }
}