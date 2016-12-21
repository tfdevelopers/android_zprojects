###############################################################################
# GMS mandatory core packages
PRODUCT_PACKAGES += \
    AndroidForWork \
    ConfigUpdater \
    GoogleBackupTransport \
    GoogleFeedback \
    GoogleLoginService \
    GoogleOneTimeInitializer \
    GooglePartnerSetup \
    GoogleServicesFramework \
    GoogleCalendarSyncAdapter \
    GoogleContactsSyncAdapter \
    GoogleTTS \
    GmsCore \
    Phonesky 

PRODUCT_PACKAGES += \
    google.xml \
    google_build.xml \
    com.google.android.dialer.support.xml \
    com.google.android.maps.xml \
    com.google.android.media.effects.xml \
    com.google.widevine.software.drm.xml \
    google_generic_update.txt \
    com.google.android.maps.jar \
    com.google.android.media.effects.jar \
    com.google.android.dialer.support.jar \
    com.google.widevine.software.drm.jar

#ifeq ($(TARGET_BUILD_VARIANT), user)
#  PRODUCT_PACKAGES += SetupWizard
#endif

# Configuration files for GMS apps
PRODUCT_COPY_FILES += \
    zprojects/google/etc/preferred-apps/google.xml:system/etc/preferred-apps/google.xml \
    zprojects/google/etc/sysconfig/google.xml:system/etc/sysconfig/google.xml

# Overlay For GMS devices
#PRODUCT_PACKAGE_OVERLAYS := zprojects/google/products/gms_overlay

# Setting PRODUCT_PREBUILT_WEBVIEWCHROMIUM as yes will prevent from building
# webviewchromium from source, and use prebuilt WebViewGoogle.apk in GMS pack
PRODUCT_PREBUILT_WEBVIEWCHROMIUM := no
#
ifeq ($(PRODUCT_PREBUILT_WEBVIEWCHROMIUM),yes)
PRODUCT_PACKAGES += WebViewGoogle
# The following framework overlay must be included if prebuilt WebViewGoogle.apk is used
#PRODUCT_PACKAGE_OVERLAYS += zprojects/google/apps/WebViewGoogle/overlay
endif

# Chrome browser selection
# By default, Chrome will be the only preloaded system browser
# Use ChromeWithBrowser, if Chrome is preloaded along with another browser side-by-sde
#PRODUCT_PACKAGES += Chrome
#PRODUCT_PACKAGES += ChromeWithBrowser
#
# Uncomment the following line if customized homepage provider for Chrome should be installed
# For the details, see Android.mk in apps/Chrome directory
#PRODUCT_PACKAGES += ChromeCustomizations

# GMS mandatory application packages
#PRODUCT_PACKAGES += \
#    Gmail2 \
#    Hangouts \
#    Maps \
#    Photos \
#    Velvet \
#    YouTube 
#    Music2 \
#    PlusOne \
#    GMS_Videos 
# GMS optional application packages
PRODUCT_PACKAGES += \
    FaceLock \
    TagGoogle \
    talkback 
#    Drive \
 #   GoogleHome \
#    Newsstand \
 #   CalendarGoogle \
 #   CloudPrint \
 #   EditorsDocs \
 #   EditorsSheets \
 #   EditorsSlides \
#    GoogleMessenger \
#    PlayGames \
#    Books \
#    GoogleCamera \
#    Keep \
#    LatinImeGoogle 
# more GMS optional application packages
#PRODUCT_PACKAGES += \
#    NewsWeather 
#    GoogleHindiIME \
#    GooglePinyinIME \
#    JapaneseIME \
#    KoreanIME 
#    DMAgent \
 #   Wallet


PRODUCT_PACKAGES += \
     libfacelock_jni \
     libjni_latinimegoogle \
     libfrsdk \
     libfilterpack_facedetect
# Overlay for Google network and fused location providers
#$(call inherit-product, device/sample/products/location_overlay.mk)

# Overrides
PRODUCT_PROPERTY_OVERRIDES += \
    ro.setupwizard.mode=DISABLED \
    ro.com.google.gmsversion=6.0
