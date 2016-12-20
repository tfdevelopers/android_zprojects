LOCAL_PATH := $(my-dir)

#######################libfacelock_jni##################
include $(CLEAR_VARS)
LOCAL_MODULE := libfacelock_jni
LOCAL_SRC_FILES_64 := arm64/libfacelock_jni.so
LOCAL_MULTILIB := 64
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_SUFFIX := .so
LOCAL_MODULE_PATH := $(TARGET_OUT)/lib64/
include $(BUILD_PREBUILT)

#######################libjni_latinimegoogle##################
include $(CLEAR_VARS)
LOCAL_MODULE := libjni_latinimegoogle
LOCAL_SRC_FILES_64 := arm64/libjni_latinimegoogle.so
LOCAL_MULTILIB := 64
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_SUFFIX := .so
LOCAL_MODULE_PATH := $(TARGET_OUT)/lib64/
include $(BUILD_PREBUILT)

######################libfrsdk###################
include $(CLEAR_VARS)
LOCAL_MODULE := libfrsdk
LOCAL_SRC_FILES_64 := vendor/lib64/libfrsdk.so
LOCAL_MULTILIB := 64
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_SUFFIX := .so
LOCAL_MODULE_PATH := $(TARGET_OUT_VENDOR)/lib64/
include $(BUILD_PREBUILT)


LOCAL_SRC_FILES_32 := vendor/lib/libfrsdk.so
LOCAL_MULTILIB := 32
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_SUFFIX := .so
LOCAL_MODULE_PATH := $(TARGET_OUT_VENDOR)/lib/
include $(BUILD_PREBUILT)

######################libfilterpack_facedetect###################
include $(CLEAR_VARS)
LOCAL_MODULE := libfilterpack_facedetect
LOCAL_SRC_FILES_64 := arm64/libfilterpack_facedetect.so
LOCAL_MULTILIB := 64
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_SUFFIX := .so
LOCAL_MODULE_PATH := $(TARGET_OUT)/lib64/
include $(BUILD_PREBUILT)


LOCAL_SRC_FILES_32 := arm/libfilterpack_facedetect.so
LOCAL_MULTILIB := 32
LOCAL_MODULE_CLASS := SHARED_LIBRARIES
LOCAL_MODULE_SUFFIX := .so
LOCAL_MODULE_PATH := $(TARGET_OUT)/lib/
include $(BUILD_PREBUILT)
