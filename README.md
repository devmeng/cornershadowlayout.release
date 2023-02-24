# CornerShadowLayout API 23

[![](https://jitpack.io/v/devmeng/cornershadowlayout.release.svg)](https://jitpack.io/#devmeng/cornershadowlayout.release)

The special layout what you can config all corners radius, shadow and border line.

## Introduce

This viewgroup extends **ConstraintLayout**, so we have to use its function for adding view into CSL(CornerShadowLayout)

## Config environment

1.Config repository's url in your root project's **settings.gradle**

```groovy
pluginManagement {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

or in your root project's **build.gradle**

```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

2.Add dependence in your module's **build.gradle**

```groovy
dependencies {
    implementation "com.github.devmeng:cornershadowlayout.release:1.0.3"
}
```

## Attributes

| Name              | Default variant | Introduce                                  | Remark |
| ----------------- | --------------- | ------------------------------------------ | ------ |
| shadowColor       | #333            | The color of the shadow                    | √      |
| shadowRadius      | 0               | Radius of the shadow                       | √      |
| allCornerRadius   | 0               | Radius of layout's corners                 | √      |
| topLeftRadius     | 0               | Top-left radius of layout's corners        | √      |
| topRightRadius    | 0               | Top-right radius of layout's corners       | √      |
| bottomLeftRadius  | 0               | Bottom-left radius of layout's corners     | √      |
| bottomRightRadius | 0               | Bottom-right radius of layout's corners    | √      |
| backColor         | #FFF            | Background color of layout                 | √      |
| borderColor       | #333            | Border color of layout                     | √      |
| borderWidth       | 0               | Border width of layout                     | √      |
| backRes           | 0               | Drawable resources Id of background in CSL | >1.0.0 |

## Version Introduce

### v1.0.0

Using without **backRes** and **Skins**.

### v1.0.1

This version update **backRes** for changing background in area of drawing CSL. And updating the function of  changing skins.

### v1.0.2 - v1.0.3

Cause bout the wrong of changing skins for backRes, this version fixes it.

Please check the link if you wanna change its skins under here and initialize it follow README.

**Link skinlib.release: https://github.com/devmeng/skinlib.release** 



## Best wishes

Updating from time to time, keep following please. ;D
