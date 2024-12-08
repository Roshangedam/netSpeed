/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netspeeed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

public class Net {

    public Long sentData = 0L;
    public Long recvData = 0L;
    public Float downSpeed = 0.0f;
    public Float upSpeed = 0.0f;

    SystemInfo si = new SystemInfo();
    Long sentData1 = 0L;
    Long recvData1 = 0L;
    Long totalSentData;
    Long totalRecvData;

    public void update() throws InterruptedException {
        // Calculate the difference in data sent and received
        totalSentData = (sentData - sentData1);
        totalRecvData = (recvData - recvData1);
        recvData1 = recvData;
        sentData1 = sentData;

        // Convert to KB/s
        upSpeed = totalSentData.floatValue() / 1024;
        downSpeed = totalRecvData.floatValue() / 1024;

        // Reset sentData and recvData
        sentData = 0L;
        recvData = 0L;

        // Iterate over all network interfaces (Ethernet and WiFi)
        for (NetworkIF netIF : si.getHardware().getNetworkIFs()) {
            // Check for connected interfaces (ignore disconnected ones)
            if (netIF.isConnectorPresent()) {
                String displayName = netIF.getDisplayName().toLowerCase();

                // Check if the interface is WiFi or Ethernet
                if (displayName.contains("wi-fi") || displayName.contains("wlan")) {
                    System.out.println("WiFi interface detected: " + displayName);
                } else if (displayName.contains("ethernet") || displayName.contains("eth")) {
                    System.out.println("Ethernet interface detected: " + displayName);
                }

                // Add data sent and received by this interface
                sentData += netIF.getBytesSent();
                recvData += netIF.getBytesRecv();
            }
        }

        // Convert total sent/recv to KB
        sentData /= 1024;
        recvData /= 1024;
    }
}
