package com.kgwb.fxgui;

import com.kgwb.model.MiniLinkDeviceConfigWrapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


public class Controller implements Initializable {
    @FXML
    private Button btnExport;
    @FXML
    private AnchorPane contentAnchorPane;
    @FXML
    private TableView<MiniLinkDeviceConfigWrapper> tableView;
    @FXML
    private ParallelProgressBar progress;
    @FXML
    private Label rightStatusLabel;
    @FXML
    private TextField urlTextEntry;
    @FXML
    private Button btnChangePathAndRun;
    @FXML
    private Button btnRun;

    public void initialize(URL location, ResourceBundle resources) {
        tableView = new TableView<>();

        Map<String, String> fieldCaption = new LinkedHashMap<>();

        fieldCaption.put("file name", "fileName");
        fieldCaption.put("site", "siteId");
        fieldCaption.put("ip address", "ipAddress");
        fieldCaption.put("software version", "softwareVersion");
        fieldCaption.put("bridge priority mapping type", "bridgePriorityMappingType");
        fieldCaption.put("bridge network pcp selection", "bridgeNtPcpSelection");
        fieldCaption.put("bridge priority mapping map", "bridgePriorityMappingMap");
        fieldCaption.put("bridge scheduler profile", "bridgeSchedulerProfile");
        fieldCaption.put("bridge queue set profile", "bridgeQueueSetProfile");
        fieldCaption.put("bridge aging enable", "bridgeAgingEnable");
        fieldCaption.put("bridge aging", "bridgeAging");
        fieldCaption.put("scheduler profile name", "schedulerProfileName");
        fieldCaption.put("tc scheduler type and weight", "tcSchedarTypeWeight");
        fieldCaption.put("queue set profile name", "queueSetProfileName");
        fieldCaption.put("tc queue", "tcQueue");
        fieldCaption.put("eth int 1", "ethernetInterface1");
        fieldCaption.put("eth int 2", "ethernetInterface2");
        fieldCaption.put("eth int 3", "ethernetInterface3");
        fieldCaption.put("eth int 4", "ethernetInterface4");
        fieldCaption.put("eth int 5", "ethernetInterface5");
        fieldCaption.put("eth int 6", "ethernetInterface6");
        fieldCaption.put("eth int 7", "ethernetInterface7");
        fieldCaption.put("eth int 8", "ethernetInterface8");
//        fieldCaption.put("eth int 9", "ethernetInterface9");
//        fieldCaption.put("eth int 10", "ethernetInterface10");
//        fieldCaption.put("eth int 11", "ethernetInterface11");
//        fieldCaption.put("eth int 12", "ethernetInterface12");
//        fieldCaption.put("eth int 13", "ethernetInterface13");
//        fieldCaption.put("eth int 14", "ethernetInterface14");
//        fieldCaption.put("eth int 15", "ethernetInterface15");
//        fieldCaption.put("eth int 16", "ethernetInterface16");
//        fieldCaption.put("eth int 17", "ethernetInterface17");
//        fieldCaption.put("eth int 18", "ethernetInterface18");
//        fieldCaption.put("eth int 19", "ethernetInterface19");
//        fieldCaption.put("eth int 20", "ethernetInterface20");

        fieldCaption.forEach((k, v) -> {
            TableColumn<MiniLinkDeviceConfigWrapper, String> column = new TableColumn<>(k);
            column.setId(v + "Col");
            column.setCellValueFactory(new PropertyValueFactory<>(v));
            tableView.getColumns().add(column);
        });

        tableView.setRowFactory(tv -> {
            TableRow<MiniLinkDeviceConfigWrapper> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    MiniLinkDeviceConfigWrapper clickedRow = row.getItem();

                    File file = new File(String.format("%s/%s", urlTextEntry.getText(), clickedRow.getFileName()));
                    try {
                        java.awt.Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        exceptionDialog("Error Opening File", "Unable to open File !", file.getPath(), e);
                    }
                }
            });
            return row;
        });
        contentAnchorPane.getChildren().add(tableView);
        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);

        urlTextEntry.setPromptText("Click [Change ...] to select folder of Mini-Link QoS *.cfg files.");

        btnChangePathAndRun.setOnAction(event -> {
            if (progress.isRunning()) return;
            if (FileSourceNotReady(event, true)) return;
            startProcess(true);
        });


        btnRun.setOnAction(event -> {
            if (FileSourceNotReady(event, false)) return;
            startProcess(true);
        });

        btnExport.setOnAction(event -> {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MS Excel 2007 files (*.xls)", "*.xls");
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(extFilter);
            File file = chooser.showSaveDialog(primaryStage);

            if (file == null) return;

            Workbook workbook = new HSSFWorkbook();
            Sheet spreadsheet = workbook.createSheet("QoS");

            Row row = spreadsheet.createRow(0);

            for (int j = 0; j < tableView.getColumns().size(); j++) {
                row.createCell(j).setCellValue(tableView.getColumns().get(j).getText());
            }

            for (int i = 0; i < tableView.getItems().size(); i++) {
                row = spreadsheet.createRow(i + 1);
                for (int j = 0; j < tableView.getColumns().size(); j++) {
                    if (tableView.getColumns().get(j).getCellData(i) != null) {
                        row.createCell(j).setCellValue(tableView.getColumns().get(j).getCellData(i).toString());
                    } else {
                        row.createCell(j).setCellValue("");
                    }
                }
            }

            FileOutputStream fileOut;
            try {
                fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                java.awt.Desktop.getDesktop().open(file);
            } catch (FileNotFoundException e) {
//                messageBox("Export Error", e.getMessage());
                exceptionDialog("Export Error", "Could not export file !", file.getPath(), e);
            } catch (IOException e) {
                exceptionDialog("Export Error", "Could not export file due to Input/Output Error.", file.getPath(), e);
            }
        });
    }

    private boolean FileSourceNotReady(ActionEvent event, boolean forceChange) {
        String currentFolderPath = urlTextEntry.getText();
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        String browsedPath;
        File folder = new File(currentFolderPath);

        if (folder.exists() && folder.isDirectory() && !forceChange)
            browsedPath = folder.getAbsolutePath();
        else {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            if (folder.exists()) {
                directoryChooser.setInitialDirectory(folder);
            }
            File fileSelected = directoryChooser.showDialog(primaryStage);
            browsedPath = fileSelected != null ? fileSelected.getAbsolutePath() : null;
        }

        if (browsedPath == null) return true;
        urlTextEntry.setText(browsedPath);
        return false;
    }

