# To enable ProGuard in your project, edit project.properties
# to define the proguard.config property as described in that file.
#
# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in ${sdk.dir}/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the ProGuard
# include property in project.properties.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:
-dontskipnonpubliclibraryclassmembers
# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
-optimizationpasses 5
-dontusemixedcaseclassnames
-keepattributes Signature,*Annotation*
-dontpreverify
-verbose
-dontwarn org.apache.commons.**,org.apache.http.**
-dontwarn javax.naming.**
-dontwarn nl.siegmann.epublib.**
-dontwarn org.htmlcleaner.**
-dontwarn org.slf4j.**
-dontwarn org.apache.log4j.**
-dontwarn us.monoid.web.**
-dontwarn junit.textui.**

-keep class * extends android.support.v7.app.ActionBarActivity
-keep class * extends android.support.v7.app.AppCompatActivity
-keep class * extends android.support.v4.app.FragmentActivity
-keep class * extends android.app.Activity
-keep class * extends android.app.Application
-keep class * extends android.app.Service
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.backup.BackupAgentHelper
-keep class * extends android.preference.Preference
-keep class * extends android.widget.BaseAdapter
-keep class * extends android.widget.ListView
-keep class * extends android.widget.GridView
-keep class * extends android.widget.View
-keep class it.angrydroids.epub3reader.** { *; }
-keep interface it.angrydroids.epub3reader.** { *; }
-keep class com.android.vending.licensing.ILicensingService
-keep class com.facebook.** { *; }
-keep class org.apache.commons.logging.**
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class com.android.volley.** { *; }
-keep interface com.android.volley.** { *; }
-keep class org.bouncycastle.** { *;}
-keep interface org.bouncycastle.** { *;}
-keep class junit.** { *; }
-keep interface junit.** { *; }
-keep class nl.siegmann.epublib.** {*;}
-keep class org.htmlcleaner.** {*;}
-keep class org.slf4j.** {*;}

-keepattributes InnerClasses

-keep class **.R
-keep class **.R$* {
    <fields>;
}

-keep class android.support.v7.internal.** { *; }
-keep interface android.support.v7.internal.** { *; }
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }
-keepattributes EnclosingMethod

-keep class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context,android.util.AttributeSet);
    public <init>(android.content.Context,android.util.AttributeSet,int);
    public void set*(...);
}

-keepclasseswithmembers class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context,android.util.AttributeSet,int);
}

-keep class * extends android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Gson specific classes
#-keep class sun.misc.Unsafe {
#    <fields>;
#    <methods>;
#}
-keep class sun.misc.Unsafe { *; }


-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson

-keep class com.vega.cliptv.model.** {
#    public protected <fields>;
#    public protected <methods>;
    public private <fields>;
    public private <methods>;
}
-keep class vn.com.vega.cliptvsdk.model.** {
#    public protected <fields>;
#    public protected <methods>;
    public private <fields>;
    public private <methods>;
}
-keep class com.vn.vega.base.model.** {
#    public protected <fields>;
#    public protected <methods>;
    public private <fields>;
    public private <methods>;
}
-keep class com.vega.webvttparser.SubVtt {
#    public protected <fields>;
#    public protected <methods>;
    public private <fields>;
    public private <methods>;
}
-keep class com.vn.vega.base.model.** {
#    public protected <fields>;
#    public protected <methods>;
    public private <fields>;
    public private <methods>;
}
-keep class com.vega.cliptv.cards.** {
#    public protected <fields>;
#    public protected <methods>;
    public private <fields>;
    public private <methods>;
}
# Keep - Library. Keep all public and protected classes, fields, and methods.
#-keep public class * {
#    public protected <fields>;
#    public protected <methods>;
#}

# Also keep - Enumerations. Keep the special static methods that are required in
# enumeration classes.
-keepclassmembers enum  * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keepattributes *Annotation*

-dontwarn org.apache.**
-keep class * extends com.koushikdutta.async.TapCallback {
  public protected private *;
}
-keep class com.bugsense.** { *; }
-keep class org.blinkenlights.** { *;}

#---------- Crashlytics --------------------
-keepattributes SourceFile,LineNumberTable
#-------------------------------------------

#---------- Google analytic --------------------
-keep public class com.google.** {*;}
#-------------------------------------------

#---------- Ok Http ------------------
-dontwarn rx.**
-dontwarn okio.**

-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn retrofit.**
-dontwarn retrofit.appengine.UrlFetchClient
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
#---------- End Ok Http ------------------

-keep class * extends android.support.v7.internal.view.menu.MenuBuilder
-keep class * implements android.support.v7.internal.view.menu.MenuBuilder
-keep class android.support.v7.internal.view.menu.MenuBuilder

# Parse
-keep class org.apache.http.** { *; }
-keepnames class com.parse.** { *; }
-dontwarn android.net.SSLCertificateSocketFactory
-dontwarn android.app.Notification
-dontwarn com.squareup.**
-dontwarn com.parse.**
## New rules for EventBus 3.0.x ##
# http://greenrobot.org/eventbus/documentation/proguard/

-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
#retrolabdat
-dontwarn java.lang.invoke.*

# ButterKnife 7

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#
# Retrofit 2.X
## https://square.github.io/retrofit/ ##

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
#rxjava
# rxjava
-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}
-dontwarn com.appsflyer.AFKeystoreWrapper
-dontwarn com.appsflyer.**
-assumenosideeffects class android.util.Log {
    *;
}
-dontskipnonpubliclibraryclasses
-dontobfuscate
-forceprocessing
-optimizationpasses 5

-keep class * extends android.app.Activity
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** e(...);
}
#Fresco
# Keep our interfaces so they can be used by other ProGuard rules.
# See http://sourceforge.net/p/proguard/bugs/466/
-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

# Do not strip any method/class that is annotated with @DoNotStrip
-keep @com.facebook.common.internal.DoNotStrip class *
-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

# Keep native methods
-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**
-dontwarn com.squareup.okhttp.**
-dontwarn okhttp3.**
-dontwarn javax.annotation.**
-dontwarn com.android.volley.toolbox.**
-dontwarn com.facebook.infer.**

# Ensures entities remain un-obfuscated so table and columns are named correctly
-keep class com.coin.market.model.** { *; }

# ActiveAndroid
-keep class com.activeandroid.** { *; }
-keep class com.activeandroid.**.** { *; }
-keep class * extends com.activeandroid.Model
-keep class * extends com.activeandroid.serializer.TypeSerializer

-keep class com.google.**
-dontwarn com.google.**

-keep class com.sun.mail.**
-dontwarn com.sun.mail.**
-keep class com.startapp.** {
      *;
}

-keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile, LineNumberTable, *Annotation*, EnclosingMethod
-dontwarn android.webkit.JavascriptInterface
-dontwarn com.startapp.**