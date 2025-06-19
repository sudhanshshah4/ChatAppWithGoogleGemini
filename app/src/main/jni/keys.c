#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_genai_chatapp_NativeBridge_getApiKey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "MY_KEY");
}