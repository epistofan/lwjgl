import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;

public class Matrix {


    public static Vector3f offset;


    Matrix4f getProjectionMatrix(long window){



        final float FOV = (float) Math.toRadians(60.0f);
        final float Z_NEAR = 0.01f;
        final float Z_FAR = 1000.0f;
        Matrix4f projectionMatrix;

        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        glfwGetWindowSize(window, w, h);
        int width = w.get(0);
        int height = h.get(0);



        float aspectRatio = (float) width / height;
        projectionMatrix = new Matrix4f().perspective(FOV, aspectRatio, Z_NEAR, Z_FAR);


        return projectionMatrix;
    }

    Matrix4f getWorldMatrix(float x, float y, float z){


        offset = new Vector3f(x,y,z);
        Vector3f rotation = new Vector3f(0.0f,30.0f,0.0f);
        float scale = 1.0f;
        Matrix4f worldMatrix = new Matrix4f().identity().translate(offset).
                rotateX((float)Math.toRadians(rotation.x)).
                rotateY((float)Math.toRadians(rotation.y)).
                rotateZ((float)Math.toRadians(rotation.z)).
                scale(scale);


        return worldMatrix;
    }


}
