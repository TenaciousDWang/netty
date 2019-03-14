package com.nettyDemo.protocal.respones;
import static com.nettyDemo.protocal.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

import java.util.List;

import com.nettyDemo.Session.Session;
import com.nettyDemo.protocal.Packet;
public class ListGroupMembersResponsePacket extends Packet{

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {

        return LIST_GROUP_MEMBERS_RESPONSE;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(List<Session> sessionList) {
		this.sessionList = sessionList;
	}
    
    
}
