import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;

public class Window {


    public static long window;
    public static long width, height;
    public static Matrix4f worldMatrix = new Matrix().getWorldMatrix(0.0f, 0.0f, -3.0f);
    private static float z = 0.0f;
    private static float x = 0.0f;
    private static float y = 0.0f;
    private static GLFWWindowCloseCallback glfwWindowCloseCallback = new GLFWWindowCloseCallback (){

        @Override
        public void invoke(long window) {

        }
    };

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

               z = z+0.1f;
                worldMatrix.translate(0.0f, 0.0f, z);
                Shaders.setUniformWorldMatrix(worldMatrix);


            }else if(key == GLFW_KEY_S && action == GLFW_PRESS) {
                System.out.println("S pressed");
                z = z-0.1f;
                worldMatrix.translate(0.0f, 0.0f, z);
                Shaders.setUniformWorldMatrix(worldMatrix);


        }else if(key == GLFW_KEY_A && action == GLFW_PRESS) {
            System.out.println("A pressed");
            x = x-0.1f;
            worldMatrix.translate(x, 0.0f, z);
            Shaders.setUniformWorldMatrix(worldMatrix);

        }else if(key == GLFW_KEY_D && action == GLFW_PRESS) {
            System.out.println("D pressed");
            x = x+0.1f;
            worldMatrix.translate(x, y, z);
            Shaders.setUniformWorldMatrix(worldMatrix);

        }
        }
    };



        public void createWindow(){

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
            glEnable(GL_DEPTH_TEST);
            glEnable(GL_CCW);
            glCullFace(GL_BACK);
//window size
            IntBuffer w = BufferUtils.createIntBuffer(1);
            IntBuffer h = BufferUtils.createIntBuffer(1);
            glfwGetWindowSize(window, w, h);
            width = w.get(0);
            height = h.get(0);



        }




}