//    private String verifyOrBrowseFolder(Stage primaryStage, String initDir, boolean forceChange) {
//        File folder = new File(initDir);
//
//        if (folder.exists() && folder.isDirectory() && !forceChange)
//            return folder.getAbsolutePath();
//        else {
//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            if (folder.exists()) {
//                directoryChooser.setInitialDirectory(folder);
//            }
//            File selectedDirectory = directoryChooser.showDialog(primaryStage);
//            return selectedDirectory != null ? selectedDirectory.getAbsolutePath() : null;
//        }
//    }

    private void startProcess(boolean start) {
        btnRun.setText(start ? "Stop" : "Start");
        progress.setVisible(start);
        if (!start) return;
        //else rightStatusLabel.setText( "...");

        File folder = new File(urlTextEntry.getText());
        if (!(folder.exists() || folder.isDirectory())) return;

        final File[] files = folder.listFiles();
        int max = files != null ? files.length : 0;
        progress.start(max);

        //https://docs.oracle.com/javase/8/javafx/api/javafx/concurrent/Task.html
        //https://stackoverflow.com/questions/32498307/in-javafx-is-an-observablearraylist-thread-safe

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                int iterations;
                long ms = System.currentTimeMillis();
                ObservableList<MiniLinkDeviceConfigWrapper> dataObjects = FXCollections.observableArrayList();
                for (iterations = 0; iterations < max; iterations++) {
                    if (isCancelled()) {
                        updateMessage("Cancelled");
                        progress.stop();
                        break;
                    }
                    updateMessage("Processing " + iterations);
                    updateProgress(iterations, max);

                    progress.add(1);
                    try {
                        MiniLinkDeviceConfigWrapper configWrapper = process(files[iterations]);
                        dataObjects.add(configWrapper);
                    } catch (IOException interrupted) {
                        if (isCancelled()) {
                            updateMessage("Cancelled");
                            progress.stop();
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                Platform.runLater(() -> {
                    String progressMessage = getMessage();
                    if (progressMessage.contentEquals("Cancelled")) {
                        rightStatusLabel.setText("Cancelled !");
                    } else {
                        rightStatusLabel.setText(String.format("%d files processed in %d ms.", max, +(System.currentTimeMillis() - ms)));
                        tableView.setItems(dataObjects);
                    }

                    startProcess(false);
                });

                return null;
            }
        };

        btnRun.setOnAction(event -> { //Start/Stop
            if (task.isRunning()) {
                task.cancel();
                return;
            }

            if (FileSourceNotReady(event, false)) return;

            btnRun.setText("Stop");
            progress.setVisible(true);
            rightStatusLabel.setText("...");

            startProcess(true);
        });

        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private MiniLinkDeviceConfigWrapper process(File file) throws IOException {
        String ml_su_activeRelease = "";
        String ml_brg_nt_pcp_selection = "";
        String ml_brg_prio_m_type = "";
        String key_current_sdlr_profile = "";
        String key_current_qu_set_profile = "";
        String ml_brg_sdlr_profile = "";
        String ml_brg_qu_set_profile = "";
        String ml_su_release = "";
        String ml_ip_address = "";
        String fileName = file.getName();
        String siteId = "";

        try {
            Pattern regex = Pattern.compile("NE.\\d+.\\w+-(?<site>\\d+-\\w+)_");
            Matcher regexMatcher = regex.matcher(fileName);
            if (regexMatcher.find()) {
                siteId = regexMatcher.group("site");
            }
        } catch (PatternSyntaxException ignore) {
        }

        boolean ml_brg_aging_enable = false;
        boolean flag_brg_aging = false;
        boolean flag_eth_prf = false;
        boolean flag_int_eth = false;
        boolean flag_bridge_port = false;
        boolean flag_policing = false;

        float su_release = 0.0f;

        Map<String, List<String>> ml_map_int_eth_port = new HashMap<>();
        Map<String, List<String>> ml_map_sdlr_profile = new HashMap<>();
        Map<String, List<String>> ml_map_qu_set_profile = new HashMap<>();
        List<String> ml_list_brg_prio_m_map = new ArrayList<>();
        List<String> ml_list_brg_aging = new ArrayList<>();
//        List<String> ml_list_tc_sdlr_typeN_weight = new ArrayList<>();
//        List<String> ml_list_tc_qu = new ArrayList<>();

        String currentIntEthKey = "";
        String currentIntEthernetWanLanDCN = "";

        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                if (line.startsWith("!") || line.trim().isEmpty()) continue;

                if (line.startsWith("su-activerelease")) {
                    ml_su_activeRelease = line.replace("su-activerelease ", "");
                    continue;
                }

                if (line.startsWith(" ip address ")) {
                    try {
                        Pattern regex = Pattern.compile(" ip address (?<ip>[0-9.]+)(/(?<CIDR>\\d+)| (?<mask>[0-9.]+))");
                        Matcher regexMatcher = regex.matcher(line);
                        if (regexMatcher.find()) {
                            ml_ip_address = regexMatcher.group("ip");
                        }
                    } catch (PatternSyntaxException ignore) {
                        //Syntax error in the regular expression.
                    }
                }
                if (line.startsWith("su-release ")) {
                    if (line.endsWith(ml_su_activeRelease)) {
                        try {
                            Pattern regex = Pattern.compile("\\d+\\.\\d+.{2,} " + ml_su_activeRelease);
                            Matcher regexMatcher = regex.matcher(line);
                            if (regexMatcher.find()) {
                                ml_su_release = regexMatcher.group().replace(" " + ml_su_activeRelease, "");
                                if (ml_su_release.startsWith("1.7")) su_release = 1.7f;
                                else if (ml_su_release.startsWith("1.5")) su_release = 1.5f;
                                else if (ml_su_release.startsWith("1.3")) su_release = 1.3f;
                            }
                        } catch (PatternSyntaxException ignore) {
                            //Syntax error in the regular expression.
                        }
                    }
                    continue;
                }

                if (line.startsWith("bridge priority-mapping type")) {
                    ml_brg_prio_m_type = line.replace("bridge priority-mapping type ", "");
                    continue;
                }
                if (line.startsWith("bridge network-pcp-selection")) {
                    ml_brg_nt_pcp_selection = line.replace("bridge network-pcp-selection ", "");
                    continue;
                }

                if (line.startsWith("bridge priority-mapping map")) {
                    ml_list_brg_prio_m_map.add(line.replace("bridge priority-mapping map ", ""));
                    continue;
                }

                if (line.startsWith("bridge scheduler-profile ")) {
                    ml_brg_sdlr_profile = line.replace("bridge scheduler-profile ", "");
                    continue;
                }
                if (line.startsWith("bridge queue-set-profile ")) {
                    ml_brg_qu_set_profile = line.replace("bridge queue-set-profile ", "");
                    continue;
                }

                if (!flag_brg_aging) {
                    if (line.startsWith("bridge aging ")) {
                        flag_brg_aging = true;
                        ml_brg_aging_enable = line.contains("bridge aging enable");
                        continue;
                    }
                } else if (line.startsWith("bridge aging ")) {
                    ml_list_brg_aging.add(line.replace("bridge aging ", ""));
                    continue;
                }

                if (line.contentEquals("ethernet-profiles")) {
                    flag_eth_prf = true;
                    continue;
                }

                if (flag_eth_prf) {
                    if (!line.startsWith(" ")) {
                        flag_eth_prf = false;
                    } else if (line.startsWith(" scheduler-profile ")) {
                        try {
                            Pattern regex = Pattern.compile(" scheduler-profile (?<profile>\\d+) name \"(?<name>.*?)\"");
                            Matcher regexMatcher = regex.matcher(line);
                            if (regexMatcher.find()) {
                                String sdlr_profile = regexMatcher.group("profile");
                                String sdlr_profile_name = regexMatcher.group("name");
                                key_current_sdlr_profile = String.format("%s, %s", sdlr_profile, sdlr_profile_name);
                                ml_map_sdlr_profile.put(key_current_sdlr_profile, new ArrayList<>());
                            }
                        } catch (PatternSyntaxException ignore) {
                        }
                    } else if (line.startsWith("  tc-scheduler-type-and-weight ")) {
                        if (!key_current_sdlr_profile.isEmpty() && ml_map_sdlr_profile.containsKey(key_current_sdlr_profile)) {
                            ml_map_sdlr_profile.get(key_current_sdlr_profile).add(line.replace("  tc-scheduler-type-and-weight ", ""));
                        }
//                        ml_list_tc_sdlr_typeN_weight.add(line.replace("  tc-scheduler-type-and-weight ", ""));
                    } else if (line.startsWith(" queue-set-profile ")) {
                        try {
                            Pattern regex = Pattern.compile(" queue-set-profile (?<profile>\\d+) name \"(?<name>.*?)\"");
                            Matcher regexMatcher = regex.matcher(line);
                            if (regexMatcher.find()) {
                                key_current_qu_set_profile = String.format("%s, %s", regexMatcher.group("profile"), regexMatcher.group("name"));
                                ml_map_qu_set_profile.put(key_current_qu_set_profile, new ArrayList<>());
                            }
                        } catch (PatternSyntaxException ignore) {
                        }
                    } else if (line.startsWith("   tc-queue ")) {
                        if (!key_current_qu_set_profile.isEmpty() && ml_map_qu_set_profile.containsKey(key_current_qu_set_profile)) {
                            ml_map_qu_set_profile.get(key_current_qu_set_profile).add(line.replace("   tc-queue ", ""));
                        }
//                        ml_list_tc_qu.add(line.replace("   tc-queue ", ""));
                    } else continue;
                }

                if (!flag_int_eth && line.startsWith("interface ethernet")) {
                    Pattern regex = Pattern.compile("interface ethernet(-eps)? (?<eth>\\d+/\\d+(\\+\\d+)?/\\d+)(?<lanwan>[ a-z-]+)?");
                    Matcher regexMatcher = regex.matcher(line);
                    if (regexMatcher.find()) {
                        currentIntEthernetWanLanDCN = regexMatcher.group("lanwan");
                        if (currentIntEthernetWanLanDCN == null || currentIntEthernetWanLanDCN.isEmpty())
                            currentIntEthKey = regexMatcher.group("eth");
                        else
                            currentIntEthKey = regexMatcher.group("eth") + currentIntEthernetWanLanDCN;
                        ml_map_int_eth_port.put(currentIntEthKey, new ArrayList<>());
                        flag_int_eth = true;
                    }
                    continue;
                }

                if (flag_int_eth && su_release == 1.7f) {
                    if (line.startsWith(" exit"))
                        flag_int_eth = false;
                    else if (line.startsWith(" !LAN") || line.startsWith(" !WAN")) { // !LAN-DCN 1/4/0 - Port 1
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith(" name ")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith(" no shutdown") || line.startsWith(" shutdown")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith(" no trapenable") || line.startsWith(" trapenable")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith(" role")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    }
                    if (line.startsWith(" scheduler-profile ")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    }
                    if (line.startsWith(" queue-set-profile ")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith("  no alarm-enable ethernet-down") || line.startsWith("  alarm-enable ethernet-down")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith("  trusted")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith("   exit")) {
                        flag_policing = false;
                    } else if (line.startsWith("  policing")) {
                        flag_policing = true;
                    } else if (flag_policing && line.startsWith("   mode")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith("  user-priority-mapping ")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.replace("  user-priority-mapping ", ""));
                    } else if (line.startsWith("  scheduler-profile ")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    } else if (line.startsWith("  queue-set-profile ")) {
                        ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                    }
                }//1.7

                if (su_release == 1.3f) {
                    if (flag_int_eth) {
                        if (line.startsWith(" exit")) {
                            flag_int_eth = false;
                        } else if (line.startsWith(" usage bridge-port ")) { // !LAN-DCN 1/4/0 - Port 1
                            ml_map_int_eth_port.get(currentIntEthKey).add(String.format("!%s %s - Port %s", currentIntEthernetWanLanDCN.trim().toUpperCase(), currentIntEthKey.replace(currentIntEthernetWanLanDCN, ""), line.replace(" usage bridge-port ", "")));
                        } else if (line.startsWith(" alias ")) {
                            ml_map_int_eth_port.get(currentIntEthKey).add(line.trim().replace("alias ", "name "));
                        } else if (line.startsWith("  no shutdown") || line.startsWith("  shutdown")) {
                            ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                        } else if (line.startsWith("  no trapenable") || line.startsWith("  trapenable")) {
                            ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                        } else if (line.startsWith("  no alarm-enable ethernet-down") || line.startsWith("  alarm-enable ethernet-down")) {
                            ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                        }
                    }

                    if (line.startsWith("bridge-port")) {
                        int lastIndexOfs = line.lastIndexOf(" ");
                        final String substring = line.substring(lastIndexOfs + 1);

                        final String[] portLanKey = {""};
                        for (Map.Entry<String, List<String>> entry : ml_map_int_eth_port.entrySet()) {
                            String key = entry.getKey();
                            List<String> vList = entry.getValue();
                            boolean found = false;
                            for (String item : vList) {
                                if (item.endsWith("Port " + substring)) {
                                    portLanKey[0] = key;
                                    found = true;
                                    break;
                                }
                            }
                            if (found) break;
                        }

                        currentIntEthKey = portLanKey[0];

                        flag_bridge_port = !currentIntEthKey.isEmpty();

                        continue;
                    }

                    if (flag_bridge_port) {
                        if (line.startsWith(" exit")) flag_bridge_port = false;

                        if (!currentIntEthKey.isEmpty()) {
                            if (line.startsWith(" role")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            }
                            if (line.startsWith(" scheduler-profile ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            }
                            if (line.startsWith(" queue-set-profile ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            } else if (line.startsWith(" trusted")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            } else if (line.startsWith(" user-priority-mapping ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.replace(" user-priority-mapping ", ""));
                            } else if (line.startsWith("  exit")) {
                                flag_policing = false;
                            } else if (line.startsWith(" policing")) {
                                flag_policing = true;
                            } else if (flag_policing && line.startsWith("  mode ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            } else if (line.startsWith("  user-priority-mapping ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.replace("  user-priority-mapping ", ""));
                            } else if (line.startsWith("  scheduler-profile ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            } else if (line.startsWith("  queue-set-profile ")) {
                                ml_map_int_eth_port.get(currentIntEthKey).add(line.trim());
                            }
                        }
                    }
                } //1.3
            }
        }

        return new MiniLinkDeviceConfigWrapper(
                fileName,
                siteId,
                ml_ip_address,
                ml_su_release,
                ml_brg_sdlr_profile,
                ml_brg_qu_set_profile,
                ml_brg_prio_m_type,
                ml_brg_nt_pcp_selection,
                ml_list_brg_prio_m_map.toArray(new String[0]),
                ml_map_sdlr_profile,
                ml_map_qu_set_profile,
                ml_brg_aging_enable,
                ml_list_brg_aging.toArray(new String[0]),
                ml_map_int_eth_port
        );
    }

    public int convertNetmaskToCIDR(InetAddress netmask){

        byte[] netmaskBytes = netmask.getAddress();
        int cidr = 0;
        boolean zero = false;
        for(byte b : netmaskBytes){
            int mask = 0x80;

            for(int i = 0; i < 8; i++){
                int result = b & mask;
                if(result == 0){
                    zero = true;
                }else if(zero){
                    throw new IllegalArgumentException("Invalid netmask.");
                } else {
                    cidr++;
                }
                mask >>>= 1;
            }
        }
        return cidr;
    }

    //https://code.makery.ch/blog/javafx-dialogs-official/
    private void exceptionDialog(String title, String headerText, String contentText, Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);
        alert.initStyle(StageStyle.UTILITY);
        alert.showAndWait();
    }
}
