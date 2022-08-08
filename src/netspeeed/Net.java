/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package netspeeed;


import oshi.SystemInfo;


/**
 *
 * @author rgedam
 */
public class Net {
    public Long sentData=new Long(0);
    public Long recvData=new Long(0);
    public Float downSpeed=new Float(0.0000);
    public Float upSpeed=new Float(0.0000);
    public Float cupSpeed=new Float(0.0000);
    private Boolean flag=true;
    SystemInfo si= new SystemInfo();
    Long sentData1=new Long(0);;
    Long recvData1=new Long(0);;
    Long totalSentData;
    Long totalRecvData;
    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
 
    public void update() throws InterruptedException {

        while (flag) {
            
          
            totalSentData = (sentData - sentData1);
            totalRecvData = (recvData - recvData1);
            recvData1 = recvData;
            sentData1 = sentData;
            upSpeed = totalSentData.floatValue() / Float.valueOf(1024);
            downSpeed = totalRecvData.floatValue() / Float.valueOf(1024);
            Thread.sleep(1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    
                    sentData=(si.getHardware().getNetworkIFs().get(0).getBytesSent()/1024);
                    recvData=(si.getHardware().getNetworkIFs().get(0).getBytesRecv()/1024);
                   
                }

            }).start();
        }

    }
}
