package org.hieka;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@SpringBootApplication
public class ApplicationMain {

    @Parameter(names = "-v",description = "version")
    private String version;

    @Parameter(names = "-help",description = "This is help")
    private String help;


    public static void main(String[] args) {
        ApplicationMain main =new ApplicationMain();
        JCommander commander = JCommander.newBuilder().addObject(main).build();
        commander.parse(args);
        commander.setProgramName("java -jar xxx.jar");
        commander.usage();

        SpringApplication.run(ApplicationMain.class,args);
        log.debug("runing ..."+main.version);





    }
}
