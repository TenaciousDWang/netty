package com.nettyDemo.protocal.respones;
import static com.nettyDemo.protocal.command.Command.LOGIN_RESPONSE;

import com.nettyDemo.protocal.Packet;

public class LoginResponsePacket extends Packet{

	private boolean success;

    private String reason;
    
    private String userName;
    
    private String userId;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
