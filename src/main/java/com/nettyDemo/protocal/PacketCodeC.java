package com.nettyDemo.protocal;

import static com.nettyDemo.protocal.command.Command.*;

import java.util.HashMap;
import java.util.Map;

import com.nettyDemo.protocal.request.CreateGroupRequestPacket;
import com.nettyDemo.protocal.request.GroupMessageRequestPacket;
import com.nettyDemo.protocal.request.JoinGroupRequestPacket;
import com.nettyDemo.protocal.request.ListGroupMembersRequestPacket;
import com.nettyDemo.protocal.request.LoginRequestPacket;
import com.nettyDemo.protocal.request.LogoutRequestPacket;
import com.nettyDemo.protocal.request.MessageRequestPacket;
import com.nettyDemo.protocal.request.QuitGroupRequestPacket;
import com.nettyDemo.protocal.respones.CreateGroupResponsePacket;
import com.nettyDemo.protocal.respones.GroupMessageResponsePacket;
import com.nettyDemo.protocal.respones.JoinGroupResponsePacket;
import com.nettyDemo.protocal.respones.ListGroupMembersResponsePacket;
import com.nettyDemo.protocal.respones.LoginResponsePacket;
import com.nettyDemo.protocal.respones.LogoutResponsePacket;
import com.nettyDemo.protocal.respones.MessageResponsePacket;
import com.nettyDemo.protocal.respones.QuitGroupResponsePacket;
import com.nettyDemo.serialize.Serializer;
import com.nettyDemo.serialize.impl.JSONSerializer;

import io.netty.buffer.ByteBuf;

public class PacketCodeC {
	
	public static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();
    
    private final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private final Map<Byte, Serializer> serializerMap;

    private PacketCodeC() {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
        packetTypeMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
        packetTypeMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
    }
	
	public ByteBuf encode(ByteBuf byteBuf,Packet packet) {

	    // 2. 序列化 Java 对象
	    byte[] bytes = Serializer.DEFAULT.serialize(packet);

	    // 3. 实际编码过程
	    byteBuf.writeInt(MAGIC_NUMBER);
	    byteBuf.writeByte(packet.getVersion());
	    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
	    byteBuf.writeByte(packet.getCommand());
	    byteBuf.writeInt(bytes.length);
	    byteBuf.writeBytes(bytes);

	    return byteBuf;
	}	
	
	public Packet decode(ByteBuf byteBuf) {
	    // 跳过 magic number
	    byteBuf.skipBytes(4);

	    // 跳过版本号
	    byteBuf.skipBytes(1);

	    // 序列化算法标识
	    byte serializeAlgorithm = byteBuf.readByte();

	    // 指令
	    byte command = byteBuf.readByte();

	    // 数据包长度
	    int length = byteBuf.readInt();

	    byte[] bytes = new byte[length];
	    byteBuf.readBytes(bytes);

	    Class<? extends Packet> requestType = getRequestType(command);
	    Serializer serializer = getSerializer(serializeAlgorithm);

	    if (requestType != null && serializer != null) {
	        return serializer.deserialize(requestType, bytes);
	    }

	    return null;
	}
	
    private Serializer getSerializer(byte serializeAlgorithm) {

        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {

        return packetTypeMap.get(command);
    }
}
