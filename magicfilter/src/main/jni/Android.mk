LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := beauty
LOCAL_LDLIBS := -llog -lm -ljnigraphics

LOCAL_SRC_FILES := $(wildcard $(LOCAL_PATH)/*.cpp)
LOCAL_SRC_FILES += $(wildcard $(LOCAL_PATH)/*/*.cpp)

include $(BUILD_SHARED_LIBRARY)