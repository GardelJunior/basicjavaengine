package engine.core;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.lwjgl.glfw.GLFW;

public class Input {
	private static HashMap<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	private static HashMap<Integer, ByteBuffer> joyBtns = new HashMap<Integer, ByteBuffer>();
	private static HashMap<Integer, FloatBuffer> sticks = new HashMap<Integer, FloatBuffer>();
	public static final int 
	KEY_SPACE         = 32,
    KEY_APOSTROPHE    = 39,
    KEY_COMMA         = 44,
    KEY_MINUS         = 45,
    KEY_PERIOD        = 46,
    KEY_SLASH         = 47,
    KEY_0             = 48,
    KEY_1             = 49,
    KEY_2             = 50,
    KEY_3             = 51,
    KEY_4             = 52,
    KEY_5             = 53,
    KEY_6             = 54,
    KEY_7             = 55,
    KEY_8             = 56,
    KEY_9             = 57,
    KEY_SEMICOLON     = 59,
    KEY_EQUAL         = 61,
    KEY_A             = 65,
    KEY_B             = 66,
    KEY_C             = 67,
    KEY_D             = 68,
    KEY_E             = 69,
    KEY_F             = 70,
    KEY_G             = 71,
    KEY_H             = 72,
    KEY_I             = 73,
    KEY_J             = 74,
    KEY_K             = 75,
    KEY_L             = 76,
    KEY_M             = 77,
    KEY_N             = 78,
    KEY_O             = 79,
    KEY_P             = 80,
    KEY_Q             = 81,
    KEY_R             = 82,
    KEY_S             = 83,
    KEY_T             = 84,
    KEY_U             = 85,
    KEY_V             = 86,
    KEY_W             = 87,
    KEY_X             = 88,
    KEY_Y             = 89,
    KEY_Z             = 90,
    KEY_LEFT_BRACKET  = 91,
    KEY_BACKSLASH     = 92,
    KEY_RIGHT_BRACKET = 93,
    KEY_GRAVE_ACCENT  = 96,
    KEY_WORLD_1       = 161,
    KEY_WORLD_2       = 162,
    /** Function keys. */
	KEY_ESCAPE        = 256,
	KEY_ENTER         = 257,
	KEY_TAB           = 258,
	KEY_BACKSPACE     = 259,
	KEY_INSERT        = 260,
	KEY_DELETE        = 261,
	KEY_RIGHT         = 262,
	KEY_LEFT          = 263,
	KEY_DOWN          = 264,
	KEY_UP            = 265,
	KEY_PAGE_UP       = 266,
	KEY_PAGE_DOWN     = 267,
	KEY_HOME          = 268,
	KEY_END           = 269,
	KEY_CAPS_LOCK     = 280,
	KEY_SCROLL_LOCK   = 281,
	KEY_NUM_LOCK      = 282,
	KEY_PRINT_SCREEN  = 283,
	KEY_PAUSE         = 284,
	KEY_F1            = 290,
	KEY_F2            = 291,
	KEY_F3            = 292,
	KEY_F4            = 293,
	KEY_F5            = 294,
	KEY_F6            = 295,
	KEY_F7            = 296,
	KEY_F8            = 297,
	KEY_F9            = 298,
	KEY_F10           = 299,
	KEY_F11           = 300,
	KEY_F12           = 301,
	KEY_F13           = 302,
	KEY_F14           = 303,
	KEY_F15           = 304,
	KEY_F16           = 305,
	KEY_F17           = 306,
	KEY_F18           = 307,
	KEY_F19           = 308,
	KEY_F20           = 309,
	KEY_F21           = 310,
	KEY_F22           = 311,
	KEY_F23           = 312,
	KEY_F24           = 313,
	KEY_F25           = 314,
	KEY_KP_0          = 320,
	KEY_KP_1          = 321,
	KEY_KP_2          = 322,
	KEY_KP_3          = 323,
	KEY_KP_4          = 324,
	KEY_KP_5          = 325,
	KEY_KP_6          = 326,
	KEY_KP_7          = 327,
	KEY_KP_8          = 328,
	KEY_KP_9          = 329,
	KEY_KP_DECIMAL    = 330,
	KEY_KP_DIVIDE     = 331,
	KEY_KP_MULTIPLY   = 332,
	KEY_KP_SUBTRACT   = 333,
	KEY_KP_ADD        = 334,
	KEY_KP_ENTER      = 335,
	KEY_KP_EQUAL      = 336,
	KEY_LEFT_SHIFT    = 340,
	KEY_LEFT_CONTROL  = 341,
	KEY_LEFT_ALT      = 342,
	KEY_LEFT_SUPER    = 343,
	KEY_RIGHT_SHIFT   = 344,
	KEY_RIGHT_CONTROL = 345,
	KEY_RIGHT_ALT     = 346,
	KEY_RIGHT_SUPER   = 347,
	KEY_MENU          = 348,
	/* Joystick Buttons */
	JOY_CROSS = 0,
	JOY_CIRCLE = 1,
	JOY_SQUARE = 2,
	JOY_TRIANGLE = 3,
	JOY_L1 = 4,
	JOY_R1 = 5,
	JOY_SELECT = 6,
	JOY_START = 7,
	JOY_LB = 8,
	JOY_RB = 9,
	JOY_UP = 10,
	JOY_RIGHT = 11,
	JOY_DOWN = 12,
	JOY_LEFT = 13,
	/* AXIS */
	JOY_AXIS_L_X = 0,
	JOY_AXIS_L_Y = 1,
	JOY_AXIS_R_X = 2,
	JOY_AXIS_R_Y = 3,
	JOY_AXIS_L2 = 4,
	JOY_AXIS_R2 = 5;
	
