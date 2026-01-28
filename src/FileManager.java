import java.io.File;
import java.util.*;

public class FileManager {

    private static final String SHARED_DIR = "shared";

    public static List<String> listFiles() {
        File dir = new File(SHARED_DIR);
        if (!dir.exists()) dir.mkdir();

        String[] files = dir.list();
        return files == null ? new ArrayList<>() : Arrays.asList(files);
    }

    public static File getFile(String filename) {
        return new File(SHARED_DIR + "/" + filename);
    }
}
