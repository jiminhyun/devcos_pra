package com.example.springtheory.temp_20260629;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private final File dir;
    private final File file;
    private static final DateTimeFormatter now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public FileLogger() {
        this.dir = new File(System.getProperty("user.home"), "OneDrive/Desktop/app-logs");
        this.file = new File(dir, "app.log");
    }

    public void log(String level, String message){
        if(!dir.exists()) {
            dir.mkdir();
        }
        String line = LocalDateTime.now().format(now) + " [" + level + "] " + message + "\n";
        try (
                FileWriter fw = new FileWriter(file, true);
                ){
            fw.write(line);
        } catch (IOException e) {
            System.out.println("로그: 실패" + e.getMessage());
        }
    }

    public String getLogFilePath() {
        return file.getAbsolutePath();
    }
}
