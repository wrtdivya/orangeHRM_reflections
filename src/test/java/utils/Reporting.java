package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class Reporting {

    @Attachment(value = "Step: {0}", type = "text/plain")
    public static String attachText(String name, String message) {
        return message;
    }

    @Attachment(value = "Screenshot: {0}", type = "image/png")
    public static byte[] attachScreenshot(String name, byte[] screenshotBytes) {
        return screenshotBytes;
    }

    public static void attachScreenshotFile(String name, File screenshotFile) {
        try {
            attachScreenshot(name, Files.readAllBytes(screenshotFile.toPath()));
        } catch (IOException e) {
            System.out.println("Unable to attach screenshot file: " + e.getMessage());
        }
    }

    @Attachment(value = "Video: {0}", type = "video/mp4")
    public static byte[] attachVideo(String name, byte[] videoBytes) {
        return videoBytes;
    }

    public static void attachVideoFile(String name, File videoFile) {
        try {
            attachVideo(name, Files.readAllBytes(videoFile.toPath()));
        } catch (IOException e) {
            System.out.println("Unable to attach video file: " + e.getMessage());
        }
    }

    public static void addAllureLog(String message) {
        Allure.addAttachment("Log", "text/plain", message, ".txt");
    }
}
