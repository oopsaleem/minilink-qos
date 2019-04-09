package com.kgwb.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MiniLinkDeviceConfigWrapper {

    private SimpleStringProperty fileName;
    private SimpleStringProperty siteId;
    private SimpleStringProperty softwareVersion;
    private SimpleStringProperty bridgePriorityMappingType;
    private SimpleStringProperty bridgeNtPcpSelection;
    private SimpleStringProperty bridgePriorityMappingMap;
    private SimpleStringProperty bridgeSchedulerProfile;
    private SimpleStringProperty bridgeQueueSetProfile;
    private SimpleBooleanProperty bridgeAgingEnable;
    private SimpleStringProperty bridgeAging;
    private SimpleStringProperty schedulerProfileName;
    private SimpleStringProperty tcSchedarTypeWeight;
    private SimpleStringProperty queueSetProfileName;
    private SimpleStringProperty tcQueue;
    private SimpleStringProperty ethernetInterface1;
    private SimpleStringProperty ethernetInterface2;
    private SimpleStringProperty ethernetInterface3;
    private SimpleStringProperty ethernetInterface4;
    private SimpleStringProperty ethernetInterface5;
    private SimpleStringProperty ethernetInterface6;
    private SimpleStringProperty ethernetInterface7;
    private SimpleStringProperty ethernetInterface8;
    private SimpleStringProperty ethernetInterface9;
    private SimpleStringProperty ethernetInterface10;
    private SimpleStringProperty ethernetInterface11;
    private SimpleStringProperty ethernetInterface12;
    private SimpleStringProperty ethernetInterface13;
    private SimpleStringProperty ethernetInterface14;
    private SimpleStringProperty ethernetInterface15;
    private SimpleStringProperty ethernetInterface16;
    private SimpleStringProperty ethernetInterface17;
    private SimpleStringProperty ethernetInterface18;
    private SimpleStringProperty ethernetInterface19;
    private SimpleStringProperty ethernetInterface20;

    public MiniLinkDeviceConfigWrapper(String fileName,
                                       String siteId,
                                       String softwareVersion,
                                       String bridgePriorityMappingType,
                                       String bridgeNtPcpSelection,
                                       String[] bridgePriorityMappingMap,
                                       String bridgeSchedulerProfile,
                                       String bridgeQueueSetProfile,
                                       boolean bridgeAgingEnable,
                                       String[] bridgeAging,
                                       String schedulerProfileName,
                                       String[] tcSchedarTypeWeight,
                                       String queueSetProfileName,
                                       String[] tcQueue,
                                       Map<String, List<String>> eths) {
        this.fileName = new SimpleStringProperty(fileName);
        this.siteId = new SimpleStringProperty(siteId);
        this.softwareVersion = new SimpleStringProperty(softwareVersion);
        this.bridgePriorityMappingType = new SimpleStringProperty(bridgePriorityMappingType);
        this.bridgeNtPcpSelection = new SimpleStringProperty(bridgeNtPcpSelection);
        this.bridgePriorityMappingMap = new SimpleStringProperty(String.join(", ", bridgePriorityMappingMap));
        this.bridgeSchedulerProfile = new SimpleStringProperty(bridgeSchedulerProfile);
        this.bridgeQueueSetProfile = new SimpleStringProperty(bridgeQueueSetProfile);
        this.bridgeAgingEnable = new SimpleBooleanProperty(bridgeAgingEnable);
        this.bridgeAging = new SimpleStringProperty(String.join(", ", bridgeAging));
        this.schedulerProfileName = new SimpleStringProperty(schedulerProfileName);
        this.tcSchedarTypeWeight = new SimpleStringProperty(String.join(", ", tcSchedarTypeWeight));
        this.queueSetProfileName = new SimpleStringProperty(queueSetProfileName);
        this.tcQueue = new SimpleStringProperty(String.join(", ", tcQueue));

        int ethi = 0;
        for (Map.Entry<String, List<String>> entry : eths.entrySet()) {
            ethi++;
            switch (ethi) {
                case 1:
                    this.ethernetInterface1 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 2:
                    this.ethernetInterface2 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 3:
                    this.ethernetInterface3 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 4:
                    this.ethernetInterface4 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 5:
                    this.ethernetInterface5 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 6:
                    this.ethernetInterface6 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 7:
                    this.ethernetInterface7 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 8:
                    this.ethernetInterface8 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 9:
                    this.ethernetInterface9 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 10:
                    this.ethernetInterface10 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 11:
                    this.ethernetInterface11 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 12:
                    this.ethernetInterface12 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 13:
                    this.ethernetInterface13 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 14:
                    this.ethernetInterface14 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 15:
                    this.ethernetInterface15 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 16:
                    this.ethernetInterface16 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 17:
                    this.ethernetInterface17 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 18:
                    this.ethernetInterface18 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 19:
                    this.ethernetInterface19 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                case 20:
                    this.ethernetInterface20 = new SimpleStringProperty(String.join(", ", entry.getValue().toArray(new String[0])));
                    break;
                default:
                    System.out.println("Extra columns expected !" +  eths.entrySet().size());
            }
        }
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }

    public String getSiteId() {
        return siteId.get();
    }

    public SimpleStringProperty siteIdProperty() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId.set(siteId);
    }

    public String getSoftwareVersion() {
        return softwareVersion.get();
    }

    public SimpleStringProperty softwareVersionProperty() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion.set(softwareVersion);
    }

    public String getBridgePriorityMappingType() {
        return bridgePriorityMappingType.get();
    }

    public SimpleStringProperty bridgePriorityMappingTypeProperty() {
        return bridgePriorityMappingType;
    }

    public void setBridgePriorityMappingType(String bridgePriorityMappingType) {
        this.bridgePriorityMappingType.set(bridgePriorityMappingType);
    }

    public String getBridgeNtPcpSelection() {
        return bridgeNtPcpSelection.get();
    }

    public SimpleStringProperty bridgeNtPcpSelectionProperty() {
        return bridgeNtPcpSelection;
    }

    public void setBridgeNtPcpSelection(String bridgeNtPcpSelection) {
        this.bridgeNtPcpSelection.set(bridgeNtPcpSelection);
    }

    public String getBridgePriorityMappingMap() {
        return bridgePriorityMappingMap.get();
    }

    public SimpleStringProperty bridgePriorityMappingMapProperty() {
        return bridgePriorityMappingMap;
    }

    public void setBridgePriorityMappingMap(String bridgePriorityMappingMap) {
        this.bridgePriorityMappingMap.set(bridgePriorityMappingMap);
    }

    public String getBridgeSchedulerProfile() {
        return bridgeSchedulerProfile.get();
    }

    public SimpleStringProperty bridgeSchedulerProfileProperty() {
        return bridgeSchedulerProfile;
    }

    public void setBridgeSchedulerProfile(String bridgeSchedulerProfile) {
        this.bridgeSchedulerProfile.set(bridgeSchedulerProfile);
    }

    public String getBridgeQueueSetProfile() {
        return bridgeQueueSetProfile.get();
    }

    public SimpleStringProperty bridgeQueueSetProfileProperty() {
        return bridgeQueueSetProfile;
    }

    public void setBridgeQueueSetProfile(String bridgeQueueSetProfile) {
        this.bridgeQueueSetProfile.set(bridgeQueueSetProfile);
    }

    public boolean isBridgeAgingEnable() {
        return bridgeAgingEnable.get();
    }

    public SimpleBooleanProperty bridgeAgingEnableProperty() {
        return bridgeAgingEnable;
    }

    public void setBridgeAgingEnable(boolean bridgeAgingEnable) {
        this.bridgeAgingEnable.set(bridgeAgingEnable);
    }

    public String getBridgeAging() {
        return bridgeAging.get();
    }

    public SimpleStringProperty bridgeAgingProperty() {
        return bridgeAging;
    }

    public void setBridgeAging(String bridgeAging) {
        this.bridgeAging.set(bridgeAging);
    }

    public String getSchedulerProfileName() {
        return schedulerProfileName.get();
    }

    public SimpleStringProperty schedulerProfileNameProperty() {
        return schedulerProfileName;
    }

    public void setSchedulerProfileName(String schedulerProfileName) {
        this.schedulerProfileName.set(schedulerProfileName);
    }

    public String getTcSchedarTypeWeight() {
        return tcSchedarTypeWeight.get();
    }

    public SimpleStringProperty tcSchedarTypeWeightProperty() {
        return tcSchedarTypeWeight;
    }

    public void setTcSchedarTypeWeight(String tcSchedarTypeWeight) {
        this.tcSchedarTypeWeight.set(tcSchedarTypeWeight);
    }

    public String getQueueSetProfileName() {
        return queueSetProfileName.get();
    }

    public SimpleStringProperty queueSetProfileNameProperty() {
        return queueSetProfileName;
    }

    public void setQueueSetProfileName(String queueSetProfileName) {
        this.queueSetProfileName.set(queueSetProfileName);
    }

    public String getTcQueue() {
        return tcQueue.get();
    }

    public SimpleStringProperty tcQueueProperty() {
        return tcQueue;
    }

    public void setTcQueue(String tcQueue) {
        this.tcQueue.set(tcQueue);
    }

    public String getEthernetInterface1() {
        return ethernetInterface1.get();
    }

    public SimpleStringProperty ethernetInterface1Property() {
        return ethernetInterface1;
    }

    public void setEthernetInterface1(String ethernetInterface1) {
        this.ethernetInterface1.set(ethernetInterface1);
    }

    public String getEthernetInterface2() {
        return ethernetInterface2.get();
    }

    public SimpleStringProperty ethernetInterface2Property() {
        return ethernetInterface2;
    }

    public void setEthernetInterface2(String ethernetInterface2) {
        this.ethernetInterface2.set(ethernetInterface2);
    }

    public String getEthernetInterface3() {
        return ethernetInterface3.get();
    }

    public SimpleStringProperty ethernetInterface3Property() {
        return ethernetInterface3;
    }

    public void setEthernetInterface3(String ethernetInterface3) {
        this.ethernetInterface3.set(ethernetInterface3);
    }

    public String getEthernetInterface4() {
        return ethernetInterface4.get();
    }

    public SimpleStringProperty ethernetInterface4Property() {
        return ethernetInterface4;
    }

    public void setEthernetInterface4(String ethernetInterface4) {
        this.ethernetInterface4.set(ethernetInterface4);
    }

    public String getEthernetInterface5() {
        return ethernetInterface5.get();
    }

    public SimpleStringProperty ethernetInterface5Property() {
        return ethernetInterface5;
    }

    public void setEthernetInterface5(String ethernetInterface5) {
        this.ethernetInterface5.set(ethernetInterface5);
    }

    public String getEthernetInterface6() {
        return ethernetInterface6.get();
    }

    public SimpleStringProperty ethernetInterface6Property() {
        return ethernetInterface6;
    }

    public void setEthernetInterface6(String ethernetInterface6) {
        this.ethernetInterface6.set(ethernetInterface6);
    }

    public String getEthernetInterface7() {
        return ethernetInterface7.get();
    }

    public SimpleStringProperty ethernetInterface7Property() {
        return ethernetInterface7;
    }

    public void setEthernetInterface7(String ethernetInterface7) {
        this.ethernetInterface7.set(ethernetInterface7);
    }

    public String getEthernetInterface8() {
        return ethernetInterface8.get();
    }

    public SimpleStringProperty ethernetInterface8Property() {
        return ethernetInterface8;
    }

    public void setEthernetInterface8(String ethernetInterface8) {
        this.ethernetInterface8.set(ethernetInterface8);
    }

    public String getEthernetInterface9() {
        return ethernetInterface9.get();
    }

    public SimpleStringProperty ethernetInterface9Property() {
        return ethernetInterface9;
    }

    public void setEthernetInterface9(String ethernetInterface9) {
        this.ethernetInterface9.set(ethernetInterface9);
    }

    public String getEthernetInterface10() {
        return ethernetInterface10.get();
    }

    public SimpleStringProperty ethernetInterface10Property() {
        return ethernetInterface10;
    }

    public void setEthernetInterface10(String ethernetInterface10) {
        this.ethernetInterface10.set(ethernetInterface10);
    }

    public String getEthernetInterface11() {
        return ethernetInterface11.get();
    }

    public SimpleStringProperty ethernetInterface11Property() {
        return ethernetInterface11;
    }

    public void setEthernetInterface11(String ethernetInterface11) {
        this.ethernetInterface11.set(ethernetInterface11);
    }

    public String getEthernetInterface12() {
        return ethernetInterface12.get();
    }

    public SimpleStringProperty ethernetInterface12Property() {
        return ethernetInterface12;
    }

    public void setEthernetInterface12(String ethernetInterface12) {
        this.ethernetInterface12.set(ethernetInterface12);
    }

    public String getEthernetInterface13() {
        return ethernetInterface13.get();
    }

    public SimpleStringProperty ethernetInterface13Property() {
        return ethernetInterface13;
    }

    public void setEthernetInterface13(String ethernetInterface13) {
        this.ethernetInterface13.set(ethernetInterface13);
    }

    public String getEthernetInterface14() {
        return ethernetInterface14.get();
    }

    public SimpleStringProperty ethernetInterface14Property() {
        return ethernetInterface14;
    }

    public void setEthernetInterface14(String ethernetInterface14) {
        this.ethernetInterface14.set(ethernetInterface14);
    }

    public String getEthernetInterface15() {
        return ethernetInterface15.get();
    }

    public SimpleStringProperty ethernetInterface15Property() {
        return ethernetInterface15;
    }

    public void setEthernetInterface15(String ethernetInterface15) {
        this.ethernetInterface15.set(ethernetInterface15);
    }

    public String getEthernetInterface16() {
        return ethernetInterface16.get();
    }

    public SimpleStringProperty ethernetInterface16Property() {
        return ethernetInterface16;
    }

    public void setEthernetInterface16(String ethernetInterface16) {
        this.ethernetInterface16.set(ethernetInterface16);
    }

    public String getEthernetInterface17() {
        return ethernetInterface17.get();
    }

    public SimpleStringProperty ethernetInterface17Property() {
        return ethernetInterface17;
    }

    public void setEthernetInterface17(String ethernetInterface17) {
        this.ethernetInterface17.set(ethernetInterface17);
    }

    public String getEthernetInterface18() {
        return ethernetInterface18.get();
    }

    public SimpleStringProperty ethernetInterface18Property() {
        return ethernetInterface18;
    }

    public void setEthernetInterface18(String ethernetInterface18) {
        this.ethernetInterface18.set(ethernetInterface18);
    }

    public String getEthernetInterface19() {
        return ethernetInterface19.get();
    }

    public SimpleStringProperty ethernetInterface19Property() {
        return ethernetInterface19;
    }

    public void setEthernetInterface19(String ethernetInterface19) {
        this.ethernetInterface19.set(ethernetInterface19);
    }

    public String getEthernetInterface20() {
        return ethernetInterface20.get();
    }

    public SimpleStringProperty ethernetInterface20Property() {
        return ethernetInterface20;
    }

    public void setEthernetInterface20(String ethernetInterface20) {
        this.ethernetInterface20.set(ethernetInterface20);
    }
}
