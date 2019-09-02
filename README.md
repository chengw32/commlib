# 个人用的在线依赖库  供学习使用


引用此库需要做的配置

    1 .配置 as 支持 AndroidX . （as 3.5版本在创建应用的时候勾选支持即可）
       再项目级的 gradle.properties 配置：
                                        android.useAndroidX=true
                                        android.enableJetifier=true
                                        
    2 .在 app 级的 build.gradle 的 allprojects 配置:
                                                  maven { url "https://jitpack.io" }
          
      
