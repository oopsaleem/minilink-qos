package com.kgwb.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class MiniLinkDeviceConfigWrapper {

    private SimpleStringProperty fileName;
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

    public MiniLinkDeviceConfigWrapper(String fileName,
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
                                       String[] tcQueue) {
        this.fileName = new SimpleStringProperty(fileName);
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
}
