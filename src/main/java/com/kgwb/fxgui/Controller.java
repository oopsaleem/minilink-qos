package com.kgwb.fxgui;

import com.kgwb.model.MiniLinkDeviceConfigWrapper;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;


public class Controller implements Initializable {
    @FXML
    private TableView tableView;
    @FXML
    private ParallelProgressBar progress;
    @FXML
    private Label rightStatusLabel;

    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private TextField urlTextEntry;
    @FXML
    private Button btnChangePathAndRun;
    @FXML
    private Button btnRun;

    ArrayList<String> myTexts = new ArrayList<String>();


    public void initialize(URL location, ResourceBundle resources) {
        urlTextEntry.setPromptText("Click [Change ...] to select folder of Mini-Link QoS *.cfg files.");

        btnChangePathAndRun.setOnAction(e -> {
            if (progress.isRunning()) return;

            Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            String browsedPath = browseFolder(primaryStage);
            if (browsedPath == null) return;

            urlTextEntry.setText(browsedPath);
            startProcess();
        });


        btnRun.setOnAction(e -> {
            if (progress.isRunning()) return;

            String currentFolderPath = urlTextEntry.getText();

            File folder = new File(currentFolderPath);
            if (!folder.exists() || !folder.isDirectory()) {
                Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                String browsedPath = browseFolder(primaryStage);
                if (browsedPath == null) return;
                urlTextEntry.setText(browsedPath);
            }

            btnRun.setDisable(true);
            progress.setVisible(true);
            rightStatusLabel.setText("...");

            startProcess();
        });

    }

    private String browseFolder(Stage primaryStage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
//        directoryChooser.setInitialDirectory(new File(initDir));
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        if (selectedDirectory == null) return null;

        return selectedDirectory.getAbsolutePath();
    }

    private ObservableList<MiniLinkDeviceConfigWrapper> mlConfigData = FXCollections.observableArrayList();

    private void startProcess() {
        File folder = new File(urlTextEntry.getText());
        if (!(folder.exists() || folder.isDirectory())) return;

        final File[] files = folder.listFiles();
        progress.start(files.length);

        new Thread(() -> {
            long ms = System.currentTimeMillis();
            Stream.of(files).parallel().forEach(file -> {
                progress.add(1);
                if (!file.isDirectory()) {
                    try {
                        MiniLinkDeviceConfigWrapper configWrapper = process(file);
                        mlConfigData.add(configWrapper);
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }
                }
            });
            Platform.runLater(() -> {
                rightStatusLabel.setText("" + (System.currentTimeMillis() - ms) + " ms");
                tableView.setItems(mlConfigData);
                btnRun.setDisable(false);
                progress.setVisible(false);

            });
        }).start();
    }

