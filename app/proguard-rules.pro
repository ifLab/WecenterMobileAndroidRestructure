# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hcjcch/AndroidSdk/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class org.iflab.wecentermobileandroidrestructure.model.** { *; }

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

-dontwarn com.octo.**
-keep class com.octo.** { *; }

-dontwarn com.dd.**
-keep class com.dd.** { *; }

-dontwarn com.facebook.shimmer.**
-keep class com.facebook.shimmer.** { *; }

-dontwarn com.marshalchen.ultimaterecycelrview.**
-keep class com.marshalchen.ultimaterecycelrview.** { *; }

-dontwarn com.melnykov.fab.**
-keep class com.melnykov.fab.** { *; }

-dontwarn com.me.**
-keep class com.me.** { *; }

-dontwarn com.loopj.**
-keep class com.loopj.** { *; }

-dontwarn com.google.**
-keep class com.google.** { *; }

-dontwarn de.**
-keep class de.** { *; }

-dontwarn javax.**
-keep class javax.** { *; }

-dontwarn org.**
-keep class org.** { *; }

-dontwarn net.**
-keep class net.** { *; }

-dontwarn u.aly.**
-keep class u.aly.** { *; }

-dontwarn uk.**
-keep class uk.** { *; }

-dontwarn in.**
-keep class in.** { *; }

-dontwarn fr.**
-keep class fr.** { *; }

-dontwarn uk.**
-keep class uk.** { *; }

-keep class com.tencent.mm.sdk.** {
   *;
}

-keep class com.sina.** { *; }

-keepattributes SourceFile,LineNumberTable

-keep class android.** { *; }
-keep class com.google.gson.** { *; }
-keepattributes Signature

