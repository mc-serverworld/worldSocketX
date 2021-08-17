/*
 *
 *  * WorldMISF-lib: library and basic component of mc-serverworld
 *  * Copyright (C) 2020-2021 mc-serverworld
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.serverworld.worldSocketX.socket;

import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.paper.worldSocketXPaper;
import com.serverworld.worldSocketX.paper.utils.DebugMessage;
import net.md_5.bungee.api.ChatColor;

import javax.net.ssl.SSLSocket;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SSLSocketClient {
    //private sender sender = new sender();


    static SSLSocket socket;
    //private static Scanner in;
    //private static PrintWriter out;

    //static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    private static ConcurrentLinkedQueue<MessageObject> SendMessageList = new ConcurrentLinkedQueue<>();

    public void startConnect(){
        Connecter connecter = new Connecter();
        connecter.start();
    }

    class Connecter extends Thread{
        @Override
        public void run() {
            try {
                SSLSocketKey socketKey = new SSLSocketKey();
                socketKey.initialization();

                socket = (SSLSocket) (socketKey.getCtx().getSocketFactory().createSocket(worldSocketXConfig.getHost(),worldSocketXConfig.getPort()));
                socket.setSoTimeout(300);
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                DebugMessage.sendInfo("Loading login message...");
                LoginMessage loginMessage = new LoginMessage(worldSocketXConfig.getUUID(),0);
                out.println(loginMessage.getLoginJson());
                DebugMessage.sendInfo("Login with: " + loginMessage.UUID);
                DebugMessage.sendInfo("ProtocolVersion: " + 0);
                while (true){
                    //in = new Scanner(socket.getInputStream());
                    if (in.hasNextLine()) {
                        String message = in.nextLine();
                        DebugMessage.sendInfoIfDebug("----Message receive----\n" + message + "\n----------------------");
                        if(message.equals("ACCEPTED")){
                            DebugMessage.sendInfo(ChatColor.GREEN + "Connected to server!");
                        }else if(message.equals("ERROR::UUID_USED")) {
                            DebugMessage.sendWarring(ChatColor.RED + "The UUID has been used!");
                        }else if(message.startsWith("CHECK::")){
                            String[] stgs = message.split("::");
                            SendMessageList.removeIf(stuff -> stuff.getCRC32C().equals(stgs[1]));
                        }else {
                            /*
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonmsg = jsonParser.parse(message).getAsJsonObject();
                            JSONObject json = new JSONObject(message);
                            if(worldsocket.config.debug())
                                worldsocket.getLogger().info(json.toString());
                            if(json.getString("receiver").toLowerCase().equals(worldsocket.config.name().toLowerCase()) && !json.getString("type").toLowerCase().equals("socketapi")){
                                worldsocket.eventsender.addeventqueue(message);
                                if(worldsocket.config.debug()){
                                    worldsocket.getLogger().info("Event Fired");
                                    //worldsocket.getLogger().info("Event Fired " + "message: " + "sender: " + json.getString("sender") + " receiver: " + json.getString("receiver") + " channel: " + json.getString("channel") + " type: " + json.getString("type"));
                                }
                            }else if(json.getString("receiver").toLowerCase().equals("all")&& !json.getString("type").toLowerCase().equals("socketapi")){
                                worldsocket.eventsender.addeventqueue(message);
                                if(worldsocket.config.debug()){
                                    worldsocket.getLogger().info("Event Fired");
                                    //worldsocket.getLogger().info("Event Fired " + "message: " + "sender: " + json.getString("sender") + " receiver: " + json.getString("receiver") + " channel: " + json.getString("channel") + " type: " + json.getString("type"));
                                }


                            }else if(json.getString("type").toLowerCase().equals("socketapi")){

                            }else
                                if (worldsocket.config.debug())
                                worldsocket.getLogger().info(ChatColor.YELLOW + "Unknow message");*/
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                DebugMessage.sendWarring(ChatColor.RED + "Error while connect to socket server");
            }
        }
    }

    public static void sendMessage(MessageObject messageObject) {
        //queue.add(message);
        SendMessageList.add(messageObject);
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("");//TODO Send Message
            DebugMessage.sendInfoIfDebug("Send message\n"+messageObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendRawMessage(String message){
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println(message);
            DebugMessage.sendInfoIfDebug("Send message\n"+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*private class Sender extends Thread {
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                    synchronized (queue) {
                        if (!queue.isEmpty()) {
                            for (String stuff : queue) {
                                //TODO create event
                                out.println(stuff);
                                DebugMessage.sendInfoIfDebug("----Message receive----\n" + stuff + "\n----------------------");
                            }
                            queue.clear();
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    private void checker(){
        SendMessageList.clear();
        worldSocketXPaper.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(worldSocketXPaper.getInstance(), new Runnable() {
            @Override
            public void run() {
                synchronized (SendMessageList) {
                        //ConnectCheckList.add(String.valueOf(new Date().getTime());
                        //PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                        //DebugMessage.sendInoIfDebug("checking connection");
                        if(SendMessageList.size()>20) {
                            SendMessageList.clear();
                            //TODO Call reconnect
                        }
                }
            }
        }, 0L, worldSocketXConfig.getCheckRate()*20);
    }
}
