package com.nettyDemo.Server.handler;

import java.util.Date;
import java.util.UUID;

import com.nettyDemo.Session.Session;
import com.nettyDemo.protocal.request.LoginRequestPacket;
import com.nettyDemo.protocal.respones.LoginResponsePacket;
import com.nettyDemo.util.SessionUtil;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
//加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
	
    //构造单例
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler() {
    }
	
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());
        
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            String userId = randomUserId();
            loginResponsePacket.setUserId(userId);
            System.out.println(new Date() +"用户：***"+userId+"***"+"【"+loginRequestPacket.getUserName()+"】"+ ": 登录成功!");
            SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUserName()), ctx.channel());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
    
    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
    
    // 用户断线之后取消绑定
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
