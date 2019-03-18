package com.nettyDemo.Client.handler;

import java.util.concurrent.TimeUnit;

import com.nettyDemo.protocal.request.HeartBeatRequestPacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
	
	   private static final int HEARTBEAT_INTERVAL = 5;

	    @Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	        scheduleSendHeartBeat(ctx);

	        super.channelActive(ctx);
	    }

	    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
	        ctx.executor().schedule(() -> {

	            if (ctx.channel().isActive()) {
	                ctx.writeAndFlush(new HeartBeatRequestPacket());
	                scheduleSendHeartBeat(ctx);
	            }

	        }, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
	    }
}
