

import com.sun.prism.ps.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

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



    public static void main(String [] args) {
//window
        Window window = new Window();
        window.createWindow();
//shaders
        Shaders.loadShaders();
        Shaders.createShader();
        Shaders.createShaderProgram();
        Shaders.attachShader();
        Shaders.linkShaderProgram();
        Shaders.useShaderProgram();
//matrices
        Matrix4f projectionMatrix = new Matrix().getProjectionMatrix(Window.window);
        Matrix4f worldMatrix = new Matrix().getWorldMatrix(0.0f, 0.0f, -3.0f);



//vao
        Buffers.setVAO();


//vbo color
        Buffers.setColoursVBO();

  //vbo indices

        Buffers.setIndicesVBO();

//vbo positions

        Buffers.setPositionsVBO();


//uniform
        Shaders.setUniform(projectionMatrix);
        Shaders.setUniformWorldMatrix(worldMatrix);




        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);


        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

        glClearColor(1.0f, 0.8f, 0.5f, 0.0f);


        while(!glfwWindowShouldClose(Window.window)){


            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glDrawElements(GL_TRIANGLES, GameItem.indices.length, GL_UNSIGNED_INT, 0);




            glfwSwapBuffers(Window.window);
          glfwPollEvents();




        }

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glUseProgram(0);
        /*glDeleteProgram(shaderProgram);
        glBindVertexArray(0);
        memFree(colorB);
        memFree(posBuffer);
        memFree(indicesBuffer);*/
        glBindVertexArray(0);
        glfwDestroyWindow(Window.window);
       glfwTerminate();

    }



}
