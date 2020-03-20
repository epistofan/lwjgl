import org.joml.Matrix4f;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static java.lang.Boolean.FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;

public class Shaders {

    private static String vertexSource;
    private static String fragmentSource;
    private static int shaderProgram;
    private static int location;
    private static int location2;
    private static int fragmentShader;
    private static int vertexShader;


    static void loadShaders(){

        vertexSource = ShaderLoader.getVertexShader();
        fragmentSource =ShaderLoader.getFragmentShader();


    }

   static void createShader(){
        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexSource);
        glCompileShader(vertexShader);

        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, fragmentSource);
        glCompileShader(fragmentShader);

        int vertexShaderStatus = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if (vertexShaderStatus != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(vertexShader));
        }

        int fragmentShaderStatus = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if (fragmentShaderStatus != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(fragmentShader));
        }



    }

    static void createShaderProgram(){

        shaderProgram = glCreateProgram();



    }
    static void attachShader(){


        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);

    }

    static void useShaderProgram(){


        glUseProgram(shaderProgram);

        location = glGetUniformLocation(shaderProgram, "projectionMatrix");
        location2 = glGetUniformLocation(shaderProgram, "worldMatrix");


    }
    static void linkShaderProgram(){

        glLinkProgram(shaderProgram);
        int shaderProgramStatus = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (shaderProgramStatus != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(shaderProgram));
        }

    }
    static void setUniform(Matrix4f projectionMatrix){

        MemoryStack stack = MemoryStack.stackPush();
        FloatBuffer fb = stack.mallocFloat(16);
        projectionMatrix.get(fb);



        glUniformMatrix4fv(location, FALSE, fb);


    }

    static void setUniformWorldMatrix(Matrix4f worldMatrix){

        MemoryStack stack1 = MemoryStack.stackPush();
        FloatBuffer fb1 = stack1.mallocFloat(16);
        worldMatrix.get(fb1);

        glUniformMatrix4fv(location2, FALSE, fb1);



    }
}
