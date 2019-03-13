package com.nettyDemo.protocal;

import com.alibaba.fastjson.annotation.JSONField;


public abstract class Packet {


	/**
     * 协议版本
     */
	@JSONField(deserialize = false, serialize = false)
	private Byte version = 1;

    /**
    * 指令
    */
	@JSONField(serialize = false)
    public abstract Byte getCommand();

	public Byte getVersion() {
		return version;
	}

    public void setVersion(Byte version) {
		this.version = version;
	}
    
}
