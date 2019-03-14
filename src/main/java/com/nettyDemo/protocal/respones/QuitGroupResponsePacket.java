package com.nettyDemo.protocal.respones;
import static com.nettyDemo.protocal.command.Command.QUIT_GROUP_RESPONSE;

import com.nettyDemo.protocal.Packet;
public class QuitGroupResponsePacket extends Packet{
	
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {

        return QUIT_GROUP_RESPONSE;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
    

}
