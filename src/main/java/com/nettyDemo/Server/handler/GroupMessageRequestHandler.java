package com.nettyDemo.Server.handler;

import com.nettyDemo.protocal.request.GroupMessageRequestPacket;
import com.nettyDemo.protocal.respones.GroupMessageResponsePacket;
import com.nettyDemo.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket>{
	//构造单例
	public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();
	
	protected GroupMessageRequestHandler() {
		
	}	
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {
        // 1.拿到 groupId 构造群聊消息的响应
        String groupId = requestPacket.getToGroupId();
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupId);
        responsePacket.setMessage(requestPacket.getMessage());
        responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.writeAndFlush(responsePacket);
    }
}
