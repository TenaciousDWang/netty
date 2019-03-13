package com.nettyDemo.protocal.request;

import com.nettyDemo.protocal.Packet;
import static com.nettyDemo.protocal.command.Command.LOGOUT_REQUEST;

public class LogoutRequestPacket extends Packet{
    @Override
    public Byte getCommand() {

        return LOGOUT_REQUEST;
    }
}
