package com.nettyDemo.protocal.request;
import static com.nettyDemo.protocal.command.Command.LIST_GROUP_MEMBERS_REQUEST;

import com.nettyDemo.protocal.Packet;
public class ListGroupMembersRequestPacket extends Packet{
	
    private String groupId;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_REQUEST;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
    
    

}
