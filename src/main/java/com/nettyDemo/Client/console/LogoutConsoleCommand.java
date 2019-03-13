package com.nettyDemo.Client.console;

import java.util.Scanner;

import com.nettyDemo.protocal.request.LogoutRequestPacket;

import io.netty.channel.Channel;

public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
