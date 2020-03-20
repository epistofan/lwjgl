import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;

public class Buffers {


    static void setVAO(){

        int cube1Vao = glGenVertexArrays();
        glBindVertexArray(cube1Vao);

        int cube2Vao = glGenVertexArrays();
        glBindVertexArray(cube2Vao);



    }


    static void  setPositionsVBO(){
        int posVboId = glGenBuffers();
        FloatBuffer posBuffer = memAllocFloat(GameItem.positions.length);
        posBuffer.put(GameItem.positions).flip();
        glBindBuffer(GL_ARRAY_BUFFER, posVboId);
        glBufferData(GL_ARRAY_BUFFER, posBuffer, GL_STATIC_DRAW);



    }
    static void setColoursVBO(){

        FloatBuffer colorB = memAllocFloat(GameItem.colours.length);
        colorB.put(GameItem.colours).flip();
        int vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, colorB, GL_STATIC_DRAW);



    }
    static void setIndicesVBO(){

        int idxVboId = glGenBuffers();
        IntBuffer indicesBuffer = MemoryUtil.memAllocInt(GameItem.indices.length);
        indicesBuffer.put(GameItem.indices).flip();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, idxVboId);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);





    }
}
