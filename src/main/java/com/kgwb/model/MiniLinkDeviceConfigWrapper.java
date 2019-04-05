package com.kgwb.model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class MiniLinkDeviceConfigWrapper {

    public MiniLinkDeviceConfigWrapper() { }

//    public MiniLinkDeviceConfigWrapper(SimpleStringProperty fileName, SimpleStringProperty softwareVersion, SimpleStringProperty bridgePriorityMappingType, SimpleStringProperty bridgeNtPcpSelection, SimpleStringProperty bridgePriorityMappingMap, SimpleStringProperty bridgeSchedulerProfile, SimpleStringProperty bridgeQueueSetProfile, SimpleStringProperty bridgeAgingEnable, SimpleStringProperty bridgeAging, SimpleStringProperty schedulerProfileName, SimpleStringProperty tcSchedarTypeWeight, SimpleStringProperty queueSetProfileName, String[] tcQueue) {
//        this.fileName = fileName;
//        this.softwareVersion = softwareVersion;
//        this.bridgePriorityMappingType = bridgePriorityMappingType;
//        this.bridgeNtPcpSelection = bridgeNtPcpSelection;
//        this.bridgePriorityMappingMap = bridgePriorityMappingMap;
//        this.bridgeSchedulerProfile = bridgeSchedulerProfile;
//        this.bridgeQueueSetProfile = bridgeQueueSetProfile;
//        this.bridgeAgingEnable = bridgeAgingEnable;
//        this.bridgeAging = bridgeAging;
//        this.schedulerProfileName = schedulerProfileName;
//        this.tcSchedarTypeWeight = tcSchedarTypeWeight;
//        this.queueSetProfileName = queueSetProfileName;
//        this.tcQueue = tcQueue;
//    }

    private SimpleStringProperty fileName = new SimpleStringProperty();
    public String getFileName() { return fileName.get(); }
    public SimpleStringProperty fileNameProperty() { return fileName; }

    private SimpleStringProperty softwareVersion = new SimpleStringProperty();
    public String getSoftwareVersion() {
        return softwareVersion.get();
    }
    public SimpleStringProperty softwareVersionProperty() {
        return softwareVersion;
    }

    private SimpleStringProperty bridgePriorityMappingType = new SimpleStringProperty();
    public String getBridgePriorityMappingType() {
        return bridgePriorityMappingType.get();
    }
    public SimpleStringProperty bridgePriorityMappingTypeProperty() {
        return bridgePriorityMappingType;
    }

    private SimpleStringProperty bridgeNtPcpSelection = new SimpleStringProperty();
    public String getBridgeNtPcpSelection() {
        return bridgeNtPcpSelection.get();
    }
    public SimpleStringProperty bridgeNtPcpSelectionProperty() {
        return bridgeNtPcpSelection;
    }

    private SimpleStringProperty bridgePriorityMappingMap = new SimpleStringProperty();
    public String getBridgePriorityMappingMap() {
        return bridgePriorityMappingMap.get();
    }
    public SimpleStringProperty bridgePriorityMappingMapProperty() {
        return bridgePriorityMappingMap;
    }

    private SimpleStringProperty bridgeSchedulerProfile = new SimpleStringProperty();
    public String getBridgeSchedulerProfile() {
        return bridgeSchedulerProfile.get();
    }
    public SimpleStringProperty bridgeSchedulerProfileProperty() {
        return bridgeSchedulerProfile;
    }

    private SimpleStringProperty bridgeQueueSetProfile = new SimpleStringProperty();
    public String getBridgeQueueSetProfile() {
        return bridgeQueueSetProfile.get();
    }
    public SimpleStringProperty bridgeQueueSetProfileProperty() {
        return bridgeQueueSetProfile;
    }

    private SimpleStringProperty bridgeAgingEnable = new SimpleStringProperty();
    public String getBridgeAgingEnable() {
        return bridgeAgingEnable.get();
    }
    public SimpleStringProperty bridgeAgingEnableProperty() {
        return bridgeAgingEnable;
    }

    private SimpleStringProperty bridgeAging = new SimpleStringProperty();
    public String getBridgeAging() {
        return bridgeAging.get();
    }
    public SimpleStringProperty bridgeAgingProperty() { return bridgeAging; }

    private SimpleStringProperty schedulerProfileName = new SimpleStringProperty();
    public String getSchedulerProfileName() {
        return schedulerProfileName.get();
    }
    public SimpleStringProperty schedulerProfileNameProperty() {
        return schedulerProfileName;
    }

    private SimpleStringProperty tcSchedarTypeWeight = new SimpleStringProperty();
    public String getTcSchedarTypeWeight() {
        return tcSchedarTypeWeight.get();
    }
    public SimpleStringProperty tcSchedarTypeWeightProperty() {
        return tcSchedarTypeWeight;
    }

    private SimpleStringProperty queueSetProfileName = new SimpleStringProperty();
    public String getQueueSetProfileName() {
        return queueSetProfileName.get();
    }
    public SimpleStringProperty queueSetProfileNameProperty() {
        return queueSetProfileName;
    }

    private List<SimpleStringProperty> tcQueue = new ArrayList<>();
    public String[] getTcQueue() { return transformerFrom(tcQueue); }
    public List<SimpleStringProperty> tcQueueProperty() {
        return tcQueue;
    }

    private String[] transformerFrom(List<SimpleStringProperty> items) {
        return null;
    }


}
