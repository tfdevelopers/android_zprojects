LOCAL_PATH := $(call my-dir)
###############################################################################
include $(CLEAR_VARS)
LOCAL_MODULE := google_build.xml
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := ETC
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/sysconfig
LOCAL_SRC_FILES := ./sysconfig/$(LOCAL_MODULE)
include $(BUILD_PREBUILT)

##############################################
include $(CLEAR_VARS)
LOCAL_MODULE := com.google.android.media.effects.xml
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := ETC
# This will install the file in /system/etc/permissions
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := ./permissions/$(LOCAL_MODULE)
include $(BUILD_PREBUILT)

##############################################
include $(CLEAR_VARS)
LOCAL_MODULE := com.google.android.dialer.support.xml
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := ETC
# This will install the file in /system/etc/permissions
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := ./permissions/$(LOCAL_MODULE)
include $(BUILD_PREBUILT)

##############################################
include $(CLEAR_VARS)
LOCAL_MODULE := com.google.android.maps.xml
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := ETC
# This will install the file in /system/etc/permissions
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := ./permissions/$(LOCAL_MODULE)
include $(BUILD_PREBUILT)

##############################################

