# 个人用的在线依赖库  供学习使用


引用此库需要做的配置

    1 .Androidx适配：配置 as 支持 AndroidX . （as 3.5版本在创建应用的时候勾选支持即可）
       不是 3.5 版本的在项目级的 gradle.properties 配置：
                                        android.useAndroidX=true
                                        android.enableJetifier=true
                                        
    2 .jitpack配置（依赖包发布的平台）： 在 app 级的 build.gradle 的 allprojects 配置:
                                                  maven { url "https://jitpack.io" }
                                                  
    3 .java8支持配置： 在项目级的 build.gradle  android{
    
                                        //配置对java8的支持
                                            compileOptions {
                                                    sourceCompatibility JavaVersion.VERSION_1_8
                                                    targetCompatibility JavaVersion.VERSION_1_8
                                            }
                                 }  
                                 
     4 .屏幕适配配置： 在 manifest.xml 的 <application></application> 标签内配置数值根据设计图调整）     
                                                                     <meta-data
                                                                            android:name="design_width_in_dp"
                                                                            android:value="360" />
                                                                        <meta-data
                                                                            android:name="design_height_in_dp"
                                                                            android:value="640" />
      
     5 .butterknife 注解配置： 在项目级的 build.gradle 的 dependencies{
     
            //注解配置
            annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
     }
     
     6 .沉浸式配置（记得主题改成 NoTitle 的）： 在 manifest.xml 的 <application>
     
                                               //配置
                                             <meta-data
                                                    android:name="android.max_aspect"
                                                    android:value="2.4" />
                                        </application>
      7 .
