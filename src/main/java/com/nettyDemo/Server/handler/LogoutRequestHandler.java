package com.nettyDemo.Server.handler;

import com.nettyDemo.protocal.request.LogoutRequestPacket;
import com.nettyDemo.protocal.respones.LogoutResponsePacket;
import com.nettyDemo.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket>{
	//构造单例
	public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();
	
	protected LogoutRequestHandler() {
		
	}
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
