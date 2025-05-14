package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class FileDownloadSimulation {
    public static void main(String[] args) {
        List<String> files = Arrays.asList("file1.zip", "file2.zip", "file3.zip");

        ExecutorService executor = Executors.newFixedThreadPool(3);

        List<Callable<String>> downloadTasks = files.stream()
                .map(file -> (Callable<String>) () -> {
                    System.out.println("Downloading " + file + "...");
                    Thread.sleep(2000); // Simula descarga
                    return file + " downloaded.";
                }).toList();

        try {
            List<Future<String>> results = executor.invokeAll(downloadTasks);
            for (Future<String> result : results) {
                System.out.println(result.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}

