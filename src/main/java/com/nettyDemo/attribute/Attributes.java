package com.nettyDemo.attribute;

import com.nettyDemo.Session.Session;

import io.netty.util.AttributeKey;

public interface Attributes {
	AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
