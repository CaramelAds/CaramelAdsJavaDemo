# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# ./gradlew :partner:assembleRelease
# ./gradlew :partner:assembleRelease

-ignorewarnings

-dontshrink
-dontoptimize
-dontpreverify

-keepattributes *Annotation*

-keep class com.google.** {*;}
-keep class com.smaato.** {*;}
-keep class com.mopub.** {*;}
-keep class com.yandex.mobile.ads.** { *; }
-keep class com.yandex.metrica.** { *; }

-dontwarn com.google.**
-dontwarn com.smaato.**
-dontwarn com.mopub.**
-dontwarn com.yandex.mobile.ads.**
-dontwarn com.yandex.metrica.**


-keepattributes *InnerClasses*

-keep class com.caramelads.external.** { *; }
-keep interface com.caramelads.external.** { *; }

-keep class com.caramelads.internal.ControllerImpl**
-keep class com.caramelads.internal.FactoryImpl**

-keep class com.caramelads.sdk.** { *; }
-keep interface com.caramelads.sdk.** { *; }

-keep class com.caramelads.model.** { *; }
-keep interface com.caramelads.model.** { *; }

-keep class com.caramelads.networking.** { *; }
-keep interface com.caramelads.networking.** { *; }

-keep class com.caramelads.external.** { *; }
-keep interface com.caramelads.external.** { *; }

-keep class com.caramelads.logs.** { *; }
-keep interface com.caramelads.logs.** { *; }


# okio

-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn retrofit2.Platform$Java8

# Retrofit
-keep class com.google.gson.** { *; }
-keep public class com.google.gson.** {public private protected *;}
-keep class com.google.inject.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class javax.inject.** { *; }
-keep class javax.xml.stream.** { *; }
-keep class retrofit.** { *; }
-keep class com.google.appengine.** { *; }
-keepattributes *Annotation*
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**

-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# Add any classes the interact with gson
# the following line is for illustration purposes
-keep class com.example.asheq.zanis_postmans.ListAddressesActivity
-keep class com.example.asheq.zanis_postmans.ListOrderActivity
-keep class com.example.asheq.zanis_postmans.LoginActivity
-keep class com.example.asheq.zanis_postmans.SendReportsActivity
-keep class com.example.asheq.track.TrackLocationService
-keep class com.example.asheq.track.TrackLocationApplication
-keep class com.example.asheq.models.** { *; }

# Hide warnings about references to newer platforms in the library
-dontwarn android.support.v7.**
# don't process support library
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.MapActivity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
# To support Enum type of class members
-keepclassmembers enum * { *; }

-keep class com.activeandroid.** { *; }
-keep class com.activeandroid.**.** { *; }
-keep class * extends com.activeandroid.Model
-keep class * extends com.activeandroid.serializer.TypeSerializer

-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

 -dontwarn com.aiming.mdt.**.*
 -dontwarn com.mopub.**.*
 -dontoptimize
 -dontskipnonpubliclibraryclasses
 #adt
 -keep class com.aiming.mdt.**{ *; }
 -keep class com.mopub.**{ *; }
 #R
 -keepclassmembers class **.R$* {
     public static <fields>;
 }
 -keepattributes *Annotation*,InnerClasses
 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
}

#unity ads
-keepattributes SourceFile,LineNumberTable
-keepattributes JavascriptInterface
-keep class android.webkit.JavascriptInterface {*;}
-keep class com.unity3d.**{*;}
-keep class com.unity3d.services.** {*;}
-dontwarn com.google.ar.core.**


#inmobi
-keepattributes SourceFile,LineNumberTable
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**
-keep public class com.google.android.gms.**
-dontwarn com.google.android.gms.**
-dontwarn com.squareup.picasso.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}
#skip the Picasso library classes
-keep class com.squareup.picasso.** {*;}
-dontwarn com.squareup.picasso.**
-dontwarn com.squareup.okhttp.**
#skip Moat classes
-keep class com.moat.** {*;}
-dontwarn com.moat.**
#skip AVID classes
-keep class com.integralads.avid.library.** {*;}