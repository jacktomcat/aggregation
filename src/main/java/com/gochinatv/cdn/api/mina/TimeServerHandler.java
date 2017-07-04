package com.gochinatv.cdn.api.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by jacktomcat on 17/7/2.
 */
public class TimeServerHandler extends IoHandlerAdapter {

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        System.out.println("Server sessionCreated : 创建session");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("Server sessionOpened : 打开session");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("Server sessionClosed : 关闭session");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        System.out.println("Server sessionIdle : session 空闲 :" + status.toString());
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        System.out.println("Server exceptionCaught : 发生异常: " +cause.getMessage());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        //super.messageReceived(session, message);
        System.out.println("Server messageReceived : 接收 message :" + message);
        session.write(message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        System.out.println("Server messageSent : 发送 message :" + message);
    }
}