    private MiniLinkDeviceConfigWrapper process(File file) throws IOException {
        MiniLinkDeviceConfigWrapper configWrapper;

        String fileName, ml_su_activerelease, ml_brg_nt_pcp_selection, ml_brg_prio_m_type, ml_brg_sdlr_profile, ml_brg_qu_set_profile, ml_brg_sdlr_profile_name, ml_brg_qu_set_profile_name, ml_su_release;
        ml_su_activerelease = ml_brg_nt_pcp_selection = ml_brg_prio_m_type = ml_brg_sdlr_profile = ml_brg_qu_set_profile = ml_brg_sdlr_profile_name = ml_brg_qu_set_profile_name = ml_su_release = "";

        fileName = file.getName();
        boolean ml_brg_aging_enable, flag_brg_aging, flag_eth_prf;
        ml_brg_aging_enable = flag_brg_aging = flag_eth_prf = false;

        List<String> ml_list_brg_prio_m_map = new ArrayList<>();
        List<String> ml_list_brg_aging = new ArrayList<>();
        List<String> ml_list_tc_sdlr_typeN_weight = new ArrayList<>();
        List<String> ml_list_tc_qu = new ArrayList<>();

        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")) {
            while (it.hasNext()) {
                String line = it.nextLine();
                if (line.startsWith("!") || line.trim().isEmpty()) continue;

                if (line.startsWith("su-activerelease")) {
                    ml_su_activerelease = line.replace("su-activerelease ", "");
                    continue;
                }

                if (line.startsWith("su-release ")) {
                    if (line.endsWith(ml_su_activerelease)) {
                        try {
                            Pattern regex = Pattern.compile("\\d+\\.\\d+.{2,} " + ml_su_activerelease);
                            Matcher regexMatcher = regex.matcher(line);
                            if (regexMatcher.find()) {
                                ml_su_release = regexMatcher.group();
                            }
                        } catch (PatternSyntaxException ex) {
                            // Syntax error in the regular expression
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

                /*
                if (line.startsWith("bridge scheduler-profile ")) {
                    ml_brg_sdlr_profile = line.replace("bridge scheduler-profile ", "");
                    continue;
                }
                if (line.startsWith("bridge queue-set-profile ")) {
                    ml_brg_qu_set_profile = line.replace("bridge queue-set-profile  ", "");
                    continue;
                }
                */

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
                    System.out.println();
                    continue;
                }

                if (flag_eth_prf) {
                    if (!line.startsWith(" ")) {
                        flag_eth_prf = false;
                    } else if (line.startsWith(" scheduler-profile ")) {
                        try {
                            Pattern regex = Pattern.compile(" scheduler-profile (?<profile>\\d+) name \\\"(?<name>.*?)\"");
                            Matcher regexMatcher = regex.matcher(line);
                            if (regexMatcher.find()) {
                                ml_brg_sdlr_profile = regexMatcher.group("profile");
                                ml_brg_sdlr_profile_name = regexMatcher.group("name");
                            }
                        } catch (PatternSyntaxException ignore) {
                        }
                    } else if (line.startsWith("  tc-scheduler-type-and-weight ")) {
                        ml_list_tc_sdlr_typeN_weight.add(line.replace("  tc-scheduler-type-and-weight ", ""));
                    } else if (line.startsWith(" queue-set-profile ")) {
                        try {
                            Pattern regex = Pattern.compile(" queue-set-profile (?<profile>\\d+) name \\\"(?<name>.*?)\"");
                            Matcher regexMatcher = regex.matcher(line);
                            if (regexMatcher.find()) {
                                ml_brg_qu_set_profile = regexMatcher.group("profile");
                                ml_brg_qu_set_profile_name = regexMatcher.group("name");
                            }
                        } catch (PatternSyntaxException ignore) {
                        }
                    } else if (line.startsWith("   tc-queue ")) {
                        ml_list_tc_qu.add(line.replace("   tc-queue ", ""));
                    } else continue;
                }
            }
        }

        return new MiniLinkDeviceConfigWrapper(fileName, ml_su_release, ml_brg_prio_m_type, ml_brg_nt_pcp_selection, ml_list_brg_prio_m_map.toArray(new String[0]), ml_brg_sdlr_profile, ml_brg_qu_set_profile,
                ml_brg_aging_enable, ml_list_brg_aging.toArray(new String[0]), ml_brg_sdlr_profile_name, ml_list_tc_sdlr_typeN_weight.toArray(new String[0]), ml_brg_qu_set_profile_name, ml_list_tc_qu.toArray(new String[0]));
    }
}


//        personTable.setMinWidth(1024);
//        nameCol.prefWidthProperty().bind(personTable.widthProperty().divide(4)); // w * 1/4
//        surnameCol.prefWidthProperty().bind(personTable.widthProperty().divide(2)); // w * 1/2
//        emailCol.prefWidthProperty().bind(personTable.widthProperty().divide(4)); // w * 1/4
//        personTable.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY);
//        nameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 20 ); // 50% width
//        surnameCol.setMaxWidth( 1f * Integer.MAX_VALUE * 70 ); // 30% width
//        emailCol.setMaxWidth( 1f * Integer.MAX_VALUE * 10 ); // 20% width
//        autoFitTable(personTable);
//        personTable.setColumnResizePolicy(p -> true);