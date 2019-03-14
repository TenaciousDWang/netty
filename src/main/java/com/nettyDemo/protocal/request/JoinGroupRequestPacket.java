package com.nettyDemo.protocal.request;

import com.nettyDemo.protocal.Packet;
import static com.nettyDemo.protocal.command.Command.JOIN_GROUP_REQUEST;

public class JoinGroupRequestPacket extends Packet{
	
	    private String groupId;

	    @Override
	    public Byte getCommand() {

	        return JOIN_GROUP_REQUEST;
	    }

		public String getGroupId() {
			return groupId;
		}

		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
	    
	    
}
