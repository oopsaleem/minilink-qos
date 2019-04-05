package com.kgwb.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class MiniLinkDeviceConfigWrapper {

    public MiniLinkDeviceConfigWrapper() { }

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
        this.tcQueue = new SimpleStringProperty(String.join(", ", tcQueue) );
    }

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

    private SimpleBooleanProperty bridgeAgingEnable = new SimpleBooleanProperty();
    public boolean getBridgeAgingEnable() { return bridgeAgingEnable.get(); }
    public SimpleBooleanProperty bridgeAgingEnableProperty() {
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

    private SimpleStringProperty tcQueue = new SimpleStringProperty();
    public String getTcQueue() { return tcQueue.get(); }
    public SimpleStringProperty tcQueueProperty() {
        return tcQueue;
    }
}
