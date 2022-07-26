package com.mxr.chatservice.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@Component
public class AudioServer {
    @Autowired
    private Data data;
    private DatagramSocket socket;
    private byte[] buf = new byte[10000];
    private  boolean running;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void start(){

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try{
                    socket = new DatagramSocket(10000);
                    while (true){
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);

                        InetAddress address = packet.getAddress();
                        String anotherIp = ipToip.get(address.getHostAddress());
                        InetAddress anotherAddress = InetAddress.getByName(anotherIp);
                        packet = new DatagramPacket(buf, buf.length, anotherAddress, 20000);
                        String received
                                = new String(packet.getData(), 0, packet.getLength());

                        System.out.println("udp message : " + received);
                        socket.send(packet);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        (new Thread(task)).start();
    }

    public void test(){
        if(running){
            return;
        }else{
            running = true;
        }
        Runnable task = new Runnable(){

            @Override
            public void run() {

                try{
                    socket = new DatagramSocket(10000);
                    while (true){
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);

                        InetAddress address = packet.getAddress();
                        System.out.println(address.getHostAddress());
                        String anotherIp = ipToip.get(address.getHostAddress());
                        if(anotherIp != null){
                            InetAddress anotherAddress = InetAddress.getByName(anotherIp);
                            packet = new DatagramPacket(buf, buf.length, anotherAddress, 20000);
                            socket.send(packet);
                        }
                    }

                }catch (Exception e){

                }
            }
        };
        (new Thread(task)).start();
    }
    Map<String, String> ipToip = new HashMap<>();
    Map<String, String> usernameToIp = new HashMap<>();

    public Map<String, String> getUsernameToIp() {
        return usernameToIp;
    }

    public Map<String, String> getIpToip() {
        return ipToip;
    }

}
