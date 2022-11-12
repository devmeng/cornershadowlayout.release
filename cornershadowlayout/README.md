# CornerShadowLayout

The special layout what you can config all corners radius, shadow and border line.

## Introduce

This viewgroup extends **ConstraintLayout**, so we have to use its function for adding view into CSL(CornerShadowLayout)

## Attributes

| Name              | Default variant | Introduce                               | Remark          |
| ----------------- | --------------- | --------------------------------------- | --------------- |
| shadowColor       | #333            | The color of the shadow                 | √               |
| shadowRadius      | 0               | Radius of the shadow                    | √               |
| allCornerRadius   | 0               | Radius of layout's corners              | √               |
| topLeftRadius     | 0               | Top-left radius of layout's corners     | √               |
| topRightRadius    | 0               | Top-right radius of layout's corners    | √               |
| bottomLeftRadius  | 0               | Bottom-left radius of layout's corners  | √               |
| bottomRightRadius | 0               | Bottom-right radius of layout's corners | √               |
| backColor         | #FFF            | Background color of layout              | √               |
| borderColor       | #333            | Border color of layout                  | √               |
| borderWidth       | 0               | Border width of layout                  | √               |
| backRes           | 0               | Resources Id of layout background       | update in 1.0.1 |

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
    implementation "com.github.devmeng:cornershadowlayout.release:1.0.0"
}
```

## Best wishes

:D
