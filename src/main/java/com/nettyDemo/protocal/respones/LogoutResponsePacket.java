package com.nettyDemo.protocal.respones;

import com.nettyDemo.protocal.Packet;
import static com.nettyDemo.protocal.command.Command.LOGOUT_RESPONSE;

public class LogoutResponsePacket extends Packet{
	
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {

        return LOGOUT_RESPONSE;
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
