package utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_QUICKTIME;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.*;

public class ScreenRecorderUtil extends ScreenRecorder {
    
    private static ScreenRecorder recorder;
    private final String testName;

    private ScreenRecorderUtil(GraphicsConfiguration cfg, Rectangle area, File folder, String testName) throws Exception {
        super(cfg, area, 
            new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 900),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), 
            null, folder);
        this.testName = testName;
    }

    @Override
    protected File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()) movieFolder.mkdirs();
        String time = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(movieFolder, testName + "_" + time + ".mov");
    }

    public static void startRecord(String testName) throws Exception {
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        recorder = new ScreenRecorderUtil(gc, gc.getBounds(), new File("./test-recordings/"), testName);
        recorder.start();
    }

    public static void stopRecord() throws Exception {
        if (recorder != null) recorder.stop();
    }
}
