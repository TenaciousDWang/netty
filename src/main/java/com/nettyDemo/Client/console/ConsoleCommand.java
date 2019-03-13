package com.nettyDemo.Client.console;

import java.util.Scanner;

import io.netty.channel.Channel;

public interface ConsoleCommand {
	void exec(Scanner scanner, Channel channel);
}
