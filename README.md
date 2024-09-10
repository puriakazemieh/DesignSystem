# Button:
## used in xml:

```xml
<com.kazemieh.designsystem.libdesign.Button  
    android:id="@+id/button"  
    android:layout_width="match_parent"  
    android:layout_height="match_parent"  
    android:layout_marginHorizontal="16dp"  
    android:layout_marginTop="8dp"  
    android:layout_marginBottom="16dp"  
    app:configurationButton="primary"  
    app:leadingIcon="@drawable/icon"  
    app:myCornerRadius="eight"  
    app:stateButton="enable"  
    app:styleButton="elevated"  
    app:buttonType="normal"  
    app:text="small button xml"  
    app:trailingIcon="@drawable/icon" />
```
## used in kotlin:

```kotlin
binding.button.buttonType = ButtonType.NORMAL // or ICON
binding.button.configuration = ConfigurationButton.PRIMARY // or ERROR
binding.button.leadingIcon =  
binding.button.root.context.getDrawable(R.drawable.icon) // or null
binding.button.trailingIcon =  
binding.button.root.context.getDrawable(R.drawable.icon) // or null 
binding.button.cornerRadius = CornerRadius.ROUND_100 // or ROUND_8 ,  25
binding.button.state = StateButton.ENABLE // or  DISABLE, LOADING
binding.button.style = StyleButton.FIELD // or   OUTLINE, TEXT, ELEVATED, TONAL
binding.button.text = "smal button kotlin code"
```

## change text:
### in xml:
```xml
app:buttonType="normall"  
```

### in kotlin:
```kotlin
binding.button.buttonType = ButtonType.NORMAL
// NORMAL ,  ICON
```


## use **primary** or **error** to change background color:
### in xml:
```xml
app:configurationButton="primary" 
```

### in kotlin:
```kotlin
binding.button.configuration = ConfigurationButton.PRIMARY
//or
binding.button.configuration = ConfigurationButton.ERROR
```

## icon in left:
### in xml:
```xml
app:leadingIcon="@drawable/icon" 
```

### in kotlin:
```kotlin
binding.button.leadingIcon =  
binding.button.root.context.getDrawable(R.drawable.icon)
//or
binding.button.leadingIcon = null
```

## icon in right:
### in xml:
```xml
app:trailingIcon="@drawable/icon"
```

### in kotlin:
```kotlin
binding.button.trailingIcon =  
binding.button.root.context.getDrawable(R.drawable.icon)
//or
binding.button.trailingIcon = null
```

## used **eight** or **hundred** to change radius or any **number**:
### in xml:
```xml
app:myCornerRadius="eight"  
```

### in kotlin:
```kotlin
binding.button.cornerRadius = CornerRadius.ROUND_100
//or
binding.button.cornerRadius = CornerRadius.ROUND_8
//or
binding.button.cornerRadius = 25
```

## used **enable** or **disable** or **loading** to change state:
### in xml:
```xml
app:stateButton="enable"  
```

### in kotlin:
```kotlin
binding.button.state = StateButton.ENABLE
// ENABLE, DISABLE, LOADING
```

## used **field**, **outline**, **standard**, **elevated** or **tonal** to change style:
### in xml:
```xml
app:styleButton="field" 
```

### in kotlin:
```kotlin
binding.button.style = StyleButton.FIELD
// FIELD, OUTLINE, TEXT, ELEVATED, TONAL
```

## change text:
### in xml:
```xml
app:text="small button xml"  
```

### in kotlin:
```kotlin
binding.button.text = "smal button kotlin code"
```

