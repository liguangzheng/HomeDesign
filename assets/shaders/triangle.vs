uniform mat4 matViewProjection;
attribute vec4 a_position;
attribute vec4 a_color;

varying vec4 v_fragmentColor;

void main()
{
    gl_Position = matViewProjection * a_position;
    v_fragmentColor = a_color;
}