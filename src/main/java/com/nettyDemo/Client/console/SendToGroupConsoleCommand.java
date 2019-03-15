package com.nettyDemo.Client.console;

import java.util.Scanner;

import com.nettyDemo.protocal.request.GroupMessageRequestPacket;

import io.netty.channel.Channel;

public class SendToGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));

    }
}
