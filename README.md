Version 9.10.1

## ENG ( RUS version below )


## How to use:

1. Add links to our maven-repository:
    
       maven {url "https://maven.caramelads.com/repository/maven-releases"}
       maven {url "https://dl.adtiming.com/android-sdk"}

2. Add dependency in build.gradle:

          implementation ('com.caramelads:sdk:9.10.1')
          
3. If using proguard, add this:
         
	
         -ignorewarnings
         -dontshrink
         -dontoptimize
         -dontpreverify
         
         
         -keepclassmembers,allowobfuscation class * {
           @com.google.gson.annotations.SerializedName <fields>;
         }
         
         #retrofit
         #https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
         # Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
         # EnclosingMethod is required to use InnerClasses.
         -keepattributes Signature, InnerClasses, EnclosingMethod
         
         # Retrofit does reflection on method and parameter annotations.
         -keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
         
         # Retain service method parameters when optimizing.
         -keepclassmembers,allowshrinking,allowobfuscation interface * {
             @retrofit2.http.* <methods>;
         }
         
         # Ignore annotation used for build tooling.
         -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
         
         # Ignore JSR 305 annotations for embedding nullability information.
         -dontwarn javax.annotation.**
         
         # Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
         -dontwarn kotlin.Unit
         
         # Top-level functions that can only be used by Kotlin.
         -dontwarn retrofit2.KotlinExtensions
         -dontwarn retrofit2.KotlinExtensions$*
         
         # With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
         # and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
         -if interface * { @retrofit2.http.* <methods>; }
         -keep,allowobfuscation interface <1>
         
         
         #eventbus
         -keepattributes *Annotation*
         -keepclassmembers class * {
             @org.greenrobot.eventbus.Subscribe <methods>;
         }
         -keep enum org.greenrobot.eventbus.ThreadMode { *; }
         
         # Only required if you use AsyncExecutor
         -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
             <init>(java.lang.Throwable);
         }
         
         
         #adtiming
         
         -dontwarn com.aiming.mdt.**.*
         -dontwarn com.mopub.**.*
         
         -dontoptimize
         -dontskipnonpubliclibraryclasses
         -keepattributes *Annotation*
         
         
         
         #okhttp
         
         # JSR 305 annotations are for embedding nullability information.
         -dontwarn javax.annotation.**
         
         # A resource is loaded with a relative path so the package of this class must be preserved.
         -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
         
         # Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
         -dontwarn org.codehaus.mojo.animal_sniffer.*
         
         # OkHttp platform used only on JVM and when Conscrypt dependency is available.
         -dontwarn okhttp3.internal.platform.ConscryptPlatform
         
         
         
         
         
         # smaato
         
         -keepclasseswithmembers class **.R$* {
             public static <fields>;
         }
         
         
         -assumenosideeffects class android.util.Log {
             public static *** d(...);
             public static *** v(...);
             public static *** i(...);
         }
         
         # admob
         
         -keep public class com.google.android.gms.ads.** {
             public *;
         }
         
         -keep public class com.google.ads.** {
             public *;
         }
         
         # mobup
         
         # Keep public classes and methods.
         -keepclassmembers class com.mopub.** { public *; }
         -keep public class com.mopub.**
         -keep public class android.webkit.JavascriptInterface {}
         
         # Explicitly keep any custom event classes in any package.
         -keep class * extends com.mopub.mobileads.CustomEventBanner {}
         -keep class * extends com.mopub.mobileads.CustomEventInterstitial {}
         -keep class * extends com.mopub.nativeads.CustomEventNative {}
         -keep class * extends com.mopub.nativeads.CustomEventRewardedAd {}
         
         # Keep methods that are accessed via reflection
         -keepclassmembers class ** { @com.mopub.common.util.ReflectionTarget *; }
         
         #adtiming
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
           -keepattributes *Annotation ,*InnerClasses
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
         -keep public class com.google.android.gms.**
         -dontwarn com.google.android.gms.**
         -dontwarn com.squareup.picasso.**
         -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
              public *;
         }
         -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
              public *;
         }
         # skip the Picasso library classes
         -keep class com.squareup.picasso.** {*;}
         -dontwarn com.squareup.okhttp.**
         # skip Moat classes
         -keep class com.moat.** {*;}
         -dontwarn com.moat.**
         # skip IAB classes
         -keep class com.iab.** {*;}
         -dontwarn com.iab.**


    
4. Scenarios of ads showing:
	Ad loading:	
	In the class extends Application class using Application.AcitivityLifecycleCallbacks (showed in example) just copy it to your 		project and add it in manifest.xml, add your additional code, if necessary.
			
			<application
        			android:name=".caramel.CaramelApp"
      
	Ads showing:
	Call CaramelIntegration.showAds() to show ad
	Several elements in the application, that will show ad by click, excepting leaving from application with ‘back’ buttons. 
    	Common integration example – when move to a new activity or fragment. You can download test integration example, which 			implements above described algorithm.


# Thank you for use our product!


# RUS 




## Как использовать:

1.	Добавьте ссылку на наш maven-репозиторий:

       		maven {url "https://maven.caramelads.com/repository/maven-releases"}
       		maven {url "https://dl.adtiming.com/android-sdk"}

