package me.rexysaur.void_.Client.util;

import net.minecraft.util.IChatComponent;

public class MessageManager {
	private static IChatComponent recentMessage;
	
	public static IChatComponent getMostRecentMessage()
	{
		return MessageManager.recentMessage;
	}
	
	public static void setMostRecentMessage(IChatComponent message)
	{
		MessageManager.recentMessage = message;
	}
}
