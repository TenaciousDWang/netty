package com.nettyDemo.protocal.request;

import java.util.List;

import com.nettyDemo.protocal.Packet;
import static com.nettyDemo.protocal.command.Command.CREATE_GROUP_REQUEST;

public class CreateGroupRequestPacket extends Packet{
	private List<String> userIdList;
	
    @Override
    public Byte getCommand() {

        return CREATE_GROUP_REQUEST;
    }

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
}
