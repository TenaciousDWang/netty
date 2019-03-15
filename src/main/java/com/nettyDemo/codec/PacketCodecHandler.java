package com.nettyDemo.codec;

import java.util.List;

import com.nettyDemo.protocal.Packet;
import com.nettyDemo.protocal.PacketCodeC;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet>{
	public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();
	
    private PacketCodecHandler() {

    }
    
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        out.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
        out.add(byteBuf);
    }
}
