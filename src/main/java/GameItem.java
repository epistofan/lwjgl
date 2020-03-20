public class GameItem {

    static float[] positions = new float[] {
            // VO
            -0.5f, 0.5f, 0.5f,
// V1
            -0.5f, -0.5f, 0.5f,
// V2
            0.5f, -0.5f, 0.5f,
// V3
            0.5f, 0.5f, 0.5f,
// V4
            -0.5f, 0.5f, -0.5f,
// V5
            0.5f, 0.5f, -0.5f,
// V6
            -0.5f, -0.5f, -0.5f,
// V7
            0.5f, -0.5f, -0.5f,

    };





    static int[] indices = new int[]{
            // Front face
            0, 1, 3, 3, 1, 2,
// Top Face
            4, 0, 3, 5, 4, 3,
// Right face
            3, 2, 7, 5, 3, 7,
// Left face
            6, 1, 0, 6, 0, 4,
// Bottom face
            2, 1, 6, 2, 6, 7,
// Back face
            7, 6, 4, 7, 4, 5,

    };

    static float[] colours = new float[]{
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,
            0.5f, 0.0f, 0.0f,
            0.0f, 0.5f, 0.0f,
            0.0f, 0.0f, 0.5f,
            0.0f, 0.5f, 0.5f,



    };
















}
