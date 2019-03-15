package com.nettyDemo.Server.handler;

import java.util.ArrayList;
import java.util.List;

import com.nettyDemo.Session.Session;
import com.nettyDemo.protocal.request.ListGroupMembersRequestPacket;
import com.nettyDemo.protocal.respones.ListGroupMembersResponsePacket;
import com.nettyDemo.util.SessionUtil;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket>{
	//构造单例
	public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();
	
	protected ListGroupMembersRequestHandler() {
		
	}
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) {
        // 1. 获取群的 ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        // 3. 构建获取成员列表响应写回到客户端
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
