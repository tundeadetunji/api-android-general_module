# Code Repertoire
A project containing handy functions for daily use.

<br />


To use, add this in your root build.gradle at the end of repositories:

```html
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Then add the dependency:

```html
dependencies {
	implementation 'com.github.tundeadetunji:api-android-general_module:TAG'
}
```
Where TAG is the latest VCS release.

<br />
For static imports (as required):
<br />
<br />
<pre>
import static com.inovationware.generalmodule.General.*;
import static com.inovationware.generalmodule.Device.*;
</pre>

To check if the device is connected to internet, for example:
```java
boolean isConnectedToInternet = thereIsInternet(getApplicationContext());
```

<h3>For non static imports:</h3>
<pre>
import com.inovationware.generalmodule.Feedback;
</pre>

To issue a toast, for example:
```java
private Feedback feedback = new Feedback(getApplicationContext());
feedback.toast("toast message");
```


There's a lot of WIP, but it's only expanding, and will also tend toward optimization in future releases.

For more, please check individual documentation, or the <a href="https://tundeadetunji.github.io/api-android-general_module/" target="_blank">main documentation site</a>.
