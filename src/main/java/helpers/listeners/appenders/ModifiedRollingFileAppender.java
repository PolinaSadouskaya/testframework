package helpers.listeners.appenders;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;

public class ModifiedRollingFileAppender extends RollingFileAppender {

    @Override
    public void append(LoggingEvent event) {
        checkLogFileExist();
        super.append(event);
    }

    private void checkLogFileExist(){
        File logFile = new File(super.fileName);
        if (!logFile.exists()) {
            this.activateOptions();
        }
    }
}
