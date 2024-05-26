package com.ko.weaverloft.Service;

import com.ko.weaverloft.dto.DownloadFilesDTO;
import com.ko.weaverloft.dto.DownloadFilesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@Slf4j
public class DownloadService {

    static class FileDownloadCallable implements Callable<String> {

        private String fileLink;
        private static int idx = 1;

        public FileDownloadCallable(String fileLink) {
            this.fileLink = fileLink;
        }

        @Override
        public String call() throws Exception {
            LocalTime startTime = LocalTime.now();

            // file download logic
            String fileSize = downloadFile(fileLink, "C:\\Users\\ko\\Desktop\\fileoutputstream", "downloadedFile" + idx++);

            LocalTime endTime = LocalTime.now();

            return "스레드: " + Thread.currentThread().getName() + " 파일 크기: " + fileSize;
        }
    }

    public static String downloadFile(String url, String dir, String name) {

        try {
            URL downloadURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) downloadURL.openConnection();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                File downloadFile = new File(dir, name);
                FileOutputStream fos = new FileOutputStream(downloadFile);

                int bytesRead;
                byte[] buffer = new byte[4096];

                while((bytesRead = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }

                fos.close();
                is.close();

                return downloadFile.length()/1024 + "Kb";

            } else
                log.info("fail to connect.. {}", responseCode);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public DownloadFilesResponse downloadStart(DownloadFilesDTO downloadFilesDTO) throws InterruptedException, ExecutionException {
        List<String> urls = downloadFilesDTO.getUrls();
        List<FileDownloadCallable> callables = new ArrayList<>();
        List<String> fileSizes = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(urls.size());
        for(int i = 0; i < urls.size(); i++) {
            callables.add(new FileDownloadCallable(urls.get(i)));
        }

        List<Future<String>> futures = executorService.invokeAll(callables);
        for (Future<String> future : futures) {
            fileSizes.add(future.get());
        }

        return new DownloadFilesResponse(fileSizes);
    }
}
