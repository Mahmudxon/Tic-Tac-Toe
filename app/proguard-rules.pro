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

# Dagger ProGuard rules.
# https://github.com/square/dagger

-dontwarn dagger.internal.codegen.
-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }


# Kotlin
-dontwarn kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
-dontwarn kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
-dontwarn kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
-dontwarn kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor
-dontwarn kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl
-dontwarn kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder
-dontwarn kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil
-dontwarn kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor
-dontwarn kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor
-dontwarn kotlin.reflect.jvm.internal.impl.types.TypeConstructor





