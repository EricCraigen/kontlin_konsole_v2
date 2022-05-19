package jni.konsole;

public class IKonsoleNative {
    protected native static int jGetProcessId();

    protected native static int jGetProcessPriority();

    protected native static void jSetProcessPriority(int priority, String password);
}
