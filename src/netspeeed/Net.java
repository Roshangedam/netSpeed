/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netspeeed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import oshi.SystemInfo;
import oshi.hardware.NetworkIF;

/**
 *
 * @author rgedam
 */
public class Net {

    public Long sentData = new Long(0);
    public Long recvData = new Long(0);
    public Float downSpeed = new Float(0.0000);
    public Float upSpeed = new Float(0.0000);
    public Float cupSpeed = new Float(0.0000);

    SystemInfo si = new SystemInfo();
    Long sentData1 = new Long(0);
    Long recvData1 = new Long(0);
    Long totalSentData;
    Long totalRecvData;

    public void update() throws InterruptedException {

        totalSentData = (sentData - sentData1);
        totalRecvData = (recvData - recvData1);
        recvData1 = recvData;
        sentData1 = sentData;
        upSpeed = totalSentData.floatValue() / Float.valueOf(1024);
        downSpeed = totalRecvData.floatValue() / Float.valueOf(1024);

        int connectedIFsIndex = 0;
        for (NetworkIF x : si.getHardware().getNetworkIFs()) {
            if (!x.isConnectorPresent()) {
                connectedIFsIndex++;
            }
        }

        sentData = (si.getHardware().getNetworkIFs().get(connectedIFsIndex).getBytesSent() / 1024);
        recvData = (si.getHardware().getNetworkIFs().get(connectedIFsIndex).getBytesRecv() / 1024);

    }
}
