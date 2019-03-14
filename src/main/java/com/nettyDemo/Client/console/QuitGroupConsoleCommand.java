package com.nettyDemo.Client.console;

import java.util.Scanner;

import com.nettyDemo.protocal.request.QuitGroupRequestPacket;

import io.netty.channel.Channel;

public class QuitGroupConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

        System.out.print("输入 groupId，退出群聊：");
        String groupId = scanner.next();

        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
