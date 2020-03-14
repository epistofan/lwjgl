import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ShaderLoader {

    private static String fileInfo;

    public static String getFragmentShader() {


        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("fragmentShader.fs").toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return content;

    }


    public static String getVertexShader() {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("vertexShader.vs").toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        return content;
    }
}



