package com.nettyDemo.Server;

import com.nettyDemo.Server.handler.AuthHandler;
import com.nettyDemo.Server.handler.CreateGroupRequestHandler;
import com.nettyDemo.Server.handler.JoinGroupRequestHandler;
import com.nettyDemo.Server.handler.ListGroupMembersRequestHandler;
import com.nettyDemo.Server.handler.LoginRequestHandler;
import com.nettyDemo.Server.handler.LogoutRequestHandler;
import com.nettyDemo.Server.handler.MessageRequestHandler;
import com.nettyDemo.Server.handler.QuitGroupRequestHandler;
import com.nettyDemo.codec.PacketDecoder;
import com.nettyDemo.codec.PacketEncoder;
import com.nettyDemo.codec.Spliter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author 王皓
 */
public class NettyServer {
    public static void main(String[] args) {        
    	
        //一个工厂要运作，必然要有一个老板负责从外面接活，然后有很多员工，负责具体干活，
    	//老板就是bossGroup，员工们就是workerGroup，bossGroup接收完连接，扔给workerGroup去处理
    	
        //1.boos对应，IOServer.java中的接受新连接线程，主要负责创建新连接,bossGroup表示监听端口，accept 新连接的线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        
        //2.worker对应 IOServer.java中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理,workerGroup表示处理每一条连接的数据读写的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        
        //3.引导类 ServerBootstrap，这个类将引导我们进行服务端的启动工作
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(bossGroup, workerGroup)//我们通过.group(bossGroup, workerGroup)给引导类配置两大线程组，这个引导类的线程模型也就定型了(A.线程模型)。
                .channel(NioServerSocketChannel.class)//制定服务端的 IO 模型为NIO模型(B.IO 模型)
                .childOption(ChannelOption.SO_KEEPALIVE, true)//表示是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.TCP_NODELAY, true)//表示是否开启Nagle算法,要求高实时性false,需要减少发送次数减少网络交互，就开启
                .option(ChannelOption.SO_BACKLOG, 1024)//表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
                .childHandler(new ChannelInitializer<NioSocketChannel>() {//(C.连接读写处理逻辑)
                	//NioSocketChannel对应Socket概念是一个连接
                    protected void initChannel(NioSocketChannel ch) {
                    	
                    	ch.pipeline().addLast(new Spliter());
                    	ch.pipeline().addLast(new PacketDecoder());//添加一个解码逻辑处理器
                        ch.pipeline().addLast(new LoginRequestHandler());//添加一个逻辑处理器
                        // 新增加用户认证handler
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());//添加一个逻辑处理器     
                        ch.pipeline().addLast(new CreateGroupRequestHandler());
                        ch.pipeline().addLast(new JoinGroupRequestHandler());
                        ch.pipeline().addLast(new QuitGroupRequestHandler());
                        ch.pipeline().addLast(new LogoutRequestHandler());
                        ch.pipeline().addLast(new ListGroupMembersRequestHandler());
                    	ch.pipeline().addLast(new PacketEncoder());//添加一个编码逻辑处理器
                    }
                    
                });
        //serverBootstrap.bind(8000);
        //自动绑定递增端口
        bind(serverBootstrap, 1000);
        //启动一个Netty服务端，必须要指定三类属性，分别是
        //A.线程模型、B.IO 模型、C.连接读写处理逻辑，
        //有了这三者，之后在调用bind(8000)，
        //我们就可以在本地绑定一个 8000 端口启动起来
    }
    
    //自动绑定递增端口
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
        	//serverBootstrap.bind(8000);这个方法呢，它是一个异步的方法，调用之后是立即返回的，他的返回值是一个ChannelFuture，
        	//我们可以给这个ChannelFuture添加一个监听器GenericFutureListener，
        	//然后我们在GenericFutureListener的operationComplete方法里面，我们可以监听端口是否绑定成功
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
    
}
