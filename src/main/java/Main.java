

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static java.lang.Boolean.FALSE;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;
import static org.lwjgl.system.MemoryUtil.memFree;

public class Main {


public static long window;
    public static long width, height;
    public static Vector3f offset;
    private static GLFWKeyCallback keyCallback = new GLFWKeyCallback() {

        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
                glfwSetWindowShouldClose(window, true);
            }
        }
    };
    private static GLFWKeyCallback wKeyCallback = new GLFWKeyCallback() {

        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_W && action == GLFW_PRESS) {
                System.out.println("W pressed");
    offset.z= 1.0f;

            }
        }
    };

    private static GLFWWindowCloseCallback glfwWindowCloseCallback = new GLFWWindowCloseCallback (){


        @Override
        public void invoke(long window) {



        }
    };

    public static void main(String [] args) {

        float[] position = new float[] {
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f

        };





        int[] indices = new int[]{
                // Front face

        };

        float[] colours = new float[]{
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,

        };


        String vertexSource = ShaderLoader.getVertexShader();

        String fragmentSource =ShaderLoader.getFragmentShader();



        glfwInit();

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        window = glfwCreateWindow(1000, 800, "My Title", 0,0);
        glfwMakeContextCurrent(window);
        createCapabilities();
        glfwSetWindowPos(window, 100, 100);
        glfwSwapInterval(1);
        glfwSwapBuffers(window);

        glfwSetKeyCallback(window, keyCallback);
        glfwSetKeyCallback(window, wKeyCallback);
        glfwSetWindowCloseCallback(window, glfwWindowCloseCallback);

       //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        //glEnable(GL_DEPTH_TEST);
//window size
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, w, h);
        width = w.get(0);
        height = h.get(0);

// proj matrix
        final float FOV = (float) Math.toRadians(60.0f);
        final float Z_NEAR = 0.01f;
        final float Z_FAR = 1000.0f;
        Matrix4f projectionMatrix;
        float aspectRatio = (float) width / height;
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
//world matrix
        offset = new Vector3f(0.0f,0.0f,-3.0f);
        Vector3f rotation = new Vector3f(0.0f,0.0f,0.0f);
        float scale = 1.0f;
        Matrix4f worldMatrix = new Matrix4f().identity().translate(offset).
                rotateX((float)Math.toRadians(rotation.x)).
                rotateY((float)Math.toRadians(rotation.y)).
                rotateZ((float)Math.toRadians(rotation.z)).
                scale(scale);

        glClearColor(1.0f, 0.8f, 0.5f, 0.0f);




//vao

        int vao = glGenVertexArrays();
        glBindVertexArray(vao);
//vbo
        FloatBuffer verticesBuffer = memAllocFloat(position.length);
             verticesBuffer.put(position).flip();
             int vbo = glGenBuffers();
             glBindBuffer(GL_ARRAY_BUFFER, vbo);
             glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

  //vbo
        int idxVboId = glGenBuffers();
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
        indicesBuffer.put(indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
       // memFree(indicesBuffer);



//vbo
        int colourVboId = glGenBuffers();
        FloatBuffer colourBuffer = memAllocFloat(colours.length);
        colourBuffer.put(colours).flip();
        glBindBuffer(GL_ARRAY_BUFFER, colourVboId);
        glBufferData(GL_ARRAY_BUFFER, colourBuffer, GL_STATIC_DRAW);
        //memFree(colourBuffer);



        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, vertexSource);
        glCompileShader(vertexShader);

        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
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

        int shaderProgram = glCreateProgram();


        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        //glBindFragDataLocation(shaderProgram, 1, "fragColor");
        glLinkProgram(shaderProgram);

        int shaderProgramStatus = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (shaderProgramStatus != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(shaderProgram));
        }
        glUseProgram(shaderProgram);

        int location = glGetUniformLocation(shaderProgram, "projectionMatrix");
        int location2 = glGetUniformLocation(shaderProgram, "worldMatrix");
//uniform
        MemoryStack stack = MemoryStack.stackPush();
        FloatBuffer fb = stack.mallocFloat(16);
        projectionMatrix.get(fb);


        MemoryStack stack1 = MemoryStack.stackPush();
        FloatBuffer fb1 = stack1.mallocFloat(16);
        worldMatrix.get(fb1);

        glUniformMatrix4fv(location, FALSE, fb);
        glUniformMatrix4fv(location2, FALSE, fb1);


        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);


        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);




        while(!glfwWindowShouldClose(window)){


           // width = w.get(0);
           // height = h.get(0);




            glClear(GL_COLOR_BUFFER_BIT);

            glDrawArrays(GL_TRIANGLES, 0, position.length);



            glfwSwapBuffers(window);
           glfwPollEvents();



        }

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(0);
        glDeleteProgram(shaderProgram);
        glBindVertexArray(0);
        memFree(verticesBuffer);
        memFree(colourBuffer);
        memFree(indicesBuffer);
        glBindVertexArray(0);
        glfwDestroyWindow(window);
       glfwTerminate();

    }



}
