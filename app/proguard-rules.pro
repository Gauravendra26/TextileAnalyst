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
# Keep Retrofit classes
-keep class retrofit2.** { *; }
-dontwarn retrofit2.**

# Keep Glide classes and prevent obfuscation
-keep class com.bumptech.glide.** { *; }
-dontwarn com.bumptech.glide.**

# Keep Lottie classes
-keep class com.airbnb.lottie.** { *; }

# Keep classes in OkHttp
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**

# Keep classes in Google Play services
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# Keep anonymous inner classes (like Account_Details$1)
-keep class com.example.nssoseedanalyst.Account_Details { *; }

# Keep classes for Dexter permissions library (if using reflection or dynamic loading)
-keep class com.karumi.dexter.** { *; }
-dontwarn com.karumi.dexter.**

# Keep classes for SSP and SDP libraries
-keep class com.intuit.ssp.** { *; }
-keep class com.intuit.sdp.** { *; }

# For other dynamic libraries, make sure to add keep rules as necessary.
