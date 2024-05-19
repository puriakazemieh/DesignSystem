# DesignSystem

# SmallButton:
## used in xml:

```xml
<com.kazemieh.designsystem.libdesign.buttons.button.small.SmallButton  
    android:id="@+id/smallButtonXml"  
    android:layout_width="match_parent"  
    android:layout_height="wrap_content"  
    android:layout_marginTop="8dp"  
    android:layout_marginBottom="16dp"  
    android:paddingHorizontal="16dp"  
    app:configurationSmallButton="primary"  
    app:leadingIcon="@drawable/icon"  
    app:myCornerRadius="eight"  
    app:stateSmallButton="enable"  
    app:styleSmallButton="field"  
    app:text="small button xml"  
    app:trailingIcon="@drawable/icon" />
```


## use **primary** or **error** to change background color:
```xml
app:configurationSmallButton="primary" 
```

```kotlin
binding.incSmallButtonSample.smallButton.configuration = ConfigurationSmallButton.PRIMARY
//or
binding.incSmallButtonSample.smallButton.configuration = ConfigurationSmallButton.ERROR
```

## icon in left:
```xml
app:leadingIcon="@drawable/icon" 
```

```kotlin
binding.incSmallButtonSample.smallButton.leadingIcon =  
binding.incSmallButtonSample.root.context.getDrawable(R.drawable.icon)
//or
binding.incSmallButtonSample.smallButton.leadingIcon = null
```

## icon in right:
```xml
app:trailingIcon="@drawable/icon"
```

```kotlin
binding.incSmallButtonSample.smallButton.trailingIcon =  
binding.incSmallButtonSample.root.context.getDrawable(R.drawable.icon)
//or
binding.incSmallButtonSample.smallButton.trailingIcon = null
```

## used **eight** or **hundred** to change radius or any **number**:
```xml
app:myCornerRadius="eight"  
```

```kotlin
binding.incSmallButtonSample.smallButton.cornerRadius = CornerRadius.ROUND_100
//or
binding.incSmallButtonSample.smallButton.cornerRadius = CornerRadius.ROUND_8
//or
binding.incSmallButtonSample.smallButton.cornerRadius = 25
```

## used **enable** or **disable** or **loading** to change state:
```xml
app:stateSmallButton="enable"  
```

```kotlin
binding.incSmallButtonSample.smallButton.state = StateSmallButton.ENABLE
// ENABLE, DISABLE, LOADING
```

## used **field**, **outline**, **standard**, **elevated** or **tonal** to change style:
```xml
app:styleSmallButton="field" 
```

```kotlin
binding.incSmallButtonSample.smallButton.style = StyleSmallButton.FIELD
// FIELD, OUTLINE, TEXT, ELEVATED, TONAL
```

## change text:
```xml
app:text="small button xml"  
```

```kotlin
binding.incSmallButtonSample.smallButton.text = "smal button kotlin code"
```


