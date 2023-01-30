package com.vishwas.employee.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class Listener {

    @KafkaListener(groupId = "vishwas-1", topics = "vishwasTopic", containerFactory = "kafkaListenerContainerFactory")
    public void getMessageFromTopic(String data) {
        System.out.println(data);
        executeShellScript();
    }

    private static class ProcessReader implements Callable {
        private InputStream inputStream;

        public ProcessReader(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public Object call() throws Exception {
            return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.toList());
        }
    }

    public void executeShellScript() {
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("sh","/Users/umashav1/Study/DockerFiles/start.sh");
        ExecutorService pool = Executors.newSingleThreadExecutor();
        try {
            Process process = builder.start();
            ProcessReader task = new ProcessReader(process.getInputStream());
            Future<List<String>> future = pool.submit(task);
            List<String> results = future.get();
            for (String res : results) {
                System.out.println(res);
            }
            process.waitFor();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            pool.shutdown();
        }
    }
}
