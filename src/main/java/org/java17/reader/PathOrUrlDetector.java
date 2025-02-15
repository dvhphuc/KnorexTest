package org.java17.reader;

import java.util.regex.Pattern;

public class PathOrUrlDetector {
    private static final String URL_REGEX = "^(https?|ftp)://[\\w.-]+(:\\d+)?(/.*)?$";
    private static final String WINDOWS_FILE_PATH_REGEX = "^[a-zA-Z]:\\\\.*";
    private static final String UNIX_FILE_PATH_REGEX = "^/.*";

    public String detectType(String dataSource) {
        if (dataSource == null || dataSource.isEmpty()) {
            return "Unknown";
        }

        if (Pattern.matches(URL_REGEX, dataSource)) {
            return "URL";
        } else if (Pattern.matches(WINDOWS_FILE_PATH_REGEX, dataSource) || Pattern.matches(UNIX_FILE_PATH_REGEX, dataSource)) {
            return "File Path";
        } else {
            return "Unknown";
        }
    }
}