2.	Добавьте зависимость в build.gradle:

                implementation ('com.caramelads:sdk:9.10.1')

3.	Если вы используете proguard, то добавьте эти строчки:


          
         -ignorewarnings
         
         -dontshrink
         -dontoptimize
         -dontpreverify
         
         
         -keepclassmembers,allowobfuscation class * {
           @com.google.gson.annotations.SerializedName <fields>;
         }
         
         #retrofit
         #https://github.com/square/retrofit/blob/master/retrofit/src/main/resources/META-INF/proguard/retrofit2.pro
         # Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
         # EnclosingMethod is required to use InnerClasses.
         -keepattributes Signature, InnerClasses, EnclosingMethod
         
         # Retrofit does reflection on method and parameter annotations.
         -keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
         
         # Retain service method parameters when optimizing.
         -keepclassmembers,allowshrinking,allowobfuscation interface * {
             @retrofit2.http.* <methods>;
         }
         
         # Ignore annotation used for build tooling.
         -dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
         
         # Ignore JSR 305 annotations for embedding nullability information.
         -dontwarn javax.annotation.**
         
         # Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
         -dontwarn kotlin.Unit
         
         # Top-level functions that can only be used by Kotlin.
         -dontwarn retrofit2.KotlinExtensions
         -dontwarn retrofit2.KotlinExtensions$*
         
         # With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
         # and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
         -if interface * { @retrofit2.http.* <methods>; }
         -keep,allowobfuscation interface <1>
         
         
         #eventbus
         -keepattributes *Annotation*
         -keepclassmembers class * {
             @org.greenrobot.eventbus.Subscribe <methods>;
         }
         -keep enum org.greenrobot.eventbus.ThreadMode { *; }
         
         # Only required if you use AsyncExecutor
         -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
             <init>(java.lang.Throwable);
         }
         
         
         #adtiming
         
         -dontwarn com.aiming.mdt.**.*
         -dontwarn com.mopub.**.*
         
         -dontoptimize
         -dontskipnonpubliclibraryclasses
         -keepattributes *Annotation*
         
         
         
         #okhttp
         
         # JSR 305 annotations are for embedding nullability information.
         -dontwarn javax.annotation.**
         
         # A resource is loaded with a relative path so the package of this class must be preserved.
         -keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
         
         # Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
         -dontwarn org.codehaus.mojo.animal_sniffer.*
         
         # OkHttp platform used only on JVM and when Conscrypt dependency is available.
         -dontwarn okhttp3.internal.platform.ConscryptPlatform
         
         
         
         
         
         # smaato
         
         -keepclasseswithmembers class **.R$* {
             public static <fields>;
         }
         
         
         -assumenosideeffects class android.util.Log {
             public static *** d(...);
             public static *** v(...);
             public static *** i(...);
         }
         
         # admob
         
         -keep public class com.google.android.gms.ads.** {
             public *;
         }
         
         -keep public class com.google.ads.** {
             public *;
         }
         
         # mobup
         
         # Keep public classes and methods.
         -keepclassmembers class com.mopub.** { public *; }
         -keep public class com.mopub.**
         -keep public class android.webkit.JavascriptInterface {}
         
         # Explicitly keep any custom event classes in any package.
         -keep class * extends com.mopub.mobileads.CustomEventBanner {}
         -keep class * extends com.mopub.mobileads.CustomEventInterstitial {}
         -keep class * extends com.mopub.nativeads.CustomEventNative {}
         -keep class * extends com.mopub.nativeads.CustomEventRewardedAd {}
         
         # Keep methods that are accessed via reflection
         -keepclassmembers class ** { @com.mopub.common.util.ReflectionTarget *; }
         
         #adtiming
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
           -keepattributes *Annotation ,*InnerClasses
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
         -keep public class com.google.android.gms.**
         -dontwarn com.google.android.gms.**
         -dontwarn com.squareup.picasso.**
         -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{
              public *;
         }
         -keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{
              public *;
         }
         # skip the Picasso library classes
         -keep class com.squareup.picasso.** {*;}
         -dontwarn com.squareup.okhttp.**
         # skip Moat classes
         -keep class com.moat.** {*;}
         -dontwarn com.moat.**
         # skip IAB classes
         -keep class com.iab.** {*;}
         -dontwarn com.iab.**
         







4.	Сценарии показа рекламы:
	
	Загрузка рекламы:
	В классе, наследующем класс Application, используя Application.ActivityLifecycleCallbacks (показано в примере) просто скопируйте 	 этот класс и добавьте в manifest.xml, при необходимости можно добавлять свой код
	
		<application
        		android:name=".caramel.CaramelApp"
	    
	Отображение рекламы: 
	Метод CaramelIntegration.showAds() отображает рекламу
	
	Элементы в приложении, при клике на которые, осуществляется показ рекламы, за исключением – кнопок back при выходе из 	 		приложения. В играх типичный пример показа при переходе на новый уровень. В обычных приложениях типичный пример показа при 		переходе на новый экран(через активность или фрагмент) и возврате, также можно использовать действия пользователя, если такие 		функции есть в приложении(сохранение файла, окончание редактирования текста и т.п.). 
	
	Вы можете скачать пример тестовой интеграции, реализующей вышеописанный алгоритм.



# Спасибо, что используете наш продукт!

