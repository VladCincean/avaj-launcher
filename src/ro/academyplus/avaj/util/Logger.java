package ro.academyplus.avaj.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by vlad on 31/05/2017.
 */
public class Logger {
    private static Logger logger = new Logger();
    private static BufferedWriter bufferedWriter = null;

    private Logger() {
        logger = this;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void open() throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter("simulation.txt"));
    }

    public void log(String str) {
        try {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
