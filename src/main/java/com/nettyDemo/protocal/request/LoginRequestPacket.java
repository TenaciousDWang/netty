package com.nettyDemo.protocal.request;


import static com.nettyDemo.protocal.command.Command.LOGIN_REQUEST;

import com.nettyDemo.protocal.Packet;

public class LoginRequestPacket extends Packet {
	
    private String userId;

	private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        
        return LOGIN_REQUEST;
    }
    
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
