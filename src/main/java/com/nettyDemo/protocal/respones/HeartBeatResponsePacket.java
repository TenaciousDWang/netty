package com.nettyDemo.protocal.respones;
import static com.nettyDemo.protocal.command.Command.HEARTBEAT_RESPONSE;

import com.nettyDemo.protocal.Packet;
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
