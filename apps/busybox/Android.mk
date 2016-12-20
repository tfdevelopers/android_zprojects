LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := busybox
LOCAL_MODULE_TAGS := eng
LOCAL_MODULE_CLASS := EXECUTABLES
LOCAL_MODULE_PATH := $(TARGET_OUT)/xbin/
LOCAL_SRC_FILES := busybox

include $(BUILD_PREBUILT)

TOOLS := \
	grep killall tail xargs vi

SYMLINKS := $(addprefix $(TARGET_OUT)/bin/,$(TOOLS))

$(SYMLINKS): $(LOCAL_INSTALLED_MODULE) $(LOCAL_PATH)/Android.mk
	@echo "Symlink: $@ -> busybox"
	@mkdir -p $(dir $@)
	@rm -rf $@
	$(hide) ln -sf busybox $@

ALL_DEFAULT_INSTALLED_MODULES += $(SYMLINKS)
