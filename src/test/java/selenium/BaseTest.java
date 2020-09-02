package selenium;

import io.qameta.allure.Allure;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

    public BaseTest() {

        Allure.addAttachment("My attachment", "My attachment content");

        Path content = Paths.get("D:\\work\\projects\\testoutput");
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("My attachment", is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
