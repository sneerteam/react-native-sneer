## React Native Sneer

[![npm version](https://badge.fury.io/js/react-native-sneer.svg)](https://badge.fury.io/js/react-native-sneer)

A react native android module to talk with the Sneer api.

### Setup

* install module

```bash
 npm install react-native-sneer --save
```

* `android/settings.gradle`

```gradle
...
include ':react-native-sneer'
project(':react-native-sneer').projectDir = new File(settingsDir, '../node_modules/react-native-sneer')
```

* `android/app/build.gradle`

```gradle
...
dependencies {
    ...
    compile project(':react-native-sneer')
}
```

* register module (in MainActivity.java)

```java
...
import me.sneer.react.SneerPackage;      // <-------
...

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {

 
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ...
    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      ...
      .addPackage(new MainReactPackage())
      .addPackage(new SneerPackage(this))      // <-------
      ...
      .build();

    ...

    setContentView(mReactRootView);
  }

  ...

}
```

## Usage

```js
var Sneer = require('react-native-sneer');
// TODO

```
