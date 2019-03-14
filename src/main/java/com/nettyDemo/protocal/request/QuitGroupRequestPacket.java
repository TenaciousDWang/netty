package com.nettyDemo.protocal.request;
import static com.nettyDemo.protocal.command.Command.QUIT_GROUP_REQUEST;

import com.nettyDemo.protocal.Packet;
public class QuitGroupRequestPacket extends Packet{
	
    private String groupId;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_REQUEST;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
    
    
}
