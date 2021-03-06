package com.gochinatv.cdn.api.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;


public class MinaTimeServer {
   
	private static final int PORT = 9123;
	
	public static void main( String[] args ) throws Exception
    {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast( "logger", new LoggingFilter() );
        acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));
        acceptor.setHandler(  new TimeServerHandler() );

        acceptor.getSessionConfig().setReadBufferSize( 2048 );
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10 );
        
        acceptor.bind( new InetSocketAddress(PORT) );

        acceptor.addListener(new IoServiceListener() {
            @Override
            public void serviceActivated(IoService service) throws Exception {

            }

            @Override
            public void serviceIdle(IoService service, IdleStatus idleStatus) throws Exception {

            }

            @Override
            public void serviceDeactivated(IoService service) throws Exception {

            }

            @Override
            public void sessionCreated(IoSession session) throws Exception {
                session.write("hello world");
            }

            @Override
            public void sessionDestroyed(IoSession session) throws Exception {

            }
        });
    
    }
	
}
