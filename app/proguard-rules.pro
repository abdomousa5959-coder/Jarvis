# Open Jarvis ProGuard Rules
-keep class com.openjarvis.graphify.nodes.** { *; }
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep @androidx.room.Dao interface *
-keepclassmembers class * { @androidx.room.* <methods>; }
-keep class com.openjarvis.accessibility.JarvisAccessibilityService { *; }
-keep class * extends android.accessibilityservice.AccessibilityService
-keep class * extends android.view.accessibility.AccessibilityNodeInfo
-keep class com.openjarvis.graphify.** { *; }
-keep class com.openjarvis.** { *; }
-keepclassmembers class kotlinx.coroutines.** { *; }
-keepclassmembernames class kotlinx.** { volatile <fields>; }
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
-dontwarn okhttp3.**
-dontwarn okio.**
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
-keep class com.google.mlkit.** { *; }
-dontwarn com.google.mlkit.**
-keep class androidx.security.crypto.** { *; }
-keep class androidx.work.** { *; }
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** { *** Companion; }
-keepclasseswithmembers class kotlinx.serialization.json.** { kotlinx.serialization.KSerializer serializer(...); }
-allowaccessmodification
-repackageclasses
-mergeinterfacesaggressively
-optimizationpasses 5
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
    public static int i(...);
}
