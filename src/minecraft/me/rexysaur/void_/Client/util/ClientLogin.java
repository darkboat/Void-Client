package me.rexysaur.void_.Client.util;

import java.util.UUID;

import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;

import me.rexysaur.void_.Client.Client;
import me.rexysaur.void_.Client.Session.MinecraftLogin;
import net.minecraft.client.Minecraft;

public class ClientLogin {
	public static void login(String name, String pass)
	{
        if (name != null && !name.isEmpty() && pass != null & !pass.isEmpty())
        {
            String clientToken = UUID.randomUUID().toString();
            Minecraft.sessionService = (new YggdrasilAuthenticationService(Minecraft.gameConfig.userInfo.proxy, clientToken)).createMinecraftSessionService();

            try {
            	System.err.println(name);
            	System.err.println(pass);
                Minecraft.session = MinecraftLogin.login(name, pass);
                
                Client.isLoggedIn = true;
        	} catch (Exception e) {
        	    System.err.println("Login Failed");
        	    Client.isLoggedIn = false;
        	    e.printStackTrace();
        	}
        }
	}
}