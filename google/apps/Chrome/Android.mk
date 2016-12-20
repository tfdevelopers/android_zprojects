###############################################################################
# Chrome
LOCAL_PATH := $(call my-dir)

my_src_abi := armeabi-v7a
include $(CLEAR_VARS)
LOCAL_MODULE := Chrome
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_TAGS := optional
LOCAL_BUILT_MODULE_STEM := package.apk
LOCAL_MODULE_SUFFIX := $(COMMON_ANDROID_PACKAGE_SUFFIX)
LOCAL_CERTIFICATE := PRESIGNED
#LOCAL_OVERRIDES_PACKAGES := Browser
LOCAL_SRC_FILES := $(LOCAL_MODULE).apk

LOCAL_PREBUILT_JNI_LIBS := \
  @lib/$(my_src_abi)/libchromeview.so \
  @lib/$(my_src_abi)/libchromium_android_linker.so \
@lib/$(my_src_abi)/libchrome.2171.59.so \
@lib/$(my_src_abi)/libchrome.2171.37.so \
@lib/$(my_src_abi)/libchrome.2125.114.so \
@lib/$(my_src_abi)/libchrome.2125.102.so \
@lib/$(my_src_abi)/libchrome.2062.117.so \
@lib/$(my_src_abi)/libchrome.1985.135.so \
@lib/$(my_src_abi)/libchrome.1985.131.so \
@lib/$(my_src_abi)/libchrome.1985.128.so \
@lib/$(my_src_abi)/libchrome.1985.122.so \
@lib/$(my_src_abi)/libchrome.1916.141.so \
@lib/$(my_src_abi)/libchrome.1916.138.so \
@lib/$(my_src_abi)/libchrome.1916.122.so \
@lib/$(my_src_abi)/libchrome.1847.114.so \
@lib/$(my_src_abi)/crazy.libchrome.so \
@lib/$(my_src_abi)/crazy.libchrome.align 

include $(BUILD_PREBUILT)

