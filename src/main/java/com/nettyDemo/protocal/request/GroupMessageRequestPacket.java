package com.nettyDemo.protocal.request;
import static com.nettyDemo.protocal.command.Command.GROUP_MESSAGE_REQUEST;

import com.nettyDemo.protocal.Packet;
public class GroupMessageRequestPacket extends Packet{
    private String toGroupId;
    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.setToGroupId(toGroupId);
        this.setMessage(message);
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }

	public String getToGroupId() {
		return toGroupId;
	}

	public void setToGroupId(String toGroupId) {
		this.toGroupId = toGroupId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
