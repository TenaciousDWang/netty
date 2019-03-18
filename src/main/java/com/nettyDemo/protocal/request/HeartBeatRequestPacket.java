package com.nettyDemo.protocal.request;
import static com.nettyDemo.protocal.command.Command.HEARTBEAT_REQUEST;

import com.nettyDemo.protocal.Packet;
public class HeartBeatRequestPacket extends Packet{
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
