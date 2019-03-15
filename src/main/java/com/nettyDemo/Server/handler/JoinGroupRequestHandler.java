package com.nettyDemo.Server.handler;

import com.nettyDemo.protocal.request.JoinGroupRequestPacket;
import com.nettyDemo.protocal.respones.JoinGroupResponsePacket;
import com.nettyDemo.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket>{
	//构造单例
	public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();
	
	protected JoinGroupRequestHandler() {
		
	}
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 2. 构造加群响应发送给客户端
        JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();

        responsePacket.setSuccess(true);
        responsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