	public static void update() {
		for(Integer key : keys.keySet()) {
			keys.replace(key, Window.getKey(key));
		}
		for(Integer key : sticks.keySet()) {
			if(GLFW.glfwJoystickPresent(key)) {
				sticks.replace(key, GLFW.glfwGetJoystickAxes(key));
				joyBtns.put(key, GLFW.glfwGetJoystickButtons(key));
			}
		}
	}
	
	public static boolean getKeyDown(int key) {
		return Window.getKey(key) && !keys.get(key);
	}
	
	public static boolean getJoystickDown(int joyNumber, int key) {
		if(joyBtns.containsKey(joyNumber)) {
			 return (GLFW.glfwGetJoystickButtons(joyNumber).get(key)==1 && joyBtns.get(joyNumber).get(key)!=1);
		}else {
			return false;
		}
	}
	
	public static boolean getJoystickPressed(int joyNumber, int key) {
		if(joyBtns.containsKey(joyNumber)) {
			return joyBtns.get(joyNumber).get(key) == 1;
		}else {
			return false;
		}
	}
	
	public static boolean getJoystickUp(int joyNumber, int key) {
		if(joyBtns.containsKey(joyNumber)) {
			 return (GLFW.glfwGetJoystickButtons(joyNumber).get(key)!=1 && joyBtns.get(joyNumber).get(key)==1);
		}else {
			return false;
		}
	}
	
	public static float getJoystickAxis(int joyNumber, int axis) {
		if(sticks.containsKey(joyNumber)) {
			return (float) Math.round(sticks.get(joyNumber).get(axis)*100)/100.0f;
		}else {
			return 0;
		}
	}
	
	public static boolean getKeyPressed(int key) {
		return keys.get(key);
	}
	
	public static boolean getKeyUp(int key) {
		return !Window.getKey(key) && keys.get(key);
	}
	
	public static void enableKey(int... keyboard) {
		for(int key : keyboard) {
			keys.put(key, false);
		}
	}
	
	public static void enableJoyStick(int joyNumber) {
		if(GLFW.glfwJoystickPresent(joyNumber)) {
			sticks.put(joyNumber, GLFW.glfwGetJoystickAxes(joyNumber));
			joyBtns.put(joyNumber, GLFW.glfwGetJoystickButtons(joyNumber));
			System.out.println(GLFW.glfwGetJoystickAxes(joyNumber).capacity());
		}else {
			System.err.println("Joystick ["+joyNumber+"] not present");
		}
	}
}
