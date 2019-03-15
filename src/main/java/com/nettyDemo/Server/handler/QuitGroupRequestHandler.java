package com.nettyDemo.Server.handler;

import com.nettyDemo.protocal.request.QuitGroupRequestPacket;
import com.nettyDemo.protocal.respones.QuitGroupResponsePacket;
import com.nettyDemo.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket>  {
	//构造单例
	public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
	
	protected QuitGroupRequestHandler() {
		
	}
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setGroupId(requestPacket.getGroupId());
        responsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(responsePacket);

    }
}
